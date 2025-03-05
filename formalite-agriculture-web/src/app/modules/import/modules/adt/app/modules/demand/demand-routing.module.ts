import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DemandComponent} from './demand.component';
import {AcceptedComponent} from "./pages/accepted/accepted.component";

import {DetailsComponent} from "./pages/details/details.component";
import {GenererAutorisationComponent} from "./pages/generer-autorisation/generer-autorisation.component";
import {ProcessedComponent} from './pages/processed/processed.component';
import {RejectedComponent} from './pages/rejected/rejected.component';
import {SubmittedComponent} from "./pages/submitted/submitted.component";
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
        redirectTo: 'submitted',
        pathMatch: 'full'
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
        path: 'details/generer-autorisation',
        component: GenererAutorisationComponent,
        data: {
          title: 'Generer autorisation'
        },
        title: 'Importation - Générer autorisation d\'enlèvement d\'aliment pour animaux vivants'
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
      {
        path: 'validate',
        component: ValidateComponent,
        data: {
          title: 'Validate'
        },
        title: 'Importation - Demande phytosanitaire de Navire - Validée'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DemandRoutingModule {}
