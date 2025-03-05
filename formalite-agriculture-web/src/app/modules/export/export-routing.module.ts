import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ExportComponent} from "./export.component";

const routes: Routes = [
  {path: '', redirectTo: 'crbn', pathMatch: 'full'},

  {
    path: '',
    component: ExportComponent,
    children: [
      // Modules concernant Contrôle de ravitaillement à bord des navires (CRBN)
      {
        path: 'crbn',
        loadChildren: () => import('./modules/crbn/crbn.module').then(m => m.CrbnModule)
      },
      // Modules concernant Certificat sanitaire pour cire (CSC)
      {
        path: 'cspc',
        loadChildren: () => import('./modules/csc/csc.module').then(m => m.CscModule)
      },
      // Modules concernant Certificat sanitaire pour cuirs et peaux (CSCP)
      {
        path: 'cscp',
        loadChildren: () => import('./modules/cscp/cscp.module').then(m => m.CscpModule)
      },
      // Modules concernant Etablissement de certificat phytosanitaire (ECP)
      {
        path: 'ecp',
        loadChildren: () => import('./modules/ecp/ecp.module').then(m => m.EcpModule)
      },
      // Modules concernant Etablissement de certificat phytosanitaire pour les fruits et légumes (ECPFL)
      {
        path: 'ecpfl',
        loadChildren: () => import('./modules/ecpfl/ecpfl.module').then(m => m.EcpflModule)
      },
      // Modules concernant Inspection phytosanitaire pour l’obtention de certificat phytosanitaire (IPOCP)
      {
        path: 'ipocp',
        loadChildren: () => import('./modules/ipocp/ipocp.module').then(m => m.IpocpModule)
      },
      // Modules concernant Inspection pour l’obtention de certificat sanitaire pour animaux vivants (IOCAV)
      {
        path: 'iocav',
        loadChildren: () => import('./modules/iocav/iocav.module').then(m => m.IocavModule)
      },
    ],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExportRoutingModule { }
