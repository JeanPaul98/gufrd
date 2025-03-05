import {Component, ComponentRef, OnInit, signal, ViewContainerRef, WritableSignal} from '@angular/core';
import { DemandService } from '../../services/demand.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import {DemandAeaaModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {Row} from "@tanstack/angular-table";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "../../../../../../../../../src/components/nice-toast/nice-toast.component";
import {finalize} from "rxjs";
import {delay, tap} from "rxjs/operators";

@UntilDestroy()
@Component({
  selector: 'app-not-submitted',
  templateUrl: './not-submitted.component.html',
  styleUrl: './not-submitted.component.scss'
})
export class NotSubmittedComponent implements OnInit {
  elements: any[] = [1,2,3,4,5];
  demand!: DemandAeaaModel;
  private niceToastComponentRef!: ComponentRef<NiceToastComponent>;
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);
  modalVisible: boolean = false;
  loadingSubmit: boolean = false;
  loadingDemands: boolean = false;

  constructor(private demandService: DemandService,private viewContainerRef: ViewContainerRef) {
    console.log('constructor');

  }

  ngOnInit(): void {
    this.getDemandNotSubmitted();
  }

  getDemandNotSubmitted() {
    this.loadingDemands = true;
    this.demandService.getDemandNotSubmitted().pipe(
      untilDestroyed(this),
      finalize(() => {
        if(this.demands() !== undefined) {
          this.loadingDemands = false;
          this.demandService.currentDemandsSubject.next(this.demands());
        }
      })
    ).subscribe((res) => {
      console.log('res', res);
      this.demands.set(res as DemandAeaaModel[]);

    });
  }

  openModal(demand: DemandAeaaModel) {

    this.demand = demand;
    this.modalVisible = true;
  }


  onSubmit() {
    console.log('onSubmit', this.demand);
    this.loadingSubmit = true;
    this.demandService.nowSubmitDemand({
      // id: this.demand.idFormalite,
      // numGenere: this.demand.numGenerer,
      idFormalite: this.demand.idFormalite,
    }).pipe(
      untilDestroyed(this),
      tap(() => {

        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps:{
            texto: 'Demande soumise',
            state: 'success'
          }
        });
        this.getDemandNotSubmitted();
      }),
      finalize(() => {
        this.modalVisible = false;
        this.loadingSubmit = false;
      })
    ).subscribe((res) => {
      console.log('res', res);
    });

  }


  xendDetailsInfo(demand: DemandAeaaModel) {
    this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetail', JSON.stringify(demand));

  }

  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }


}
