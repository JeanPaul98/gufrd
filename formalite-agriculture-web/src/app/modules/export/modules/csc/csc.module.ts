import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CscRoutingModule } from './csc-routing.module';
import { CscComponent } from './csc.component';
import {RouterOutlet} from "@angular/router";
import {NgxSonnerToaster} from "ngx-sonner";


@NgModule({
  declarations: [
    CscComponent
  ],
  imports: [
    CommonModule,
    RouterOutlet,
    NgxSonnerToaster,
    CscRoutingModule
  ]
})
export class CscModule { }
