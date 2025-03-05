import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {Row} from "@tanstack/angular-table";
import {DemandAeaaModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {DemandService} from "../../services/demand.service";
import {finalize} from "rxjs";

@UntilDestroy()
@Component({
  selector: 'app-ipppp-submitted',
  templateUrl: './submitted.component.html',
  styleUrl: './submitted.component.scss'
})
export class SubmittedComponent implements OnInit {
  demand!: Row<DemandAeaaModel>;
  loadingDemands: boolean = false;
  demands: WritableSignal<DemandAeaaModel[]> = signal<DemandAeaaModel[]>([]);

  constructor(private demandService: DemandService) {
  }

  ngOnInit(): void {
    this.getDemandSubmitted();
  }

  getDemandSubmitted() {
    this.loadingDemands = true;
    this.demandService.getDemandSubmitted().pipe(
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
    localStorage.setItem('demandDetailIPPPP', JSON.stringify(demand));
  }
}
