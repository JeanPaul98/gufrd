import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdtRoutingModule } from './adt-routing.module';
import { AdtComponent } from './adt.component';
import {RouterOutlet} from "@angular/router";
import {NgxSonnerToaster} from "ngx-sonner";


@NgModule({
  declarations: [
    AdtComponent
  ],
  imports: [
    CommonModule,
    RouterOutlet,
    AdtRoutingModule,
    NgxSonnerToaster
  ]
})
export class AdtModule { }