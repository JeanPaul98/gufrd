import {Routes} from '@angular/router';
import {ListComponent} from "./list.component";

export const routes: Routes = [
  {
    path: '',
    component: ListComponent,
    data: {
      title: `List Agrement`
    },
    children: [
      {
        path: '',
        redirectTo: 'submitted',
        pathMatch: 'full'
      },
      {
        path: 'accepted',
        loadComponent: () => import('./accepted/accepted.component').then(m => m.AcceptedComponent),
        data: {
          title: `Accepted`
        }
      },
      {
        path: 'submitted',
        loadComponent: () => import('./submitted/submitted.component').then(m => m.SubmittedComponent),
        data: {
          title: `Submitted`
        }
      },
      {
        path: 'not-submitted',
        loadComponent: () => import('./not-submitted/not-submitted.component').then(m => m.NotSubmittedComponent),
        data: {
          title: `Not Submitted`
        }
      },
      {
        path: 'rejected',
        loadComponent: () => import('./rejected/rejected.component').then(m => m.RejectedComponent),
        data: {
          title: `Rejected`
        }
      },
      {
        path: 'processed',
        loadComponent: () => import('./processed/processed.component').then(m => m.ProcessedComponent),
        data: {
          title: `Processed`
        }
      }
    ]
  },

];

