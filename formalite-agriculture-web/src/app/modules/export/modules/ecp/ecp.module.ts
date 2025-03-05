import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EcpRoutingModule } from './ecp-routing.module';
import { EcpComponent } from './ecp.component';


@NgModule({
  declarations: [
    EcpComponent
  ],
  imports: [
    CommonModule,
    EcpRoutingModule
  ]
})
export class EcpModule { }
