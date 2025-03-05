import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ButtonDirective,
  FormControlDirective,
  TableDirective,
} from '@coreui/angular';
import {
  NgFooterTemplateDirective,
  NgOptionComponent,
  NgSelectComponent,
} from '@ng-select/ng-select';

import { DemandUploadFilesComponent } from '../../../../../../../../../src/components/demand-upload-files/demand-upload-files.component';
import { StepperComponent } from '../../../../../../../../../src/components/stepper/stepper.component';

import { CreateRoutingModule } from './create-routing.module';
import { CreateComponent } from './create.component';
import { Step01Component } from './pages/step-01/step-01.component';
import { Step02Component } from './pages/step-02/step-02.component';
import { Step03Component } from './pages/step-03/step-03.component';
import {
    ModalFileShowComponent
} from "../../../../../../../../../src/components/modal-file-show/modal-file-show.component";

@NgModule({
  declarations: [
    CreateComponent,
    Step01Component,
    Step02Component,
    Step03Component,
  ],
    imports: [
        CommonModule,
        CreateRoutingModule,
        ButtonDirective,
        StepperComponent,
        ReactiveFormsModule,
        FormControlDirective,
        TableDirective,
        NgSelectComponent,
        FormsModule,
        NgOptionComponent,
        NgFooterTemplateDirective,
        DemandUploadFilesComponent,
        ModalFileShowComponent,
    ],
})
export class CreateModule {}
