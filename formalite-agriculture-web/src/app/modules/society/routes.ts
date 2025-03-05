import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'list',
    pathMatch: 'full'
  },
  {
    path: 'list',
    loadComponent: () => import('./pages/list/list.component').then(m => m.ListComponent),
    data: {
      title: `Liste Socitey`
    }
  },
  {
    path: 'create',
    loadComponent: () => import('./pages/create/create.component').then(m => m.CreateComponent),
    data: {
      title: `Create Socitey`
    }
  }
];

