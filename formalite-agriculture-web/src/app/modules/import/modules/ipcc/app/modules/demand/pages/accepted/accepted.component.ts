import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {Router} from "@angular/router";
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
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);
  loadingDemands: boolean = false;

  constructor(private demandService: DemandService, private router: Router) {
    console.log('constructor');
  }

  ngOnInit(): void {
    this.getDemandAccepted();
  }

  generatePVPdf(demand: DemandAeaaModel) {
    if (demand.statuPaiement !== 'PAYER' && demand.montantRedevance !== 0) {
      toast.custom(NiceToastComponent, {
        position: 'top-center',
        componentProps: {
          texto: 'Impossible de générer le PV car la demande n\'est pas payée.',
          state: 'error'
        }
      });
      return;
    }

    toast.custom(NiceToastComponent, {
      position: 'top-center',
      dismissible: false,
      componentProps:{
        texto: 'Génération du PV en cours...',
        state: 'loading'
      }
    });
    this.demandService.generatePVPdf(demand.idFormalite).subscribe();
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

  xendDetailsInfoAndCreatePV(demand: DemandAeaaModel) {
    if (demand.statuPaiement !== 'PAYER' && demand.montantRedevance !== 0) {
      toast.custom(NiceToastComponent, {
        position: 'top-center',
        componentProps: {
          texto: 'Impossible de créer le PV car la demande n\'est pas payée.',
          state: 'error'
        }
      });
      return;
    }

    this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetail', JSON.stringify(demand));
    this.router.navigate(['/importations/ipcc/demand/create-pv']);
  }
}

