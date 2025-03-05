import {AfterViewInit, Component, OnInit} from '@angular/core';

import {Location} from "@angular/common";
import {Observable} from "rxjs";
import {NavireInfoModel} from "../../../../../../../../../models/navire-info.model";
import {ProductModel} from "../../../../../../../../../models/product.model";
import {DemandAeaaCompletModel} from "../../../../../../../../../models/demand-aeaa.model";
import {DemandService} from "../../services/demand.service";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export class DetailsComponent implements  OnInit {

  curentTab: string = localStorage.getItem('currentTabsAIPCLAdmin') as string ?? 'submitted';
  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

  constructor(private demandService: DemandService, private location: Location) {
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


}
