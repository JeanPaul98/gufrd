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
import {FormsModule} from '@angular/forms';
import {AcceptedComponent} from './pages/accepted/accepted.component';
import {SubmittedComponent} from './pages/submitted/submitted.component';
import {NotSubmittedComponent} from './pages/not-submitted/not-submitted.component';
import {NgSelectModule} from '@ng-select/ng-select';
import {ConfirmModalComponent} from "../../../../../../../src/components/confirm-modal/confirm-modal.component";
import {NiceToastComponent} from "../../../../../../../src/components/nice-toast/nice-toast.component";
import {LoadingBarComponent} from "../../../../../../../src/components/loading-bar/loading-bar.component";
import { DetailsComponent } from './pages/details/details.component';
import { EditComponent } from './pages/edit/edit.component';
import {StepperComponent} from "../../../../../../../src/components/stepper/stepper.component";
import {PricePipe} from "../../../../../../../src/app/utils/pipes/price.pipe";
import { ValidateComponent } from './pages/validate/validate.component';


@NgModule({
  declarations: [
    DemandComponent,
    ProcessedComponent,
    RejectedComponent,
    AcceptedComponent,
    SubmittedComponent,
    NotSubmittedComponent,
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
        PricePipe,
    ]
})
export class DemandModule {
}
