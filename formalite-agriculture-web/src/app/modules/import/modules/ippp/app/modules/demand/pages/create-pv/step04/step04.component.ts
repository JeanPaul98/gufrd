import {Component, OnInit} from '@angular/core';

import {
  PVIPPPModel,
  PVInspecteurModel,
  PVProduitModel
} from "../../../../../../../../../../models/admin/pv-ippp.model";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {StepperService} from "@docs-components/stepper/stepper.service";
import {Router} from "@angular/router";

import {CreatePVService} from "../services/create-pv.service";
import {finalize} from "rxjs";
import {tap} from "rxjs/operators";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";
import _ from "lodash";

@UntilDestroy()
@Component({
  selector: 'app-step04',
  templateUrl: './step04.component.html',
  styleUrl: './step04.component.scss'
})
export class Step04Component implements OnInit {
  step1LocalData: PVIPPPModel = JSON.parse(
    localStorage.getItem('step1PVIPPP') as string
  );
  step2LocalData: { detPvProduitDtoList: PVProduitModel[] } = JSON.parse(
    localStorage.getItem('step2PVIPPP') as string
  );
  step3LocalData: { detPVInspecteurDtos: PVInspecteurModel[] } = JSON.parse(
    localStorage.getItem('step3PVIPPP') as string
  );

  xendDataPVAIPCL!: PVIPPPModel;

  constructor(
    private stepperService: StepperService,
    private router: Router,
    private createPVService: CreatePVService,
  ) {
  }

  ngOnInit(): void {
  }

  createPV() {
    this.createPVService
      .createPV(this.xendDataPVAIPCL)
      .pipe(
        untilDestroyed(this),
        tap({
          next: (data) => {
            console.log('data', data);
            localStorage.removeItem('step1PVIPPP');
            localStorage.removeItem('step2PVIPPP');
            localStorage.removeItem('step3PVIPPP');
            localStorage.removeItem('currentStepPVIPPP');

            this.createPVService.isEndGoBackSubject.next(1);
          },
          error: (err) => {
            console.log('err', err);
            toast.custom(NiceToastComponent, {
              position: 'top-center',
              componentProps: {
                texto: 'Echec de la création du PV, veuillez réessayer',
                state: 'error'
              }
            });
          },
          complete: () => {
            toast.custom(NiceToastComponent, {
              position: 'top-center',
              componentProps: {
                texto: 'Création du PV réussie',
                state: 'success'
              }
            });
          }
        })
      )
      .subscribe({});
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    /*let productObjs: ProductObjModel[] =
      this.step2LocalData.detAutorisationProduitDtos.map((product, index) => {
        return {
          quantite: product.quantite,
          unite: product.unite,
          // nombreCarton: product.nombreCarton,
          // origine: product.origine,
          produit: {
            code: product.libelle,
          },
        } as ProductObjModel;
      });

    productObjs = productObjs.filter(
      (product, index) => index + 1 < productObjs.length
    );*/

    const produitsTooXend = _.dropRight(this.step2LocalData.detPvProduitDtoList);
    const inspecteursTooXend = _.dropRight(this.step3LocalData.detPVInspecteurDtos);


    this.xendDataPVAIPCL = {
      ...this.step1LocalData,
      //compteClient: environment.COMPTE_CLIENT,
      detPvProduitDtoList: produitsTooXend,
      detPVInspecteurDtos: inspecteursTooXend,
    };

    console.log('ddddddddddddd ', this.xendDataPVAIPCL);

    this.createPV();

    // this.stepperService.goToNextStep()

    // localStorage.removeItem('step1AEAA');
    // localStorage.removeItem('step2AEAA');
    // localStorage.removeItem('currentStepAEAA');
    // this.createService.isEndGoBackSubject.next(1);
  }
}
