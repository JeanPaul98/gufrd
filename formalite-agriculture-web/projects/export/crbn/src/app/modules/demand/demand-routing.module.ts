import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DemandComponent} from "./demand.component";
import {NotSubmittedComponent} from "./pages/not-submitted/not-submitted.component";
import {CreateComponent} from "./pages/create/create.component";
import {SubmittedComponent} from "./pages/submitted/submitted.component";
import {AcceptedComponent} from "./pages/accepted/accepted.component";
import {ProcessedComponent} from "./pages/processed/processed.component";
import {RejectedComponent} from "./pages/rejected/rejected.component";

const routes: Routes = [
  {
    path: '',
    component: DemandComponent,
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
          title: 'Not-submitted'
        },
        title: 'Exportation - Demandes non soumises des contrôles de ravitaillement à bord des navires'
      },
      {
        path: 'create',
        component: CreateComponent,
        data: {
          title: 'Create'
        },
        title: 'Exportation - Demande contrôle de ravitaillement à bord des navires'
      },
      {
        path: 'submitted',
        component: SubmittedComponent,
        data: {
          title: 'Submitted'
        },
        title: 'Exportation - Demandes soumises des contrôles de ravitaillement à bord des navires'
      },
      {
        path: 'accepted',
        component: AcceptedComponent,
        data: {
          title: 'Accepted'
        },
        title: 'Exportation - Demandes acceptées des contrôles de ravitaillement à bord des navires'
      },
      {
        path: 'processed',
        component: ProcessedComponent,
        data: {
          title: 'Processed'
        },
        title: 'Exportation - Demandes traitées des contrôles de ravitaillement à bord des navires'
      },
      {
        path: 'rejected',
        component: RejectedComponent,
        data: {
          title: 'Rejected'
        },
        title: 'Exportation - Demandes rejetées des contrôles de ravitaillement à bord des navires'
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DemandRoutingModule { }
