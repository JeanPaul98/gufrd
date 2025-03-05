import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DemandRoutingModule} from './demand-routing.module';
import {DemandComponent} from './demand.component';
import {
  AccordionButtonDirective,
  AccordionComponent,
  AccordionItemComponent,
  ButtonDirective,
  DropdownComponent,
  DropdownItemDirective,
  DropdownMenuDirective,
  DropdownToggleDirective,
  FormControlDirective,
  FormSelectDirective,
  NavComponent,
  NavItemComponent,
  NavLinkDirective,
  TableDirective,
  TemplateIdDirective,
  TooltipDirective
} from "@coreui/angular";
import {NotSubmittedComponent} from './pages/not-submitted/not-submitted.component';
import {ProcessedComponent} from './pages/processed/processed.component';
import {AcceptedComponent} from './pages/accepted/accepted.component';
import {SubmittedComponent} from './pages/submitted/submitted.component';
import {RejectedComponent} from './pages/rejected/rejected.component';
import {ReactiveFormsModule} from "@angular/forms";
import {ConfirmModalComponent} from "../../../../../../../src/components/confirm-modal/confirm-modal.component";
import {DetailsComponent} from "./pages/details/details.component";
import {PricePipe} from "../../../../../../../src/app/utils/pipes/price.pipe";
import {LoadingBarComponent} from "../../../../../../../src/components/loading-bar/loading-bar.component";
import {ValidateComponent} from "./pages/validate/validate.component";
import {ListShowFilesComponent} from "../../../../../../../src/components/list-show-files/list-show-files.component";


@NgModule({
  declarations: [
    DemandComponent,
    NotSubmittedComponent,
    ProcessedComponent,
    AcceptedComponent,
    SubmittedComponent,
    RejectedComponent,
    DetailsComponent,
    ValidateComponent
  ],
  imports: [
    CommonModule,
    DemandRoutingModule,
    NavComponent,
    NavItemComponent,
    NavLinkDirective,
    ButtonDirective,
    DropdownComponent,
    DropdownItemDirective,
    DropdownMenuDirective,
    DropdownToggleDirective,
    TableDirective,
    AccordionComponent,
    AccordionItemComponent,
    TemplateIdDirective,
    AccordionButtonDirective,
    FormControlDirective,
    FormSelectDirective,
    ReactiveFormsModule,
    ConfirmModalComponent,
    PricePipe,
    LoadingBarComponent,
    TooltipDirective,
    ListShowFilesComponent,
  ]
})
export class DemandModule {
}
