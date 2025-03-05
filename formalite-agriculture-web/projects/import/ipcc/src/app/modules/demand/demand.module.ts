import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {
  AccordionModule,
  ButtonModule,
  DropdownModule,
  FormModule,
  NavModule,
  SharedModule,
  TableModule,
  TabsModule,
  TooltipModule
} from '@coreui/angular';
import {NgSelectModule} from '@ng-select/ng-select';
import {PricePipe} from "../../../../../../../src/app/utils/pipes/price.pipe";
import {ConfirmModalComponent} from "../../../../../../../src/components/confirm-modal/confirm-modal.component";
import {GeneralModalComponent} from "../../../../../../../src/components/general-modal/general-modal.component";
import {ListShowFilesComponent} from "../../../../../../../src/components/list-show-files/list-show-files.component";
import {LoadingBarComponent} from "../../../../../../../src/components/loading-bar/loading-bar.component";
import {StepperComponent} from '../../../../../../../src/components/stepper/stepper.component';
import {DemandRoutingModule} from './demand-routing.module';
import {DemandComponent} from './demand.component';
import {AcceptedComponent} from './pages/accepted/accepted.component';
import {DetailsComponent} from "./pages/details/details.component";
import {NotSubmittedComponent} from './pages/not-submitted/not-submitted.component';
import {ProcessedComponent} from './pages/processed/processed.component';
import {RejectedComponent} from './pages/rejected/rejected.component';
import {SubmittedComponent} from './pages/submitted/submitted.component';


@NgModule({
  declarations: [
    DemandComponent,
    ProcessedComponent,
    RejectedComponent,
    AcceptedComponent,
    SubmittedComponent,
    NotSubmittedComponent,
    DetailsComponent
  ],
  imports: [
    CommonModule,
    DemandRoutingModule,
    ButtonModule,
    TabsModule,
    NavModule,
    TableModule,
    DropdownModule,
    AccordionModule,
    SharedModule,
    FormsModule,
    FormModule,
    NgSelectModule,
    FormsModule,
    TooltipModule,
    StepperComponent,
    ConfirmModalComponent,
    LoadingBarComponent,
    PricePipe,
    ListShowFilesComponent,
    GeneralModalComponent
  ]
})
export class DemandModule {
}
