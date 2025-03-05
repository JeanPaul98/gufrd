import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AipclRoutingModule } from './aipcl-routing.module';
import { AipclComponent } from './aipcl.component';
import {NgxSonnerToaster} from "ngx-sonner";


@NgModule({
  declarations: [
    AipclComponent
  ],
  imports: [
    CommonModule,
    AipclRoutingModule,
    NgxSonnerToaster
  ]
})
export class AipclModule { }
