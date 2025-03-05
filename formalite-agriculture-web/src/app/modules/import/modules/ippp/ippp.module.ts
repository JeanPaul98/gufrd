import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



import {NgxSonnerToaster} from "ngx-sonner";
import {IpppComponent} from "./ippp.component";
import {IpppRoutingModule} from "./ippp-routing.module";



@NgModule({
  declarations: [
    IpppComponent,
  ],
  imports: [
    CommonModule,
    IpppRoutingModule,
    NgxSonnerToaster
  ]
})
export class IpppModule { }
