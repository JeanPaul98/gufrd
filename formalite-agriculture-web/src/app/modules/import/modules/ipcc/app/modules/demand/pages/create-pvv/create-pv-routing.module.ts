import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CreatePvComponent} from "./create-pv.component";
import {Step01Component} from "./step01/step01.component";
import {Step02Component} from "./step02/step02.component";
import {Step03Component} from "./step03/step03.component";
import {Step04Component} from "./step04/step04.component";

const routes: Routes = [
  {
    path: '',
    component: CreatePvComponent,
    data: {
      title: `Création PV d'autorisation d'enlèvement d'aliments pour animaux vivants`
    },
    children:[
      {
        path: '',
        redirectTo: 'step1',
        pathMatch: 'full'
      },
      {
        path: 'step1',
        component: Step01Component,
        data: {
          title: 'Etape 1'
        },
        title: 'Information Générale'
      },
      {
        path: 'step2',
        component: Step02Component,
        data: {
          title: 'Etape 2'
        },
        title: 'Information Produits'
      },
      {
        path: 'step3',
        component: Step03Component,
        data: {
          title: 'Etape 3'
        },
        title: 'Document Necessaires'
      },
      {
        path: 'step4',
        component: Step04Component,
        data: {
          title: 'Etape 4'
        },
        title: 'Validation'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreatePvRoutingModule { }
