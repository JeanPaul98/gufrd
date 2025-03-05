import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {Row} from "@tanstack/angular-table";
import {finalize} from "rxjs";
import {DemandAeaaModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {DemandService} from "../../services/demand.service";

@UntilDestroy()
@Component({
  selector: 'app-ipn-processed',
  templateUrl: './processed.component.html',
  styleUrl: './processed.component.scss'
})
export class ProcessedComponent implements OnInit {
  demand!: Row<DemandAeaaModel>;
  loadingDemands: boolean = false;
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);

  constructor(private demandService: DemandService) {
  }

  ngOnInit(): void {
    this.getDemandProcessed();
  }

  getDemandProcessed() {
    this.loadingDemands = true;
    this.demandService.getDemandProcessed().pipe(
      untilDestroyed(this),
      finalize(() => {
        if (this.demands() !== undefined) {
          this.loadingDemands = false;
          this.demandService.currentDemandsSubject.next(this.demands());
        }
      })
    ).subscribe((res) => {
      this.demands.set(res as DemandAeaaModel[]);
    });
  }

  demandDetailsInfo(demand: DemandAeaaModel) {
    this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetailIPN', JSON.stringify(demand));
  }

  xendDetailsInfo(demand: DemandAeaaModel) {
    this.demandService.currentDemandSubject.next(demand);
    localStorage.setItem('demandDetail', JSON.stringify(demand));

  }
}
