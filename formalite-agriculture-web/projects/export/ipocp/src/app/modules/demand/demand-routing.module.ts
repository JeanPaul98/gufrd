import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DemandComponent } from './demand.component';
import { RejectedComponent } from './pages/rejected/rejected.component';
import { CreateComponent } from './pages/create/create.component';
import { ProcessedComponent } from './pages/processed/processed.component';
import { NotSubmittedComponent } from './pages/not-submitted/not-submitted.component';
import { SubmittedComponent } from './pages/submitted/submitted.component';
import { AcceptedComponent } from './pages/accepted/accepted.component';


const routes: Routes = [
  {
    path: '',
    component: DemandComponent,
    data: {
      title: `Demande d'inspection phytosanitaire pour l’obtention de certificat phytosanitaire`
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
        title: `Importation - Demande d'inspection phytosanitaire pour l’obtention de certificat phytosanitaire - Non soumises`
      },
      {
        path: 'create',
        component: CreateComponent,
        data: {
          title: 'Create'
        },
        title: `Importation - Nouvellle Demande d'inspection phytosanitaire pour l’obtention de certificat phytosanitaire`
      },
      {
        path: 'submitted',
        component: SubmittedComponent,
        data: {
          title: 'Submitted'
        },
        title: `Importation - Demande d'inspection phytosanitaire pour l’obtention de certificat phytosanitaire - Soumises`
      },
      {
        path: 'accepted',
        component: AcceptedComponent,
        data: {
          title: 'Accepted'
        },
        title: `Importation - Demande d'inspection phytosanitaire pour l’obtention de certificat phytosanitaire - Acceptées`
      },
      {
        path: 'processed',
        component: ProcessedComponent,
        data: {
          title: 'Processed'
        },
        title: `Importation - Demande d'inspection phytosanitaire pour l’obtention de certificat phytosanitaire - Traitée`
      },
      {
        path: 'rejected',
        component: RejectedComponent,
        data: {
          title: 'Rejected'
        },
        title: `Importation - Demande d'inspection phytosanitaire pour l’obtention de certificat phytosanitaire - Rejetée`
      },  
    ] 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DemandRoutingModule {}
