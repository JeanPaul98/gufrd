import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ImportComponent} from "./import.component";

// Les routes de l'importation
const routes: Routes = [
  {path: '', redirectTo: 'ipn', pathMatch: 'full'},

  {
    path: "",
    component: ImportComponent,
    children: [
      // Modules concernant l'autorisation de deport et de transit (ADT)
      {
        path: "adt",
        loadChildren: () => import("./modules/adt/adt.module").then(m => m.AdtModule)
      },
      // Modules concernant l'autorisation d'enlevement d'aliment pour animaux (AEAA)
      {
        path: "aeaa",
        loadChildren: () => import("./modules/aeaa/aeaa.module").then(m => m.AeaaModule)
      },
      // Modules concernant l'autorisation d'importation animaux vivant (AIAV)
      {
        path: "aiav",
        loadChildren: () => import("./modules/aiav/aiav.module").then(m => m.AiavModule)
      },
      // Modules concernant l'autorisation d'importation et d'enlevement de médicaments vétérinaires (AIEMV)
      {
        path: "aiemv",
        loadChildren: () => import("./modules/aiemv/aiemv.module").then(m => m.AiemvModule)
      },
      // Modules concernant l'autorisation d'importation de produit en consomation locale (AIPCL)
      {
        path: "aipcl",
        loadChildren: () => import("./modules/aipcl/aipcl.module").then(m => m.AipclModule)
      },
      // Modules concernant l'inspection phytosanitaire cargaison & contenaire (IPCC)
      {
        path: "ipcc",
        loadChildren: () => import("./modules/ipcc/ipcc.module").then(m => m.IpccModule)
      },
      // Modules concernant l'inspection phytosanitaire de navire (IPN)
      {
        path: "ipn",
        loadChildren: () => import("./modules/ipn/ipn.module").then(m => m.IpnModule)
      },
      // Modules concernant l'inspection phytosanitaire de produits phyto pharmacetiques (IPPPP)
      {
        path: "ippp",
        loadChildren: () => import("./modules/ippp/ippp.module").then(m => m.IpppModule)
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImportRoutingModule { }
