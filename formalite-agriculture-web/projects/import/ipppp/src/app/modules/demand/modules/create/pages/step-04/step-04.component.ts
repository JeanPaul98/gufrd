import {Component} from '@angular/core';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import {
  DetPhytosanitaireProduitDto,
  IpRequest,
  ProductRequestModel
} from "../../../../../../../../../../../src/app/requests/ip.request";
import {DemandService} from "../../../../services/demand.service";
import {UploadFilesModel} from "../../../../../../../../../../../src/app/models/upload-files.model";
import {
  DemandUploadFilesService
} from "../../../../../../../../../../../src/components/demand-upload-files/demand-upload-files.service";
import {of, switchMap} from "rxjs";

@UntilDestroy()
@Component({
  selector: 'app-step-04',
  templateUrl: './step-04.component.html',
  styleUrl: './step-04.component.scss',
})
export class Step04Component {
  step1LocalData: Partial<IpRequest> = JSON.parse(localStorage.getItem('step1IPPPP') as string);
  step2LocalData: {
    detPhytosanitaireProduitDtos: ProductRequestModel[]
  } = JSON.parse(localStorage.getItem('step2IPPPP') as string);
  step3LocalData: UploadFilesModel = JSON.parse(localStorage.getItem('step3IPPPP') as string);

  constructor(
    private stepperService: StepperService,
    private service: DemandService,
    public demandUploadFilesService: DemandUploadFilesService
  ) {
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    let productObjs: DetPhytosanitaireProduitDto[] = this.step2LocalData.detPhytosanitaireProduitDtos.map((product) => {
      return {
        quantite: product.quantite,
        produit: {
          code: product.libelle,
        },
        fournisseur: product.fournisseur,
        origine: product.origine,
        conteneur: product.conteneur,
        descriptionEnvoi: product.descriptionEnvoi,
        nombreColis: product.nombreColis,
      } as DetPhytosanitaireProduitDto;
    });

    productObjs = productObjs.filter((product, index) => index + 1 < productObjs.length);

    this.service.updateAccount(this.step1LocalData, true);
    this.service.account$.value.detPhytosanitaireProduitDtos = productObjs;
    this.service.updateAccount(this.service.account$.value, true);
    this.createDemand(this.service.account$.value);
  }

  getProductName(code: string): string {
    return this.step2LocalData.detPhytosanitaireProduitDtos.find((x) => x.libelle === code)?.libelle as string;
  }

  private createDemand(data: IpRequest) {
    this.service.createDemand(data).pipe(
      untilDestroyed(this),
      switchMap((responses) => {
        this.demandUploadFilesService.saveFiles(
          responses as number,
          this.step3LocalData,
        );
        return of(responses);
      })
    ).subscribe(() => {
      localStorage.removeItem('step1IPPPP');
      localStorage.removeItem('step2IPPPP');
      localStorage.removeItem('step3IPPPP');
      localStorage.removeItem('currentStepIPPPP');
      this.service.isEndGoBackSubject.next(1);
      this.service.successHandler('Demande d\'inspection phytosanitaire de navire a été créée avec succès');
    });
  }
}
