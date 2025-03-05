import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AiemvRoutingModule } from './aiemv-routing.module';
import { AiemvComponent } from './aiemv.component';


@NgModule({
  declarations: [
    AiemvComponent
  ],
  imports: [
    CommonModule,
    AiemvRoutingModule
  ]
})
export class AiemvModule { }
