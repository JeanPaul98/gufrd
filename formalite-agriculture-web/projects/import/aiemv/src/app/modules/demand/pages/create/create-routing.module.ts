import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateComponent } from './create.component';
import { Step01Component } from './step-01/step-01.component';
import { Step02Component } from './step-02/step-02.component';
import { Step03Component } from './step-03/step-03.component';
import { Step04Component } from './step-04/step-04.component';

const routes: Routes = [
  {
    path: '',
    component: CreateComponent,
    data: {
      title: `Demandes d'inspection phytosanitaire de navire`
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
        title: 'Information Navires'
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
        title: 'Document Necessaires'
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreateRoutingModule { }
