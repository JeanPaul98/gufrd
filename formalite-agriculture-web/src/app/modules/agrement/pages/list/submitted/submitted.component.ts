import {DatePipe} from "@angular/common";
import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
import {RouterModule} from "@angular/router";
import {ButtonModule, TableModule} from "@coreui/angular";
import {ConfirmModalComponent} from "@docs-components/confirm-modal/confirm-modal.component";
import {LoadingBarComponent} from "@docs-components/loading-bar/loading-bar.component";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {finalize} from "rxjs";
import {DemandAgrementModel} from "../../../../../models/agrement.model";
import {PricePipe} from "../../../../../utils/pipes/price.pipe";

import {AgrementService} from "../../../services/agrement.service";

@UntilDestroy()
@Component({
  selector: 'app-submitted',
  standalone: true,
  imports: [
    TableModule,
    ButtonModule,
    LoadingBarComponent,
    DatePipe,
    RouterModule,
    ConfirmModalComponent,
    PricePipe
  ],
  templateUrl: './submitted.component.html',
  styleUrl: './submitted.component.scss'
})
export class SubmittedComponent implements OnInit {
  demands: WritableSignal<DemandAgrementModel[]> = signal<DemandAgrementModel[]>([]);
  demand!: DemandAgrementModel;
  loadingDemands: boolean = false;
  modalVisible: boolean = false;
  loadingSubmit: boolean = false;

  agrementSerice =  inject(AgrementService);

  ngOnInit(): void {
    this.getDemandSubmitted();
  }

  openModal(demand: DemandAgrementModel) {

    this.demand = demand;
    this.modalVisible = true;
  }

  xendDetailsInfo(demand: DemandAgrementModel) {
    // this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetail', JSON.stringify(demand));

  }

  getDemandSubmitted() {
    this.loadingDemands = true;
    this.agrementSerice.getDemandSubmitted().pipe(
      untilDestroyed(this),
      finalize(() => {
        if(this.demands() !== undefined) {
          this.loadingDemands = false;
          // this.agrementSerice.currentDemandsSubject.next(this.demands());
        }
      })
    ).subscribe((res) => {
      console.log('res', res);
      this.demands.set(res as DemandAgrementModel[]);
    });
  }


  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }

  onSubmit() {

  }
}
