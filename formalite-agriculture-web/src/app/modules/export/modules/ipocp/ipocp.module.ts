import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IpocpRoutingModule } from './ipocp-routing.module';
import { IpocpComponent } from './ipocp.component';


@NgModule({
  declarations: [
    IpocpComponent
  ],
  imports: [
    CommonModule,
    IpocpRoutingModule
  ]
})
export class IpocpModule { }
