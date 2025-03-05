import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DemandComponent } from './demand.component';
import { RejectedComponent } from './pages/rejected/rejected.component';
import { ProcessedComponent } from './pages/processed/processed.component';

import {DetailsComponent} from "./pages/details/details.component";
import {SubmittedComponent} from "./pages/submitted/submitted.component";
import {AcceptedComponent} from "./pages/accepted/accepted.component";


const routes: Routes = [
  {
    path: '',
    component: DemandComponent,
    data: {
      title: `Demande Inspection phytosanitaire de produits phyto pharmaceutiques`
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
        title: 'Importation - Détails Inspection phytosanitaire de produits phyto pharmaceutiques'
      },
      {
        path: '',
        data: {
          title: 'Create'
        },
        title: 'Importation - Creation Pv pour Inspection phytosanitaire de produits phyto pharmaceutiques',
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
        title: 'Importation - Inspection phytosanitaire de produits phyto pharmaceutiques - Soumise'
      },
      {
        path: 'accepted',
        component: AcceptedComponent,
        data: {
          title: 'Accepted'
        },
        title: 'Importation - Inspection phytosanitaire de produits phyto pharmaceutiques - Acceptées'
      },
      {
        path: 'processed',
        component: ProcessedComponent,
        data: {
          title: 'Processed'
        },
        title: 'Importation - Inspection phytosanitaire de produits phyto pharmaceutiques - Traitée'
      },
      {
        path: 'rejected',
        component: RejectedComponent,
        data: {
          title: 'Rejected'
        },
        title: 'Importation - Inspection phytosanitaire de produits phyto pharmaceutiques - Rejetée'
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DemandRoutingModule {}
