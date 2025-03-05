import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {DemandAeaaModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {DemandService} from "../../services/demand.service";
import {finalize, tap} from "rxjs";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "../../../../../../../../../src/components/nice-toast/nice-toast.component";


@UntilDestroy()
@Component({
  selector: 'app-ipppp-not-submitted',
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

  getDemandNotSubmitted(): void {
    this.loadingDemands = true;
    this.demandService.getDemandNotSubmitted().pipe(
      untilDestroyed(this),
      finalize(() => {
        if (this.demands() !== undefined) {
          this.loadingDemands = false;
          this.demandService.currentDemandsSubject.next(this.demands());
        }
      })
    ).subscribe((res) => {
      this.demands.set(res as DemandAeaaModel[]);
    });
  }

  openModal(demand: DemandAeaaModel): void {
    this.demand = demand;
    this.modalVisible = true;
  }

  onSubmit(): void {
    this.loadingSubmit = true;
    this.demandService.submitDemand({idFormalite: this.demand.idFormalite}).pipe(
      untilDestroyed(this),
      tap(() => {
        toast.custom(
          NiceToastComponent, {
            position: 'top-center',
            componentProps: {
              texto: 'Demande d\'inspection phytosanitaire de navire a été soumise avec succès',
              state: 'success'
            }
          }
        );
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
    localStorage.setItem('demandDetailIPPPP', JSON.stringify(demand));
  }

  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }
}

