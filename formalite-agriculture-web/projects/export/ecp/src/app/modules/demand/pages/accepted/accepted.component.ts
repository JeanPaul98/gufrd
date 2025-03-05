import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {Row} from "@tanstack/angular-table";
import {DemandAeaaCompletModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {DemandService} from "../../services/demand.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {finalize} from "rxjs";

@UntilDestroy()
@Component({
  selector: 'app-accepted',
  templateUrl: './accepted.component.html',
  styleUrl: './accepted.component.scss'
})
export class AcceptedComponent implements OnInit {
  demand!: Row<DemandAeaaCompletModel>;
  demands: WritableSignal<DemandAeaaCompletModel[]> = signal<DemandAeaaCompletModel[]>([]);
  loadingDemands: boolean = false;

  constructor(private demandService: DemandService) {
    console.log('constructor');
  }

  ngOnInit(): void {
    this.getDemandAccepted();
  }

  getPaiementUrl(demand: DemandAeaaCompletModel) {
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
      this.demands.set(res as DemandAeaaCompletModel[]);
    });
  }

  xendDetailsInfo(demand: DemandAeaaCompletModel) {
    this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetail', JSON.stringify(demand));

  }
}

