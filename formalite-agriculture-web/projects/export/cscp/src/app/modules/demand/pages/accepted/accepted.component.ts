import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {Row} from "@tanstack/angular-table";
import {DemandiocavModel} from "../../../../../../../../../src/app/models/demand-iocav.model";
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
  demand!: Row<DemandiocavModel>;
  demands: WritableSignal<DemandiocavModel[]> = signal<DemandiocavModel[]>([]);
  loadingDemands: boolean = false;

  constructor(private demandService: DemandService) {
    console.log('constructor');
  }

  ngOnInit(): void {
    this.getDemandAccepted();
  }

  getPaiementUrl(demand: DemandiocavModel) {
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
      this.demands.set(res as DemandiocavModel[]);
    });
  }

  xendDetailsInfo(demand: DemandiocavModel) {
    this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetail', JSON.stringify(demand));

  }
}

