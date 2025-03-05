import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IpccRoutingModule } from './ipcc-routing.module';
import { IpccComponent } from './ipcc.component';
import {RouterOutlet} from "@angular/router";
import {NgxSonnerToaster} from "ngx-sonner";

@NgModule({
  declarations: [
    IpccComponent
  ],
  imports: [
    CommonModule,
    IpccRoutingModule,
    RouterOutlet,
    NgxSonnerToaster
  ]
})
export class IpccModule { }