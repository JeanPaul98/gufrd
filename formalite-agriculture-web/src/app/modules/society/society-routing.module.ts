import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListComponent} from "@ngxpert/cmdk";
import {CreateComponent} from "./pages/create/create.component";
import {SocietyComponent} from "./society.component";

const routes: Routes = [
  {
    path: '',
    component: SocietyComponent,
    data: {
      title: `Demdzandes d'inspection phytosanitaire de navire`
    },
    children: [
      {
        path: '',
        redirectTo: 'list',
        pathMatch: 'full'
      },
      {
        path: 'list',
        component: ListComponent,
        data: {
          title: 'Detazils dezmande'
        },
        title: 'Importation - Demande phssytosanitaire de Navire - Non soumises'
      },
      {
        path: 'create',
        component: CreateComponent,
        data: {
          title: 'Dssetails demande'
        },
        title: 'Importaqqqqqtion - Demande phytosanitaire de Navire - Non soumises'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SocietyRoutingModule { }
