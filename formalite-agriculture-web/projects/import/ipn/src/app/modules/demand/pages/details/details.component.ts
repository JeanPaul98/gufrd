import {Component, OnInit} from '@angular/core';
import {DemandService} from "../../services/demand.service";
import {Location} from "@angular/common";
import {DemandAeaaCompletModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";

@Component({
  selector: 'app-ipn-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export class DetailsComponent implements OnInit {

  shipInfo: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetailIPN') as string);

  constructor(private demandService: DemandService, private location: Location) {
  }

  ngOnInit(): void {
    this.demandService.isHideDemandBarSubject.next(true);
    this.location.subscribe(() => {
      this.demandService.isHideDemandBarSubject.next(false);
    });
  }

  onClickBack() {
    this.demandService.isHideDemandBarSubject.next(false);
    this.demandService.back();
  }
}
