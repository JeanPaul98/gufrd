import {DatePipe} from "@angular/common";
import {Component, inject, signal, WritableSignal} from '@angular/core';
import {RouterLink} from "@angular/router";
import {ButtonDirective, TableDirective} from "@coreui/angular";
import {LoadingBarComponent} from "@docs-components/loading-bar/loading-bar.component";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {finalize} from "rxjs";
import {TippyTooltipDirective} from "../../../../../../directives/tippy-tooltip/tippy-tooltip.directive";
import {DemandAgrementModel} from "../../../../../models/agrement.model";
import {PricePipe} from "../../../../../utils/pipes/price.pipe";
import {AgrementService} from "../../../services/agrement.service";

@UntilDestroy()
@Component({
  selector: 'app-processed',
  standalone: true,
  imports: [
    LoadingBarComponent,
    RouterLink,
    PricePipe,
    DatePipe,
    TippyTooltipDirective,
    ButtonDirective,
    TableDirective
  ],
  templateUrl: './processed.component.html',
  styleUrl: './processed.component.scss'
})
export class ProcessedComponent {
  demands: WritableSignal<DemandAgrementModel[]> = signal<DemandAgrementModel[]>([]);
  demand!: DemandAgrementModel;
  loadingDemands: boolean = false;
  modalVisible: boolean = false;
  loadingSubmit: boolean = false;

  agrementSerice =  inject(AgrementService);

  ngOnInit(): void {
    this.getDemandProcessed();
  }

  openModal(demand: DemandAgrementModel) {

    this.demand = demand;
    this.modalVisible = true;
  }

  xendDetailsInfo(demand: DemandAgrementModel) {
    // this.demandService.currentDemandSubject.next(demand);
    // localStorage.setItem('demandDetail', JSON.stringify(demand));

  }

  getDemandProcessed() {
    this.loadingDemands = true;
    this.agrementSerice.getDemandProcessed().pipe(
      untilDestroyed(this),
      finalize(() => {
        if(this.demands() !== undefined) {
          this.loadingDemands = false;
          // this.agrementSerice.currentDemandsSubject.next(this.demands());
        }
      })
    ).subscribe((res) => {
      this.loadingDemands = false;
      console.log('res', res);
      this.demands.set(res as DemandAgrementModel[]);
    });
  }


  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }

  onSubmit() {

  }
}
