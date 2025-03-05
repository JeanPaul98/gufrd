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

  getPaiementUrl(demand: DemandAeaaModel) {
    this.demandService.getPayementUrl(demand.idFormalite);
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
    this.selectedDemand = demand;
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
    this.openModal(demand);
  }

  openModal(demand: DemandAeaaModel) {
    this.selectedDemand = demand;
    this.isModalVisible = true;
  }

  onConfirm() {
    this.isLoading = true;
    if (this.selectedDemand.urlPaiement) {
      // Open the payment URL in a new tab
      window.open(this.selectedDemand.urlPaiement, '_blank');
      this.isLoading = false;
      this.isModalVisible = false;
    }
  }

  onCancel() {
    this.isModalVisible = false;
  }
}

