import {Routes} from '@angular/router';
import {RolesEnum} from "./enums/roles.enum";
import {DefaultLayoutComponent} from './layout';

export const routes: Routes = [
  {path: '', redirectTo: 'importations', pathMatch: 'full'},
  {
    path: '',
    component: DefaultLayoutComponent,
    data: {title: 'Home'},
    children: [
      // Tableau de board de l'application
      // {
      //   path: 'dashboard',
      //   loadChildren: () => import('./pages/dashboard/routes').then((m) => m.routes)
      // },
      // Modules Formalités d'importations
      {
        path: 'society',
        loadChildren: () => import('./modules/society/routes').then(m => m.routes),
      },
      {
        path: 'agrement',
        loadChildren: () => import('./modules/agrement/route').then(m => m.routes),
      },
      {

        path: 'importations',
        loadChildren: () => import('./modules/import/import.module').then((m) => m.ImportModule),
        // canActivate: [KCAuthGuard],
        data: {roles : [RolesEnum.MANAGER, RolesEnum.INSPECTEUR_PHYTOSANITAIRE, RolesEnum.INSPECTEUR_VETERINAIRE]}
      },
      // Modules Formalités d'exportations
      {
        path: 'exportations',
        loadChildren: () => import('./modules/export/export.module').then((m) => m.ExportModule),
        // canActivate: [KCAuthGuard],
        data: {roles : [RolesEnum.MANAGER,RolesEnum.INSPECTEUR_VETERINAIRE]}
      },
    ],
  },
  {
    path: '404',
    loadComponent: () => import('./pages/page404/page404.component').then(m => m.Page404Component),
    data: {title: 'Page 404'}
  },
  {
    path: '500',
    loadComponent: () => import('./pages/page500/page500.component').then(m => m.Page500Component),
    data: {title: 'Page 500'}
  },
  {path: '**', redirectTo: '404', pathMatch: 'full'},
];
