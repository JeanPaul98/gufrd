import { Component } from '@angular/core';
import { StepperService } from '../../../../../../../../../../src/components/stepper/stepper.service';
import { NavireInfoModel } from '../../../../../../../../../../src/app/models/navire-info.model';
import {
  ProductModel,
  ProductObjModel,
} from '../../../../../../../../../../src/app/models/product.model';
import { Router } from '@angular/router';
import { CreateService } from '../services/create.service';
import {DemandiocavModel, DemandiocavSimpleModel} from '../../../../../../../../../../src/app/models/demand-iocav.model';

import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import {finalize, of, switchMap} from 'rxjs';
import {environment} from "../../../../../../../../../../src/environments/environment";
import {UploadFilesModel} from "../../../../../../../../../../src/app/models/upload-files.model";
import {
  DemandUploadFilesService
} from "../../../../../../../../../../src/components/demand-upload-files/demand-upload-files.service";
import {map} from "rxjs/operators";
import _ from "lodash";

@UntilDestroy()
@Component({
  selector: 'app-step-04',
  templateUrl: './step-04.component.html',
  styleUrl: './step-04.component.scss',
})
export class Step04Component {
  constructor(
    private stepperService: StepperService,
    private router: Router,
    private createService: CreateService,
    public demandUploadFilesService: DemandUploadFilesService
  ) {}
  selectedCar: number = 1;
  cars = [
    { id: 1, name: 'Volvo' },
    { id: 2, name: 'Saab' },
    { id: 3, name: 'Opel' },
    { id: 4, name: 'Audi' },
  ];
  fileName = '';

  step1LocalData: NavireInfoModel = JSON.parse(
    localStorage.getItem('step1iocav') as string
  );
  step2LocalData: { detCertificatProduitDtos: ProductModel[] } = JSON.parse(
    localStorage.getItem('step2iocav') as string
  );
  step3LocalData: UploadFilesModel = JSON.parse(
    localStorage.getItem('step3iocav') as string
  );

  Demandiocav!: DemandiocavSimpleModel;

  onFileSelected(event: Event) {
    const target: HTMLInputElement = event.target as HTMLInputElement;

    const file: File | null | undefined = target?.files?.item(0);

    console.log('====================================');
    console.log(target?.files);
    console.log('====================================');
    if (file) {
      this.fileName = file.name;
    }
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }



  createDemand() {
    this.createService
      .createDemande(this.Demandiocav)
      .pipe(
        untilDestroyed(this),
        switchMap((responses) => {
          this.demandUploadFilesService.saveFiles(responses as number, this.step3LocalData);
          return of(responses);
        }),
        map((res) => {
          console.log('res', res);
          return res;
        }),
        finalize(() => {
          localStorage.removeItem('step1iocav');
          localStorage.removeItem('step2iocav');
          localStorage.removeItem('step3iocav');
          localStorage.removeItem('currentStepiocav');
          this.createService.isEndGoBackSubject.next(1);
        })
      )
      .subscribe({});
  }


  getProductLibelle(code: string): string {
    return this.step2LocalData.detCertificatProduitDtos.find(x => x.libelle === code)?.libelle as string;
  }
  
  onClickNext() {
    if (this.step2LocalData && this.step2LocalData.detCertificatProduitDtos) {
      let productObjs: ProductObjModel[] = this.step2LocalData.detCertificatProduitDtos.map((product, index) => {
        return {
          quantite: product.quantite,
          unite: product.unite,
          fournisseur: product.fournisseur,
          origine: product.origine,
          conteneur: product.conteneur,
          sexe: product.sexe,
          descriptionEnvoi: product.descriptionEnvoi,
          poidsTotal: product.poidsTotal,
          natureProduit: product.natureProduit,
          produit: {
            code: product.code,
          },
        } as ProductObjModel;
      });
  
      productObjs = productObjs.filter((product, index) => index + 1 < productObjs.length);
  
      this.Demandiocav = {
        ...this.step1LocalData,
        compteClient: environment.COMPTE_CLIENT,
        detCertificatProduitDtos: productObjs,
      };
  
      console.log('demande exportation iocav non-soumis ', this.Demandiocav);
  
      this.createDemand();
    } else {
      console.error('Erreur: step2LocalData ou detCertificatProduitDtos est undefined.');
    }
  }
  
}
