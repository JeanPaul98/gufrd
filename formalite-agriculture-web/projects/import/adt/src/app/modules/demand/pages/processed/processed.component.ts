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
  selector: 'app-processed',
  templateUrl: './processed.component.html',
  styleUrl: './processed.component.scss'
})
export class ProcessedComponent implements OnInit {
  demand!: Row<DemandAeaaModel>;
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);
  loadingDemands: boolean = false;

  constructor(private demandService: DemandService) {
    console.log('constructor');
  }

  ngOnInit(): void {
    this.getDemandProcessed();
  }

  generateAutorisationPdf(demand:DemandAeaaModel) {
    if(demand.statutPaiement === 'PAYER' || demand.montantRedevance === 0) {
      toast.custom(NiceToastComponent, {
        position: 'top-center',
        dismissible: false,
        componentProps: {
          texto: 'Génération de l\'autorisation en cours...',
          state: 'loading'
        }
      });
      this.demandService.generateAutorisation(demand.idFormalite).subscribe(() => {
        toast.custom(NiceToastComponent, {
          position: 'top-center',
          dismissible: false,
          componentProps: {
            texto: 'Autorisation générée avec succès!',
            state: 'success'
          }
        });
        this.getDemandProcessed()
      });
    }else{
      toast.custom(NiceToastComponent, {
        position: 'top-center',
        componentProps: {
          texto: 'Impossible de générer l\'autorisation car la demande n\'est pas payée.',
          state: 'error'
        }
      });
    }
  }

  getDemandProcessed() {
    this.loadingDemands = true;
    this.demandService.getDemandProcessed().pipe(
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
}
