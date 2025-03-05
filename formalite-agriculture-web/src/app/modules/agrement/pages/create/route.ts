import {Routes} from '@angular/router';
import {CreateComponent} from "./create.component";

export const routes: Routes = [
  {
    path: '',
    component: CreateComponent,
    data: {
      title: `Creation Agrement`
    },
    children: [
      {
        path: '',
        redirectTo: 'step1',
        pathMatch: 'full'
      },
      {
        path: 'step1',
        loadComponent: () => import('./step1/step1.component').then(m => m.Step1Component),
        data: {
          title: `Etape 1`
        }
      },
      {
        path: 'step2',
        loadComponent: () => import('./step2/step2.component').then(m => m.Step2Component),
        data: {
          title: `Etape 2`
        }
      },
    ]
  },

];

