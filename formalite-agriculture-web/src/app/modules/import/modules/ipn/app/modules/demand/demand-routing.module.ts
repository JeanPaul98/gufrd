import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DemandComponent } from './demand.component';
import { RejectedComponent } from './pages/rejected/rejected.component';
import { ProcessedComponent } from './pages/processed/processed.component';

import {DetailsComponent} from "./pages/details/details.component";
import {SubmittedComponent} from "./pages/submitted/submitted.component";
import {AcceptedComponent} from "./pages/accepted/accepted.component";
import {ValidateComponent} from "./pages/validate/validate.component";


const routes: Routes = [
  {
    path: '',
    component: DemandComponent,
    data: {
      title: `Demande d'inspection phytosanitaire de navire`
    },
    children: [
      {
        path: '',
        redirectTo: 'submitted',
        pathMatch: 'full'
      },
      {
        path: 'details/:id',
        component: DetailsComponent,
        data: {
          title: 'Details demande'
        },
        title: 'Importation - Détails demande phytosanitaire de navire'
      },
      {
        path: '',
        data: {
          title: 'Create'
        },
        title: 'Importation - Creation Pv pour demande phytosanitaire de navire',
        children: [
          {
            path: 'create-pv',
            loadChildren: () => import('./pages/create-pv/create-pv.module').then((m) => m.CreatePvModule)
          }
        ]
      },
      {
        path: 'submitted',
        component: SubmittedComponent,
        data: {
          title: 'Submitted'
        },
        title: 'Importation - Demande d\inspection phytosanitaire de navire - Soumise'
      },
      {
        path: 'accepted',
        component: AcceptedComponent,
        data: {
          title: 'Accepted'
        },
        title: 'Importation - Demande d\inspection phytosanitaire de navire - Acceptées'
      },
      {
        path: 'processed',
        component: ProcessedComponent,
        data: {
          title: 'Processed'
        },
        title: 'Importation - Demande d\inspection phytosanitaire de navire - Traitée'
      },
      {
        path: 'rejected',
        component: RejectedComponent,
        data: {
          title: 'Rejected'
        },
        title: 'Importation - Demande d\inspection phytosanitaire de navire - Rejetée'
      },
      {
        path: 'validate',
        component: ValidateComponent,
        data: {
          title: 'Validate'
        },
        title: 'Importation - Demande d\inspection phytosanitaire de navire - Validation'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DemandRoutingModule {}
