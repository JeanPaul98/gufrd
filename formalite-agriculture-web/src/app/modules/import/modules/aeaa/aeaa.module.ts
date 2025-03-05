import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AeaaRoutingModule } from './aeaa-routing.module';
import { AeaaComponent } from './aeaa.component';
import {RouterOutlet} from "@angular/router";
import {NgxSonnerToaster} from "ngx-sonner";


@NgModule({
  declarations: [
    AeaaComponent
  ],
  imports: [
    CommonModule,
    RouterOutlet,
    NgxSonnerToaster,
    AeaaRoutingModule
  ]
})
export class AeaaModule { }
