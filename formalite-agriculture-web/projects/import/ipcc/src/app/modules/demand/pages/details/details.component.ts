import {Location} from "@angular/common";
import {AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {DemandAeaaCompletModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {DetPhytosanitaireProduitDto} from "../../../../../../../../../src/app/requests/ip.request";
import {DemandService} from "../../services/demand.service";

@Component({
  selector: 'app-ipn-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DetailsComponent implements OnInit, AfterViewInit {

  shipInfo: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);
  products: DetPhytosanitaireProduitDto[] = [];

  constructor(private demandService: DemandService, private location: Location, private cdr: ChangeDetectorRef) {
  }

  ngOnInit(): void {
    this.demandService.isHideDemandBarSubject.next(true);
    this.location.subscribe(() => {
      this.demandService.isHideDemandBarSubject.next(false);
    });
    this.cdr.markForCheck();
  }

  onClickBack() {
    this.demandService.isHideDemandBarSubject.next(false);
    this.demandService.back();
  }

  ngAfterViewInit(): void {
    const demand = JSON.parse(localStorage.getItem('demandDetail') as string);
    this.shipInfo = demand;
    this.products = demand.detPhytosanitaireProduitDtos || [];
    this.cdr.detectChanges();
  }
}
