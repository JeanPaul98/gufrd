import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DemandComponent } from './demand.component';
import { RejectedComponent } from './pages/rejected/rejected.component';
import { ProcessedComponent } from './pages/processed/processed.component';
import { NotSubmittedComponent } from './pages/not-submitted/not-submitted.component';
import { SubmittedComponent } from './pages/submitted/submitted.component';
import { AcceptedComponent } from './pages/accepted/accepted.component';
import {DetailsComponent} from "./pages/details/details.component";
import {ValidateComponent} from "./pages/validate/validate.component";


const routes: Routes = [
  {
    path: '',
    component: DemandComponent,
    data: {
      title: `Demandes d'inspection phytosanitaire de navire`
    },
    children: [
      {
        path: '',
        redirectTo: 'not-submitted',
        pathMatch: 'full'
      },
      {
        path: 'not-submitted',
        component: NotSubmittedComponent,
        data: {
          title: 'Not submitted'
        },
        title: 'Importation - Demande phytosanitaire de Navire - Non soumises'
      },
      {
        path: 'details/:id',
        component: DetailsComponent,
        data: {
          title: 'Details demande'
        },
        title: 'Importation - Demande phytosanitaire de Navire - Non soumises'
      },
      {
        path: 'validate/:id',
        component: ValidateComponent,
        data: {
          title: 'Valider demande'
        },
        title: 'Importation - Valider Demande phytosanitaire de Navire - Non soumises'
      },
      {
        path: '',
        data: {
          title: 'Create'
        },
        title: 'Importation - Nouvellle Demande phytosanitaire de Navire',
        children: [
          {
            path: 'create',
            loadChildren: () => import('./pages/create/create.module').then((m) => m.CreateModule)
          }
        ]
      },
      {
        path: '',
        data: {
          title: 'Modifier'
        },
        title: 'Importation - Modifier Demande phytosanitaire de Navire',
        children: [
          {
            path: 'edit',
            loadChildren: () => import('./pages/edit/edit.module').then((m) => m.EditModule)
          }
        ]
      },
      {
        path: 'submitted',
        component: SubmittedComponent,
        data: {
          title: 'Submitted'
        },
        title: 'Importation - Demande phytosanitaire de Navire - Soumises'
      },
      {
        path: 'accepted',
        component: AcceptedComponent,
        data: {
          title: 'Accepted'
        },
        title: 'Importation - Demande phytosanitaire de Navire - Acceptées'
      },
      {
        path: 'processed',
        component: ProcessedComponent,
        data: {
          title: 'Processed'
        },
        title: 'Importation - Demande phytosanitaire de Navire - Traitée'
      },
      {
        path: 'rejected',
        component: RejectedComponent,
        data: {
          title: 'Rejected'
        },
        title: 'Importation - Demande phytosanitaire de Navire - Rejetée'
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DemandRoutingModule {}
