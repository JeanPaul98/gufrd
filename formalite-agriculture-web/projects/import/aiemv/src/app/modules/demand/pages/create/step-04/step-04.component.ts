import { Component } from '@angular/core';
import { StepperService } from '../../../../../../../../../../src/components/stepper/stepper.service';
import { NavireInfoModel } from '../../../../../../../../../../src/app/models/navire-info.model';
import {
  ProductModel,
  ProductObjModel,
} from '../../../../../../../../../../src/app/models/product.model';
import { Router } from '@angular/router';
import { CreateService } from '../services/create.service';
import {
  DemandAeaaModel,
  DemandAeaaSimpleModel,
} from '../../../../../../../../../../src/app/models/demand-aeaa.model';
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
    localStorage.getItem('step1AEAA') as string
  );
  step2LocalData: { detAutorisationProduitDtos: ProductModel[] } = JSON.parse(
    localStorage.getItem('step2AEAA') as string
  );
  step3LocalData: UploadFilesModel = JSON.parse(
    localStorage.getItem('step3AEAA') as string
  );

  demandAEAA!: DemandAeaaSimpleModel;

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
      .createDemande(this.demandAEAA)
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
          localStorage.removeItem('step1AEAA');
          localStorage.removeItem('step2AEAA');
          localStorage.removeItem('step3AEAA');
          localStorage.removeItem('currentStepAEAA');
          this.createService.isEndGoBackSubject.next(1);
        })
      )
      .subscribe({});
  }



  onClickNext() {
    let productObjs: ProductObjModel[] =
      this.step2LocalData.detAutorisationProduitDtos.map((product, index) => {
        return {
          quantite: product.quantite,
          unite: product.unite,
          nombreCarton: product.nombreCarton,
          origine: product.origine,
          produit: {
            code: product.libelle,
          },
        } as ProductObjModel;
      });

    productObjs = productObjs.filter(
      (product, index) => index + 1 < productObjs.length
    );

    this.demandAEAA = {
      ...this.step1LocalData,
      compteClient: environment.COMPTE_CLIENT,
      detAutorisationProduitDtos: productObjs,
    };

    console.log('ddddddddddddd ', this.demandAEAA);

    this.createDemand();

    // this.stepperService.goToNextStep()

    // localStorage.removeItem('step1AEAA');
    // localStorage.removeItem('step2AEAA');
    // localStorage.removeItem('currentStepAEAA');
    // this.createService.isEndGoBackSubject.next(1);
  }
}
