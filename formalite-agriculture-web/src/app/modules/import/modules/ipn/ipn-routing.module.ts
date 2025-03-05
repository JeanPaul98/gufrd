import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'demand',
    pathMatch: 'full'
  },
  {
    path: '',
    data: {title: 'Home'},
    children: [
      {
        path: 'demand',
        loadChildren: () => import('./app/modules/demand/demand.module').then((m) => m.DemandModule)
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IpnRoutingModule {
}
