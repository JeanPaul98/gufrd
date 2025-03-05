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
import {PdfViewerModule} from "ng2-pdf-viewer";
import {PricePipe} from "../../../../../../../src/app/utils/pipes/price.pipe";
import {ConfirmModalComponent} from "../../../../../../../src/components/confirm-modal/confirm-modal.component";
import {GeneralModalComponent} from "../../../../../../../src/components/general-modal/general-modal.component";
import {ListShowFilesComponent} from "../../../../../../../src/components/list-show-files/list-show-files.component";
import {LoadingBarComponent} from "../../../../../../../src/components/loading-bar/loading-bar.component";
import {NiceToastComponent} from "../../../../../../../src/components/nice-toast/nice-toast.component";
import {StepperComponent} from "../../../../../../../src/components/stepper/stepper.component";
import {TippyTooltipDirective} from "../../../../../../../src/directives/tippy-tooltip/tippy-tooltip.directive";
import {DemandRoutingModule} from './demand-routing.module';
import {DemandComponent} from './demand.component';
import {AcceptedComponent} from './pages/accepted/accepted.component';
import {DetailsComponent} from './pages/details/details.component';
import {NotSubmittedComponent} from './pages/not-submitted/not-submitted.component';
import {ProcessedComponent} from './pages/processed/processed.component';
import {RejectedComponent} from './pages/rejected/rejected.component';
import {SubmittedComponent} from './pages/submitted/submitted.component';
import {ValidateComponent} from './pages/validate/validate.component';

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
        ListShowFilesComponent,
        PdfViewerModule,
        TippyTooltipDirective,
        GeneralModalComponent
    ]
})
export class DemandModule {
}
