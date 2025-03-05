import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CrbnRoutingModule } from './crbn-routing.module';
import { CrbnComponent } from './crbn.component';


@NgModule({
  declarations: [
    CrbnComponent
  ],
  imports: [
    CommonModule,
    CrbnRoutingModule
  ]
})
export class CrbnModule { }
