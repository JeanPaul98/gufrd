import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EditRoutingModule} from './edit-routing.module';
import {EditComponent} from './edit.component';
import {Step01Component} from "./pages/step-01/step-01.component";
import {Step02Component} from "./pages/step-02/step-02.component";
import {Step03Component} from "./pages/step-03/step-03.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ButtonDirective, FormControlDirective, TableDirective} from "@coreui/angular";
import {NgOptionComponent, NgSelectComponent} from "@ng-select/ng-select";
import {StepperComponent} from "../../../../../../../../../src/components/stepper/stepper.component";


@NgModule({
  declarations: [
    EditComponent,
    Step01Component,
    Step02Component,
    Step03Component
  ],
  imports: [
    CommonModule,
    EditRoutingModule,
    ReactiveFormsModule,
    TableDirective,
    FormControlDirective,
    ButtonDirective,
    NgSelectComponent,
    FormsModule,
    NgOptionComponent,
    StepperComponent
  ]
})
export class EditModule {
}
