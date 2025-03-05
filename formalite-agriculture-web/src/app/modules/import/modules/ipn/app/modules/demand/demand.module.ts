import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
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
import {ConfirmModalComponent} from "@docs-components/confirm-modal/confirm-modal.component";

import {GeneralModalComponent} from "@docs-components/general-modal/general-modal.component";
import {ListShowFilesComponent} from "@docs-components/list-show-files/list-show-files.component";
import {LoadingBarComponent} from "@docs-components/loading-bar/loading-bar.component";
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";
import {StepperComponent} from "@docs-components/stepper/stepper.component";
import {NgSelectModule} from '@ng-select/ng-select';
import {TippyTooltipDirective} from "../../../../../../../../directives/tippy-tooltip/tippy-tooltip.directive";
import {PricePipe} from "../../../../../../../utils/pipes/price.pipe";
import {DemandRoutingModule} from './demand-routing.module';
import {DemandComponent} from './demand.component';
import {AcceptedComponent} from './pages/accepted/accepted.component';
import {DetailsComponent} from "./pages/details/details.component";
import {ProcessedComponent} from './pages/processed/processed.component';
import {RejectedComponent} from './pages/rejected/rejected.component';
import {SubmittedComponent} from './pages/submitted/submitted.component';
import {ValidateComponent} from "./pages/validate/validate.component";


@NgModule({
  declarations: [
    DemandComponent,
    ProcessedComponent,
    RejectedComponent,
    AcceptedComponent,
    SubmittedComponent,
    DetailsComponent,
    ValidateComponent
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
    ConfirmModalComponent,
    NiceToastComponent,
    LoadingBarComponent,
    StepperComponent,
    ReactiveFormsModule,
    GeneralModalComponent,
    PricePipe,
    TippyTooltipDirective,
    ListShowFilesComponent,
  ]
})
export class DemandModule {
}
