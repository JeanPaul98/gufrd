import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {Row} from "@tanstack/angular-table";
import {finalize} from "rxjs";
import {DemandAeaaModel} from "../../../../../../../../../models/demand-aeaa.model";
import {DemandService} from "../../services/demand.service";
import {tap} from "rxjs/operators";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";


@UntilDestroy()
@Component({
  selector: 'app-submitted',
  templateUrl: './submitted.component.html',
  styleUrl: './submitted.component.scss'
})
export class SubmittedComponent implements OnInit {
  demand!: DemandAeaaModel;
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);
  loadingDemands: boolean = false;
  modalVisible: boolean = false;
  loadingSubmit: boolean = false;

  constructor(private demandService: DemandService) {
    console.log('constructor');
  }

  ngOnInit(): void {
    this.getDemandSubmitted();
  }



  getDemandSubmitted() {
    this.loadingDemands = true;
    this.demandService.getDemandSubmitted().pipe(
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
    this.demandService.acceptDemand({
      idFormalite: this.demand.idFormalite,
      // numGenere: this.demand.numGenerer,
    }).pipe(
      untilDestroyed(this),
      tap(() => {

        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps:{
            texto: 'Demande acceptée!',
            state: 'success'
          }
        });
        this.getDemandSubmitted();
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
