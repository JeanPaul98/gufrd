import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";
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
} from "@coreui/angular";
import {NgSelectModule} from "@ng-select/ng-select";
import {
  DemandUploadFilesComponent
} from "../../../../../../../../../src/components/demand-upload-files/demand-upload-files.component";
import {GeneralModalComponent} from "../../../../../../../../../src/components/general-modal/general-modal.component";
import {InfoTooltipComponent} from "../../../../../../../../../src/components/info-tooltip/info-tooltip.component";
import {
  ModalFileShowComponent
} from "../../../../../../../../../src/components/modal-file-show/modal-file-show.component";
import {StepperComponent} from "../../../../../../../../../src/components/stepper/stepper.component";

import {EditRoutingModule} from './edit-routing.module';
import {EditComponent} from "./edit.component";
import {Step01Component} from "./step-01/step-01.component";
import {Step02Component} from "./step-02/step-02.component";
import {Step03Component} from "./step-03/step-03.component";
import {Step04Component} from "./step-04/step-04.component";


@NgModule({
  declarations: [
    Step01Component,
    Step02Component,
    Step03Component,
    Step04Component,
    EditComponent
  ],
  imports: [
    CommonModule,
    EditRoutingModule,
    ButtonModule,
    TabsModule,
    NavModule,
    TableModule,
    DropdownModule,
    AccordionModule,
    SharedModule,
    FormsModule,
    FormModule,
    GeneralModalComponent,
    NgSelectModule,
    FormsModule,
    TooltipModule,
    MatDialogModule,
    ReactiveFormsModule,
    FormModule,
    StepperComponent,
    InfoTooltipComponent,
    DemandUploadFilesComponent,
    ModalFileShowComponent
  ]
})
export class EditModule { }
