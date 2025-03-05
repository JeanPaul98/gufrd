import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DemandComponent} from './demand.component';
import {DemandRoutingModule} from './demand-routing.module';
import {ProcessedComponent} from './pages/processed/processed.component';
import {RejectedComponent} from './pages/rejected/rejected.component';
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
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AcceptedComponent} from './pages/accepted/accepted.component';
import {SubmittedComponent} from './pages/submitted/submitted.component';
import {NgSelectModule} from '@ng-select/ng-select';
import {DetailsComponent} from "./pages/details/details.component";
import {ConfirmModalComponent} from "@docs-components/confirm-modal/confirm-modal.component";
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";
import {LoadingBarComponent} from "@docs-components/loading-bar/loading-bar.component";
import {StepperComponent} from "@docs-components/stepper/stepper.component";
import { GenererAutorisationComponent } from './pages/generer-autorisation/generer-autorisation.component';
import {ValidateComponent} from "./pages/validate/validate.component";
import {GeneralModalComponent} from "@docs-components/general-modal/general-modal.component";
import {TippyTooltipDirective} from "../../../../../../../../directives/tippy-tooltip/tippy-tooltip.directive";
import {PricePipe} from "../../../../../../../utils/pipes/price.pipe";
import {ListShowFilesComponent} from "@docs-components/list-show-files/list-show-files.component";


@NgModule({
  declarations: [
    DemandComponent,
    ProcessedComponent,
    RejectedComponent,
    AcceptedComponent,
    SubmittedComponent,
    DetailsComponent,
    GenererAutorisationComponent,
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
        GeneralModalComponent,
        ReactiveFormsModule,
        TippyTooltipDirective,
        PricePipe,
        ListShowFilesComponent
    ]
})
export class DemandModule {
}
