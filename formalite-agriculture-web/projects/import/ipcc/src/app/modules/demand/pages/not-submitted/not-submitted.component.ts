import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {DemandAeaaModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {DemandService} from "../../services/demand.service";
import {finalize, tap} from "rxjs";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "../../../../../../../../../src/components/nice-toast/nice-toast.component";

@UntilDestroy()
@Component({
  selector: 'app-ipn-not-submitted',
  templateUrl: './not-submitted.component.html',
  styleUrl: './not-submitted.component.scss'
})
export class NotSubmittedComponent implements OnInit {
  demand!: DemandAeaaModel;
  modalVisible: boolean = false;
  loadingSubmit: boolean = false;
  loadingDemands: boolean = false;
  elements: any[] = [1, 2, 3, 4, 5];
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);

  
  constructor(private demandService: DemandService) {
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
      idFormalite: this.demand.idFormalite,
      // numGenere: this.demand.numGenerer,
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

  demandDetailsInfo(demand: DemandAeaaModel): void {
    this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetailICPP', JSON.stringify(demand));
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
