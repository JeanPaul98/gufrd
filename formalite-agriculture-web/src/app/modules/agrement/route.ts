import {Routes} from '@angular/router';

export const routes: Routes = [
      {
        path: '',
        redirectTo: 'list',
        pathMatch: 'full'
      },
      {
        path: 'list',
        loadChildren: () => import('./pages/list/route').then(m => m.routes),
      },
      {
        path: 'create',
        loadChildren: () => import('./pages/create/route').then(m => m.routes),
      },
      {
        path: 'details/:id',
        loadComponent: () => import('./pages/details/details.component').then(m => m.DetailsComponent),
        data: {
          title: `Details demande`
        },
        title: 'Importation - Demande phytosanitaire de Navire - Non soumises'
      }


];

