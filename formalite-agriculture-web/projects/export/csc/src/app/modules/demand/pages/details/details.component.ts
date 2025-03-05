import {AfterViewInit, Component, OnInit} from '@angular/core';
import {NavireInfoModel} from "../../../../../../../../../src/app/models/navire-info.model";
import {ProductModel} from "../../../../../../../../../src/app/models/product.model";
import {DemandService} from "../../services/demand.service";
import {Location} from "@angular/common";
import {DemandiocavCompletModel} from "../../../../../../../../../src/app/models/demand-iocav.model";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export class DetailsComponent implements  OnInit {

  step1LocalData: NavireInfoModel = JSON.parse(
    localStorage.getItem('step1iocav') as string
  );
  step2LocalData: { detCertificatProduitDtos: ProductModel[] } = JSON.parse(
    localStorage.getItem('step2iocav') as string
  );

  demand: DemandiocavCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

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
    this.demandService.back();
  }
}
