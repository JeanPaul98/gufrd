import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {DemandService} from "../../services/demand.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {DemandiocavModel} from "../../../../../../../../../src/app/models/demand-iocav.model";
import {Row} from "@tanstack/angular-table";
import {finalize} from "rxjs";


@UntilDestroy()
@Component({
  selector: 'app-submitted',
  templateUrl: './submitted.component.html',
  styleUrl: './submitted.component.scss'
})
export class SubmittedComponent implements OnInit {
  demand!: Row<DemandiocavModel>;
  demands: WritableSignal<DemandiocavModel[]> = signal<DemandiocavModel[]>([]);
  loadingDemands: boolean = false;

  constructor(private demandService: DemandService) {
    console.log('constructor');
  }

  ngOnInit(): void {
    this.getDemandSubmitted();
  }



  getDemandSubmitted() {
    this.loadingDemands = true;
    this.demandService.getDemandSubmitted().pipe(
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
