import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {Row} from "@tanstack/angular-table";
import {toast} from "ngx-sonner";
import {finalize} from "rxjs";
import {DemandAeaaModel} from "../../../../../../../../../models/demand-aeaa.model";
import {DemandService} from "../../services/demand.service";

@UntilDestroy()
@Component({
  selector: 'app-accepted',
  templateUrl: './accepted.component.html',
  styleUrl: './accepted.component.scss'
})
export class AcceptedComponent implements OnInit {
  demand!: Row<DemandAeaaModel>;
  selectedDemand!: DemandAeaaModel;
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);
  loadingDemands: boolean = false;

  isModalVisible: boolean = false;
  disabled!: boolean;
  isLoading: boolean = false;

  constructor(private demandService: DemandService) {
    console.log('constructor');
  }

  ngOnInit(): void {
    this.getDemandAccepted();
  }

  generateAutorisationPdf(demand: DemandAeaaModel) {
    if (demand.statutPaiement === 'PAYER' || demand.montantRedevance === 0) {
      toast.custom(NiceToastComponent, {
        position: 'top-center',
        dismissible: false,
        componentProps: {
          texto: 'Génération de l\'autorisation en cours...',
          state: 'loading'
        }
      });
      this.demandService.generateAutorisation(demand.idFormalite).subscribe(() => {
        this.getDemandAccepted()
      });
    } else {
      toast.custom(NiceToastComponent, {
        position: 'top-center',
        componentProps: {
          texto: 'Impossible de générer l\'autorisation car la demande n\'est pas payée.',
          state: 'error'
        }
      });
    }
  }

  getDemandAccepted() {
    this.loadingDemands = true;
    this.demandService.getDemandAccepted().pipe(
      untilDestroyed(this),
      finalize(() => {
        if (this.demands() !== undefined) {
          this.loadingDemands = false;
          this.demandService.currentDemandsSubject.next(this.demands());
        }
      })
    ).subscribe((res) => {
      console.log('res', res);
      this.demands.set(res as DemandAeaaModel[]);
    });
  }

  xendDetailsInfo(demand: DemandAeaaModel) {
    this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetail', JSON.stringify(demand));

  }

  okToGenerateAutorisation(demand: DemandAeaaModel) {
    if (demand.statutPaiement !== 'PAYER' && demand.montantRedevance !== 0) {
      toast.custom(NiceToastComponent, {
        position: 'top-center',
        componentProps: {
          texto: 'Impossible de générer l\'autorisation car la demande n\'est pas payée.',
          state: 'error'
        }
      });
      return;
    }
    this.openModal(demand);
  }

  openModal(demand: DemandAeaaModel) {
    this.selectedDemand = demand;
    this.isModalVisible = true;
  }

  onConfirm() {

    this.demandService.confirmPayement(this.selectedDemand.idFormalite).subscribe({
      next: (res) => {
        {
          setTimeout(() => {
            this.generateAutorisationPdf(this.selectedDemand)
            this.isLoading = false;
            this.isModalVisible = false;

          }, 600);
        }
      },
      error: (err) => {
        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps: {
            texto: 'Impossible de générer l\'autorisation, veuillez réessayer.',
            state: 'error'
          }
        });
      }
    });

  }

  onCancel() {
    this.isModalVisible = false;
  }
}

