import {Component, OnInit} from '@angular/core';
import {DemandAeaaCompletModel} from "../../../../../../../../../models/demand-aeaa.model";
import {DemandService} from "../../services/demand.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-generer-autorisation',
  templateUrl: './generer-autorisation.component.html',
  styleUrl: './generer-autorisation.component.scss'
})
export class GenererAutorisationComponent implements  OnInit {
  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

  curentTab: string = localStorage.getItem('currentTabsAIPCLAdmin') as string ?? 'submitted';


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
    this.demandService.customBack('/exportations/iocav/demand/'+this.curentTab);
  }
}
