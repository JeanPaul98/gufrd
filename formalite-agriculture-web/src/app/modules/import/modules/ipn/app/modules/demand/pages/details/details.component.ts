import {Component, OnInit} from '@angular/core';

import {Location} from "@angular/common";
import {NavireInfoModel} from "../../../../../../../../../models/navire-info.model";
import {ProductModel} from "../../../../../../../../../models/product.model";
import {DemandAeaaCompletModel} from "../../../../../../../../../models/demand-aeaa.model";
import {DemandService} from "../../services/demand.service";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export class DetailsComponent implements OnInit {

  step1LocalData: NavireInfoModel = JSON.parse(
    localStorage.getItem('step1AEAA') as string
  );
  step2LocalData: { detAutorisationProduitDtos: ProductModel[] } = JSON.parse(
    localStorage.getItem('step2AEAA') as string
  );

  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

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
    this.demandService.simpleBack();
  }
}
