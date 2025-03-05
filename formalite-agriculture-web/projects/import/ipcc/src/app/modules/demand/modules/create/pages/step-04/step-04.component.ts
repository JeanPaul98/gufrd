import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {of, switchMap} from "rxjs";
import {map} from "rxjs/operators";
import {UploadFilesModel} from "../../../../../../../../../../../src/app/models/upload-files.model";
import {
  DetPhytosanitaireProduitDto,
  IpRequest,
  ProductRequestModel
} from "../../../../../../../../../../../src/app/requests/ip.request";
import {
  DemandUploadFilesService
} from "../../../../../../../../../../../src/components/demand-upload-files/demand-upload-files.service";
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import {environment} from "../../../../../../../../../../../src/environments/environment";
import {
  CreateService
} from "../../../../../../../../../adt/src/app/modules/demand/pages/create/services/create.service";
import {DemandService} from "../../../../services/demand.service";


@UntilDestroy()
@Component({
  selector: 'app-step-04',
  templateUrl: './step-04.component.html',
  styleUrl: './step-04.component.scss',
})
export class Step04Component {
  step1LocalData: Partial<IpRequest> = JSON.parse(localStorage.getItem('step1IPCC') as string);
  step2LocalData: {
    detPhytosanitaireProduitDtos: ProductRequestModel[]
  } = JSON.parse(localStorage.getItem('step2IPCC') as string);
  step3LocalData: UploadFilesModel = JSON.parse(localStorage.getItem('step3IPCC') as string);

  constructor(
    private stepperService: StepperService,
    private service: DemandService,
    private router: Router,
    private filesService: DemandUploadFilesService,
  private createService: CreateService,
    public demandUploadFilesService: DemandUploadFilesService
  ) {
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    let productObjs: DetPhytosanitaireProduitDto[] = this.step2LocalData.detPhytosanitaireProduitDtos.map((product, index) => {
      const productObj: Partial<DetPhytosanitaireProduitDto> = {...product};
      return {
        ...productObj,
        quantite: product.quantite,
        fournisseur: product.fournisseur,
        origine: product.origine,
        conteneur: product.conteneur,
        descriptionEnvoi: product.descriptionEnvoi,
        nombreColis: product.nombreColis,
        produit: {
          code: product.code,
          },
      } as DetPhytosanitaireProduitDto;
    });

    productObjs = productObjs.filter((product, index) => index + 1 < productObjs.length);

    this.service.updateAccount(this.step1LocalData, true);
    this.service.account$.value.detPhytosanitaireProduitDtos = productObjs;
    this.service.updateAccount(this.service.account$.value, true);
    this.createDemand(this.service.account$.value);
  }


  private createDemand(data: IpRequest) {
    this.service.createDemand({
      ...data,
      compteClient: environment.COMPTE_CLIENT,
    }).pipe(
      untilDestroyed(this),
      switchMap((responses) => {
        this.filesService.saveFiles(responses as number, this.step3LocalData,);
        return of(responses);
      }),
      map((res) => {
        console.log('res', res);
        return res;
      }),
    ).subscribe({
      next: () => {
        localStorage.removeItem('step1IPCC');
        localStorage.removeItem('step2IPCC');
        localStorage.removeItem('step3IPCC');
        localStorage.removeItem('currentStepIPCC');
        this.service.isEndGoBackSubject.next(1);
        this.service.back()
      },
      error: (err) => {
        console.log('err', err);
      },
    });
  }
}
