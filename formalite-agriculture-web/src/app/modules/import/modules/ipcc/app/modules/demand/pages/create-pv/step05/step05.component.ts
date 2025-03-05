import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import _ from "lodash";
import {toast} from "ngx-sonner";
import {tap} from "rxjs/operators";
import {NiceToastComponent} from "../../../../../../../../../../../components/nice-toast/nice-toast.component";
import {StepperService} from "../../../../../../../../../../../components/stepper/stepper.service";
import {
  PVInspecteurModel,
  PVIPCCModel,
  PVProduitModel,
  PVTraitementProduitModel
} from "../../../../../../../../../../models/admin/pv-ipcc.model";
import {CreatePVService} from "../services/create-pv.service";

@UntilDestroy()
@Component({
  selector: 'app-step05',
  templateUrl: './step05.component.html',
  styleUrl: './step05.component.scss'
})
export class Step05Component implements OnInit {
  step1LocalData: PVIPCCModel = JSON.parse(
    localStorage.getItem('step1PVIPCC') as string
  );
  step2LocalData: { detPvProduitDtos: PVProduitModel[] } = JSON.parse(
    localStorage.getItem('step2PVIPCC') as string
  );
  step3LocalData: { detPVInspecteurDtos: PVInspecteurModel[] } = JSON.parse(
    localStorage.getItem('step3PVIPCC') as string
  );

  step4LocalData: { detTraitementProduitDtos: PVTraitementProduitModel[] } = JSON.parse(
    localStorage.getItem('step4PVIPCC') as string
  );

  xendDataPVIPCC!: PVIPCCModel;

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
      .createPV(this.xendDataPVIPCC)
      .pipe(
        untilDestroyed(this),
        tap({
          next: (data) => {
            console.log('data', data);
            localStorage.removeItem('step1PVIPCC');
            localStorage.removeItem('step2PVIPCC');
            localStorage.removeItem('step3PVIPCC');
            localStorage.removeItem('step4PVIPCC');
            localStorage.removeItem('currentStepPVIPCC');

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

    const produitsTooXend = _.dropRight(this.step2LocalData.detPvProduitDtos);
    const inspecteursTooXend = _.dropRight(this.step3LocalData.detPVInspecteurDtos);
    const traitementsTooXend = _.dropRight(this.step4LocalData.detTraitementProduitDtos);


    this.xendDataPVIPCC = {
      ...this.step1LocalData,
      //compteClient: environment.COMPTE_CLIENT,
      detPvProduitDtos: produitsTooXend,
      detPVInspecteurDtos: inspecteursTooXend,
      detTraitementProduitDtos: traitementsTooXend,
    };

    console.log('ddddddddddddd ', this.xendDataPVIPCC);

    this.createPV();

    // this.stepperService.goToNextStep()

    // localStorage.removeItem('step1AEAA');
    // localStorage.removeItem('step2AEAA');
    // localStorage.removeItem('currentStepAEAA');
    // this.createService.isEndGoBackSubject.next(1);
  }
}
