import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AiavRoutingModule } from './aiav-routing.module';
import { AiavComponent } from './aiav.component';

import {
    ButtonDirective,
    NavComponent,
    NavItemComponent,
    NavLinkDirective,
    TableDirective,
    TooltipDirective
} from "@coreui/angular";
import {LoadingBarComponent} from "@docs-components/loading-bar/loading-bar.component";
import {PricePipe} from "../../../../utils/pipes/price.pipe";
import {ConfirmModalComponent} from "@docs-components/confirm-modal/confirm-modal.component";


@NgModule({
  declarations: [
    AiavComponent,

  ],
  imports: [
    CommonModule,
    AiavRoutingModule,
    NavComponent,
    NavItemComponent,
    NavLinkDirective,
    ButtonDirective,
    LoadingBarComponent,
    TableDirective,
    PricePipe,
    TooltipDirective,
    ConfirmModalComponent
  ]
})
export class AiavModule { }
