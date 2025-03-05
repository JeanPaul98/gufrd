import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {Row} from "@tanstack/angular-table";
import {DemandAeaaCompletModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {DemandService} from "../../services/demand.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {finalize} from "rxjs";

@UntilDestroy()
@Component({
  selector: 'app-processed',
  templateUrl: './processed.component.html',
  styleUrl: './processed.component.scss'
})
export class ProcessedComponent implements OnInit {
  demand!: Row<DemandAeaaCompletModel>;
  demands: WritableSignal<DemandAeaaCompletModel[]> = signal<DemandAeaaCompletModel[]>([]);

  constructor(private demandService: DemandService) {
    console.log('constructor');
  }

  ngOnInit(): void {
    this.getDemandProcessed();
  }

  getDemandProcessed() {
    this.demandService.getDemandProcessed().pipe(
      untilDestroyed(this)
    ).subscribe((res) => {
      console.log('res', res);
      this.demands.set(res as DemandAeaaCompletModel[]);
    });
  }

}
