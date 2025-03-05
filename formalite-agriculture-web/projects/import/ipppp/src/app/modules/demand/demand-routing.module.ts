import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DemandComponent} from "./demand.component";
import {NotSubmittedComponent} from "./pages/not-submitted/not-submitted.component";
import {SubmittedComponent} from "./pages/submitted/submitted.component";
import {AcceptedComponent} from "./pages/accepted/accepted.component";
import {ProcessedComponent} from "./pages/processed/processed.component";
import {RejectedComponent} from "./pages/rejected/rejected.component";
import {DetailsComponent} from "./pages/details/details.component";
import {ValidateComponent} from "./pages/validate/validate.component";

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
        data: {title: 'Not-submitted'},
        title: 'Importation - Demandes non soumises des inspections phytosanitaire Produits pharmaceutiques '
      },
      {
        path: 'create',
        data: {title: 'Create'},
        title: 'Importation - Demande Inspection phytosanitaire de produits phyto pharmaceutiques',
        loadChildren: () => import('./modules/create/create.module').then(m => m.CreateModule)
      },
      {
        path: 'submitted',
        component: SubmittedComponent,
        data: {
          title: 'Submitted'
        },
        title: 'Importation - Demandes soumises des inspections phytosanitaire des Produits pharmaceutiques '
      },
      {
        path: 'accepted',
        component: AcceptedComponent,
        data: {
          title: 'Accepted'
        },
        title: 'Importation - Demandes acceptées des inspections phytosanitaire des Produits pharmaceutiques'
      },
      {
        path: 'processed',
        component: ProcessedComponent,
        data: {
          title: 'Processed'
        },
        title: 'Importation - Demandes traitées des inspections phytosanitaire des Produits pharmaceutiques'
      },
      {
        path: 'rejected',
        component: RejectedComponent,
        data: {
          title: 'Rejected'
        },
        title: 'Importation - Demandes rejetées des inspections phytosanitaire des Produits pharmaceutiques'
      },
      {
        path: 'details/:id',
        component: DetailsComponent,
        data: {title: 'Details'},
        title: 'Importation - Détails de la demande des inspections phytosanitaire des produits pharmaceutiques'
      },
      {
        path: 'validate/:id',
        component: ValidateComponent,
        data: {title: 'Validation'},
        title: 'Importation - Validation de la demande des inspections phytosanitaire des produits pharmaceutiques'
      },
      {
        path: 'edit',
        data: {title: 'Modifier'},
        title: 'Importation - Modifier demandes des inspections phytosanitaire des Produits pharmaceutiques',
        loadChildren: () => import('./modules/edit/edit.module').then((m) => m.EditModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DemandRoutingModule {
}
