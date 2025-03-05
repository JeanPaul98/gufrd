import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DemandComponent} from './demand.component';
import {AcceptedComponent} from "./pages/accepted/accepted.component";

import {DetailsComponent} from "./pages/details/details.component";
import {ProcessedComponent} from './pages/processed/processed.component';
import {RejectedComponent} from './pages/rejected/rejected.component';
import {SubmittedComponent} from "./pages/submitted/submitted.component";
import {ValidateComponent} from "./pages/validate/validate.component";


const routes: Routes = [
  {
    path: '',
    component: DemandComponent,
    data: {
      title: `Demande d'inspection phytosanitaire de container et cargaison`
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
        title: 'Importation - Details demande d\'inspection phytosanitaire de container et cargaison'
      },
      {
        path: '',
        data: {
          title: 'Create'
        },
        title: 'Importation - Creation Pv pour demande phytosanitaire de container et cargaison',
        children: [
          {
            path: 'create-pv',
            loadChildren: () => import('./pages/create-pvv/create-pv.module').then((m) => m.CreatePvModule)
          }
        ]
      },
      {
        path: 'submitted',
        component: SubmittedComponent,
        data: {
          title: 'Submitted'
        },
        title: 'Importation - Demande d\inspection phytosanitaire de container et cargaison - Soumise'
      },
      {
        path: 'accepted',
        component: AcceptedComponent,
        data: {
          title: 'Accepted'
        },
        title: 'Importation - Demande d\inspection phytosanitaire de container et cargaison - Acceptées'
      },
      {
        path: 'processed',
        component: ProcessedComponent,
        data: {
          title: 'Processed'
        },
        title: 'Importation - Demande d\inspection phytosanitaire de container et cargaison - Traitée'
      },
      {
        path: 'rejected',
        component: RejectedComponent,
        data: {
          title: 'Rejected'
        },
        title: 'Importation - Demande d\inspection phytosanitaire de container et cargaison - Rejetée'
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
