import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IocavRoutingModule } from './iocav-routing.module';
import { IocavComponent } from './iocav.component';


@NgModule({
  declarations: [
    IocavComponent
  ],
  imports: [
    CommonModule,
    IocavRoutingModule
  ]
})
export class IocavModule { }
