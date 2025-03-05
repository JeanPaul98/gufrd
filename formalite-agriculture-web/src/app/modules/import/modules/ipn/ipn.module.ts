import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {IpnComponent} from "./ipn.component";
import {IpnRoutingModule} from "./ipn-routing.module";
import {NgxSonnerToaster} from "ngx-sonner";

@NgModule({
  declarations: [
    IpnComponent
  ],
  imports: [
    CommonModule,
    IpnRoutingModule,
    NgxSonnerToaster
  ]
})
export class IpnModule {
}
