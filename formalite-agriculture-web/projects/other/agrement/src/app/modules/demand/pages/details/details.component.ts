import {Location} from "@angular/common";
import {Component, OnInit} from '@angular/core';
import {DemandAeaaCompletModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {NavireInfoModel} from "../../../../../../../../../src/app/models/navire-info.model";
import {ProductModel} from "../../../../../../../../../src/app/models/product.model";
import {DemandService} from "../../services/demand.service";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export class DetailsComponent implements  OnInit {

  step1LocalData: NavireInfoModel = JSON.parse(
    localStorage.getItem('step1AGREMENT') as string
  );
  step2LocalData: { detAutorisationProduitDtos: ProductModel[] } = JSON.parse(
    localStorage.getItem('step2AGREMENT') as string
  );

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
    // localStorage.setItem('currentStepAGREMENT', JSON.stringify(this.currentStep))
    this.demandService.isHideDemandBarSubject.next(false);
    this.demandService.back();
  }
}
