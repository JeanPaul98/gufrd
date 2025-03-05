import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {finalize} from 'rxjs';
import {
  DemandAeaaCompletModel,
  DemandAeaaSimpleModel,
} from '../../../../../../../../../../src/app/models/demand-aeaa.model';
import {NavireInfoModel} from '../../../../../../../../../../src/app/models/navire-info.model';
import {ProductModel, ProductObjModel,} from '../../../../../../../../../../src/app/models/product.model';
import {UploadFilesModel} from "../../../../../../../../../../src/app/models/upload-files.model";
import {
  DemandUploadFilesService
} from "../../../../../../../../../../src/components/demand-upload-files/demand-upload-files.service";
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';
import {environment} from "../../../../../../../../../../src/environments/environment";
import {CreateService} from "../../create/services/create.service";

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
    localStorage.getItem('step1EditAGREMENT') as string
  );
  step2LocalData: { detAutorisationProduitDtos: ProductModel[] } = JSON.parse(
    localStorage.getItem('step2EditAGREMENT') as string
  );
  step3LocalData: UploadFilesModel = JSON.parse(
    localStorage.getItem('step3AGREMENT') as string
  );

  demandAGREMENT!: DemandAeaaSimpleModel;
  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

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
    // this.createService
    //   .createDemande(this.demandAGREMENT)
    //   .pipe(
    //     untilDestroyed(this),
    //     switchMap((responses) => {
    //       this.demandUploadFilesService.saveFiles(responses as number, this.step3LocalData);
    //       return of(responses);
    //     }),
    //     map((res) => {
    //       console.log('res', res);
    //       return res;
    //     }),
    //     finalize(() => {
    //       localStorage.removeItem('step1AGREMENT');
    //       localStorage.removeItem('step2AGREMENT');
    //       localStorage.removeItem('step3AGREMENT');
    //       localStorage.removeItem('currentStepAGREMENT');
    //       this.createService.isEndGoBackSubject.next(1);
    //     })
    //   )
    //   .subscribe({});
  }

  editDemand() {
    this.createService
      .editDemande(this.demandAGREMENT)
      .pipe(
        untilDestroyed(this),
        finalize(() => {
          localStorage.removeItem('step1EditAGREMENT');
          localStorage.removeItem('step2EditAGREMENT');
          localStorage.removeItem('currentStepEditAGREMENT');
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
            code: product.code,
          },
        } as ProductObjModel;
      });

    productObjs = productObjs.filter(
      (product, index) => index + 1 < productObjs.length
    );

    this.demandAGREMENT = {
      ...this.demand,
      ...this.step1LocalData,
      compteClient: environment.COMPTE_CLIENT,
      detAutorisationProduitDtos: productObjs,
    };

    console.log('ddddddddddddd ', this.demandAGREMENT);

    this.editDemand();

    // this.stepperService.goToNextStep()

    // localStorage.removeItem('step1AGREMENT');
    // localStorage.removeItem('step2AGREMENT');
    // localStorage.removeItem('currentStepAGREMENT');
    // this.createService.isEndGoBackSubject.next(1);
  }
}
