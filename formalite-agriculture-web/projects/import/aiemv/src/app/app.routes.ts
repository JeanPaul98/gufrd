import { Routes } from '@angular/router';
import {DefaultLayoutComponent} from "./layout";

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'demand',
    pathMatch: 'full'
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    data: {title: 'Home'},
    children: [
      {
        path: 'demand',
        loadChildren: () => import('./modules/demand/demand.module').then((m) => m.DemandModule)
      } 
    ]
  },
];
