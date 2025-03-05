import {Location} from "@angular/common";
import {AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {DemandAeaaCompletModel} from "../../../../../../../../../models/demand-aeaa.model";
import {NavireInfoModel} from "../../../../../../../../../models/navire-info.model";
import {ProductModel} from "../../../../../../../../../models/product.model";
import {DetPhytosanitaireProduitDto} from "../../../../../../../../../requests/ip.request";
import {DemandService} from "../../services/demand.service";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export class DetailsComponent implements  OnInit, AfterViewInit {

  step1LocalData: NavireInfoModel = JSON.parse(
    localStorage.getItem('step1AEAA') as string
  );
  step2LocalData: { detAutorisationProduitDtos: ProductModel[] } = JSON.parse(
    localStorage.getItem('step2AEAA') as string
  );

  shipInfo: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);


  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

  products: DetPhytosanitaireProduitDto[] = [];

  constructor(private demandService: DemandService, private cdr: ChangeDetectorRef, private location: Location) {
    // this.demand$ = this.demandService.currentDemandSubject.asObservable();
  }
  ngOnInit(): void {
    this.demandService.isHideDemandBarSubject.next(true);
    this.location.subscribe(() => {
      this.demandService.isHideDemandBarSubject.next(false);
    });
  }

  onClickBack() {
    // this.currentStep = 1;
    // this.onClickPrevious()
    // localStorage.setItem('currentStepAEAA', JSON.stringify(this.currentStep))
    this.demandService.isHideDemandBarSubject.next(false);
    this.demandService.simpleBack();
  }

  ngAfterViewInit(): void {
    const demand = JSON.parse(localStorage.getItem('demandDetail') as string);
    this.shipInfo = demand;
    this.products = demand.detPhytosanitaireProduitDtos || [];
    this.cdr.detectChanges();
  }
}
