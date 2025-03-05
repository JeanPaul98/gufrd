import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {SocietyRoutingModule} from './society-routing.module';

import {SocietyComponent} from './society.component';


@NgModule({
  declarations: [
    SocietyComponent
  ],
  imports: [
    CommonModule,
    SocietyRoutingModule
  ]
})
export class SocietyModule { }
