import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {Row} from "@tanstack/angular-table";
import {toast} from "ngx-sonner";
import {finalize} from "rxjs";
import {DemandAeaaModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {NiceToastComponent} from "../../../../../../../../../src/components/nice-toast/nice-toast.component";
import {DemandService} from "../../services/demand.service";

@UntilDestroy()
@Component({
  selector: 'app-ipn-accepted',
  templateUrl: './accepted.component.html',
  styleUrl: './accepted.component.scss'
})
export class AcceptedComponent implements OnInit {
  demand!: Row<DemandAeaaModel>;
  selectedDemand!: DemandAeaaModel;
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);
  loadingDemands: boolean = false;

  urlPayement!: string;
  isModalVisible: boolean = false;
  disabled!: boolean;
  isLoading: boolean = false;

  constructor(private demandService: DemandService) {
  }

  ngOnInit(): void {
    this.getDemandAccepted();
  }

  getPaiementUrl(demand: DemandAeaaModel) {
    this.demandService.getPayementUrl(demand.idFormalite).subscribe((res) => {
      this.urlPayement = res?.url as string;
      window.open(this.urlPayement, '_blank');
      this.isLoading = false;
      this.isModalVisible = false;
    });
  }

  getDemandAccepted() {
    this.loadingDemands = true;
    this.demandService.getDemandAccepted().pipe(
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

  xendDetailsInfo(demand: DemandAeaaModel) {
    this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetail', JSON.stringify(demand));

  }

  payerDemand(demand: DemandAeaaModel) {
    if(demand.statutPaiement === 'PAYER') {
      toast.custom(NiceToastComponent, {
        position: 'top-center',
        componentProps: {
          texto: `Demande ${demand.numGenerer} déja payée, veuillez patienter pour que les agents puissent traiter la demande!`,
          state: 'info',
        },
      });
      return;
    }

    if(demand.montantRedevance === 0) {
      toast.custom(NiceToastComponent, {
        position: 'top-center',
        componentProps: {
          texto: `Veuillez patienter pour que les agents puissent traiter la demande!`,
          state: 'info',
        },
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
    this.isLoading = true;
    this.getPaiementUrl(this.selectedDemand);

  }

  onCancel() {
    this.isModalVisible = false;
  }
}
