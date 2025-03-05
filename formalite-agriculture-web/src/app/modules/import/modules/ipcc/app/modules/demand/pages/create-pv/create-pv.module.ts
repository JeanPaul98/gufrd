import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CreatePvRoutingModule} from './create-pv-routing.module';
import {CreatePvComponent} from './create-pv.component';
import {Step01Component} from './step01/step01.component';
import {Step02Component} from './step02/step02.component';
import {Step03Component} from './step03/step03.component';
import {
    ButtonDirective, ContainerComponent,
    FormControlDirective,
    FormDirective,
    FormLabelDirective,
    TableDirective
} from "@coreui/angular";
import {StepperComponent} from "../../../../../../../../../../components/stepper/stepper.component";
import {InfoTooltipComponent} from "@docs-components/info-tooltip/info-tooltip.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {GeneralModalComponent} from "@docs-components/general-modal/general-modal.component";
import {NgOptionComponent, NgSelectComponent} from "@ng-select/ng-select";
import { Step04Component } from './step04/step04.component';
import { Step05Component } from './step05/step05.component';


@NgModule({
  declarations: [
    CreatePvComponent,
    Step01Component,
    Step02Component,
    Step03Component,
    Step04Component,
    Step05Component
  ],
    imports: [
        CommonModule,
        CreatePvRoutingModule,
        ButtonDirective,
        StepperComponent,
        FormControlDirective,
        InfoTooltipComponent,
        ReactiveFormsModule,
        FormsModule,
        FormDirective,
        FormLabelDirective,
        GeneralModalComponent,
        NgOptionComponent,
        NgSelectComponent,
        TableDirective,
        ContainerComponent
    ]
})
export class CreatePvModule {
}
