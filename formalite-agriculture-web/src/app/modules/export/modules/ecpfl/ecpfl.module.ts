import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EcpflRoutingModule } from './ecpfl-routing.module';
import { EcpflComponent } from './ecpfl.component';


@NgModule({
  declarations: [
    EcpflComponent
  ],
  imports: [
    CommonModule,
    EcpflRoutingModule
  ]
})
export class EcpflModule { }
