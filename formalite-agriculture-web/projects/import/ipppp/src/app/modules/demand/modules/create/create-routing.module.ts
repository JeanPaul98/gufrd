import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CreateComponent} from "./create.component";
import {Step01Component} from "./pages/step-01/step-01.component";
import {Step02Component} from "./pages/step-02/step-02.component";
import {Step03Component} from "./pages/step-03/step-03.component";
import {Step04Component} from "./pages/step-04/step-04.component";

const routes: Routes = [
  {
    path: '',
    component: CreateComponent,
    data: {title: 'Importation - Demande Inspection phytosanitaire de produits phyto pharmaceutiques'},
    children: [
      {
        path: '',
        redirectTo: 'step1',
        pathMatch: 'full'
      },
      {
        path: 'step1',
        data: {title: 'Etape 1'},
        component: Step01Component,
        title: 'Information Navires'
      },
      {
        path: 'step2',
        data: {title: 'Etape 2'},
        component: Step02Component,
        title: 'Information Produits'
      },
      {
        path: 'step3',
        data: {title: 'Etape 3'},
        component: Step03Component,
        title: 'Document Necessaires'
      },
      {
        path: 'step4',
        data: {title: 'Etape 4'},
        component: Step04Component,
        title: 'Document Necessaires'
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreateRoutingModule {
}
