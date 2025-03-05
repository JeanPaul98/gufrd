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
  type: string = 'confirm';
  title: string = 'Confirmer la soumission';
  message: string = 'Cliquer sur le bouton "Confirmer" pour soumettre la demande!';
  cancelButton: string = 'Annuler';
  confirmButton: string = 'Confirmer';
  demand!: DemandAeaaModel;
  modalVisible: boolean = false;
  loadingSubmit: boolean = false;
  loadingDemands: boolean = false;
  elements: any[] = [1, 2, 3, 4, 5];
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);

  constructor(private service: DemandService) {
  }

  ngOnInit(): void {
    this.getDemandNotSubmitted();
  }

  getDemandNotSubmitted(): void {
    this.loadingDemands = true;
    this.service.getDemandNotSubmitted().pipe(
      untilDestroyed(this),
      finalize(() => {
        if (this.demands() !== undefined) {
          this.loadingDemands = false;
          this.service.currentDemandsSubject.next(this.demands());
        }
      })
    ).subscribe((res) => {
      this.demands.set(res as DemandAeaaModel[]);
    });
  }

  onSubmit(): void {
    this.loadingSubmit = true;
    this.service.submitDemand({idFormalite: this.demand.idFormalite}).pipe(
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
    this.service.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetailIPN', JSON.stringify(demand));
  }

  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }

  openModal(demand: DemandAeaaModel, type: string = 'confirm', title: string = 'Confirmer la soumission', message: string = 'Cliquer sur le bouton "Confirmer" pour soumettre la demande!', cancelButton: string = 'Annuler', confirmButton: string = 'Confirmer') {
    this.title = title;
    this.message = message;
    this.cancelButton = cancelButton;
    this.confirmButton = confirmButton;
    this.type = type;
    this.demand = demand;
    this.modalVisible = true;
  }

  choiceOnSubmit(type: string) {
    if (type === 'confirm') {
      this.onSubmit();
    } else if (type === 'delete') {
      this.onDelete();
    }
  }

  onDelete() {
    this.loadingSubmit = true;
    this.service.deleteDemand({idFormalite: this.demand.idFormalite}).pipe(
      untilDestroyed(this),
      tap(() => {
        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps: {
            texto: 'La demande d\'inspection phytosanitaire de navire a été supprimée avec succès',
            state: 'success'
          }
        });
        this.getDemandNotSubmitted();
      }),
      finalize(() => {
        this.modalVisible = false;
        this.loadingSubmit = false;
      })
    ).subscribe();
  }
}

