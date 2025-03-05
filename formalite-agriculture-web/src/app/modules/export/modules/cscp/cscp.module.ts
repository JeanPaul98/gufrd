import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CscpRoutingModule } from './cscp-routing.module';
import { CscpComponent } from './cscp.component';


@NgModule({
  declarations: [
    CscpComponent
  ],
  imports: [
    CommonModule,
    CscpRoutingModule
  ]
})
export class CscpModule { }
