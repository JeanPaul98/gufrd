import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {Row} from "@tanstack/angular-table";
import {DemandService} from "../../services/demand.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {finalize} from "rxjs";
import {DemandAeaaModel} from "../../../../../../../../../models/demand-aeaa.model";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";

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

  generateAutorisationPdf(idFormalite:number) {
    toast.custom(NiceToastComponent, {
      position: 'top-center',
      dismissible: false,
      componentProps:{
        texto: 'Génération de l\'autorisation en cours...',
        state: 'loading'
      }
    });
    this.demandService.generateAutorisation(idFormalite).subscribe();
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
