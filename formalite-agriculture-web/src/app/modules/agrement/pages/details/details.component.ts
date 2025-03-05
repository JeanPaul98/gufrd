import {DatePipe, Location} from "@angular/common";
import {Component} from '@angular/core';
import {ButtonDirective, TableDirective} from "@coreui/angular";
import {ListShowFilesComponent} from "@docs-components/list-show-files/list-show-files.component";
import {DemandAgrementModel} from "../../../../models/agrement.model";
import {PricePipe} from "../../../../utils/pipes/price.pipe";
import {DemandService} from "../../../import/modules/adt/app/modules/demand/services/demand.service";

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [
    ButtonDirective,
    DatePipe,
    ListShowFilesComponent,
    PricePipe,
    TableDirective
  ],
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export class DetailsComponent {
  demand: DemandAgrementModel = JSON.parse(localStorage.getItem('demandDetail') as string);

  constructor(private demandService: DemandService, private location: Location) {
    // this.demand$ = this.demandService.currentDemandSubject.asObservable();
  }
  ngOnInit(): void {

  }

  onClickBack() {
    // this.currentStep = 1;
    // this.onClickPrevious()
    // localStorage.setItem('currentStepAEAA', JSON.stringify(this.currentStep))
    this.location.back();
  }
}
