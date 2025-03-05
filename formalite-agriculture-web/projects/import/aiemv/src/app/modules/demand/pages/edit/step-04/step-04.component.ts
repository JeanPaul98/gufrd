import { Component } from '@angular/core';
import { StepperService } from '../../../../../../../../../../src/components/stepper/stepper.service';
import { NavireInfoModel } from '../../../../../../../../../../src/app/models/navire-info.model';
import {
  ProductModel,
  ProductObjModel,
} from '../../../../../../../../../../src/app/models/product.model';
import { Router } from '@angular/router';
import {
  DemandAeaaCompletModel,
  DemandAeaaModel,
  DemandAeaaSimpleModel,
} from '../../../../../../../../../../src/app/models/demand-aeaa.model';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { finalize } from 'rxjs';
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
    private createService: CreateService
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
    localStorage.getItem('step1EditAEAA') as string
  );
  step2LocalData: { detAutorisationProduitDtos: ProductModel[] } = JSON.parse(
    localStorage.getItem('step2EditAEAA') as string
  );

  demandAEAA!: DemandAeaaSimpleModel;
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

  editDemand() {
    this.createService
      .editDemande(this.demandAEAA)
      .pipe(
        untilDestroyed(this),
        finalize(() => {
          localStorage.removeItem('step1EditAEAA');
          localStorage.removeItem('step2EditAEAA');
          localStorage.removeItem('currentStepEditAEAA');
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
      ...this.demand,
      ...this.step1LocalData,
      compteClient: environment.COMPTE_CLIENT,
      detAutorisationProduitDtos: productObjs,
    };

    console.log('ddddddddddddd ', this.demandAEAA);

    this.editDemand();

    // this.stepperService.goToNextStep()

    // localStorage.removeItem('step1AEAA');
    // localStorage.removeItem('step2AEAA');
    // localStorage.removeItem('currentStepAEAA');
    // this.createService.isEndGoBackSubject.next(1);
  }
}
