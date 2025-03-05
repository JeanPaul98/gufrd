import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CreateRoutingModule} from './create-routing.module';
import {Step01Component} from './step-01/step-01.component';
import {Step02Component} from './step-02/step-02.component';
import {Step03Component} from './step-03/step-03.component';
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
import {NgSelectModule} from '@ng-select/ng-select';
import {MatDialogModule} from '@angular/material/dialog';
import {StepperComponent} from '../../../../../../../../../src/components/stepper/stepper.component';
import {CreateComponent} from './create.component';
import {Step04Component} from './step-04/step-04.component';
import {GeneralModalComponent} from "../../../../../../../../../src/components/general-modal/general-modal.component";
import {
  DemandUploadFilesComponent
} from "../../../../../../../../../src/components/demand-upload-files/demand-upload-files.component";

@NgModule({
  declarations: [
    Step01Component,
    Step02Component,
    Step03Component,
    CreateComponent,
    Step04Component
  ],
  imports: [
    CommonModule,
    CreateRoutingModule,
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
    DemandUploadFilesComponent
  ]
})
export class CreateModule {
}
