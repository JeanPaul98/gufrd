import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {finalize, of, switchMap} from 'rxjs';
import {map} from "rxjs/operators";
import {DemandAeaaSimpleModel,} from '../../../../../../../../../../src/app/models/demand-aeaa.model';
import {NavireInfoModel} from '../../../../../../../../../../src/app/models/navire-info.model';
import {ProductModel, ProductObjModel,} from '../../../../../../../../../../src/app/models/product.model';
import {UploadFilesModel} from "../../../../../../../../../../src/app/models/upload-files.model";
import {
  DemandUploadFilesService
} from "../../../../../../../../../../src/components/demand-upload-files/demand-upload-files.service";
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';
import {environment} from "../../../../../../../../../../src/environments/environment";
import {CreateService} from '../services/create.service';

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
    localStorage.getItem('step1ADT') as string
  );
  step2LocalData: { detAutorisationProduitDtos: ProductModel[] } = JSON.parse(
    localStorage.getItem('step2ADT') as string
  );
  step3LocalData: UploadFilesModel = JSON.parse(
    localStorage.getItem('step3ADT') as string
  );

  demandADT!: DemandAeaaSimpleModel;

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
      .createDemande(this.demandADT)
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
          localStorage.removeItem('step1ADT');
          localStorage.removeItem('step2ADT');
          localStorage.removeItem('step3ADT');
          localStorage.removeItem('currentStepADT');
          this.createService.isEndGoBackSubject.next(1);
        })
      )
      .subscribe({});
  }


  getProductLibelle(code: string): string {
    return this.step2LocalData.detAutorisationProduitDtos.find(x => x.libelle === code)?.libelle as string;
  }
  onClickNext() {
    let productObjs: ProductObjModel[] =
      this.step2LocalData.detAutorisationProduitDtos.map((product, index) => {
        const productObj: ProductObjModel = {...product};
        return {
          ...productObj,
          quantite: product.quantite,
          unite: product.unite,
          conteneur: product.conteneur,
          provenance: product.provenance,
          produit: {
            code: product.code,
          },
        } as ProductObjModel;
      });

    productObjs = productObjs.filter(
      (product, index) => index + 1 < productObjs.length
    );
    console.log("detail",productObjs)
    this.demandADT = {
      ...this.step1LocalData,
      compteClient: environment.COMPTE_CLIENT,
      detAutorisationProduitDtos: productObjs,
    };

    console.log('ddddddddddddd ', this.demandADT);

    this.createDemand();

    // this.stepperService.goToNextStep()

    // localStorage.removeItem('step1ADT');
    // localStorage.removeItem('step2ADT');
    // localStorage.removeItem('currentStepADT');
    // this.createService.isEndGoBackSubject.next(1);
  }
}
