import {AfterViewInit, Component} from '@angular/core';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {finalize} from 'rxjs';
import {
  DetPhytosanitaireProduitDto,
  IpRequest,
  ProductRequestModel
} from "../../../../../../../../../../../src/app/requests/ip.request";
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import {DemandService} from "../../../../services/demand.service";

@UntilDestroy()
@Component({
  selector: 'app-step-04',
  templateUrl: './step-04.component.html',
  styleUrl: './step-04.component.scss',
})
export class Step04Component implements AfterViewInit {

  shipInfo: Partial<IpRequest> | undefined ;
  products: {
    detPhytosanitaireProduitDtos: ProductRequestModel[]
  } = JSON.parse(localStorage.getItem('step2EditIPCC') as string);

  constructor(
    private stepperService: StepperService,
    private service: DemandService
  ) {
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    let productObjs: DetPhytosanitaireProduitDto[] = this.products.detPhytosanitaireProduitDtos.map((product, index) => {
      return {
        quantite: product.quantite,
        produit: {code: product.libelle},
        fournisseur: product.fournisseur,
        origine: product.origine,
        conteneur: product.conteneur,
        descriptionEnvoi: product.descriptionEnvoi,
        nombreColis: product.nombreColis,
      } as DetPhytosanitaireProduitDto;
    });

    productObjs = productObjs.filter((product, index) => index + 1 < productObjs.length);

    this.service.updateAccount(this.shipInfo!, true);
    this.service.account$.value.detPhytosanitaireProduitDtos = productObjs;
    this.service.updateAccount(this.service.account$.value, true);
    this.updateDemand(this.service.account$.value);
  }

  private updateDemand(data: IpRequest) {
    this.service.updateDemand(data).pipe(
      untilDestroyed(this),
      finalize(() => {
        localStorage.removeItem('step1EditIPCC');
        localStorage.removeItem('step2EditIPCC');
        localStorage.removeItem('currentStepEditIPCC');
        this.service.isEndGoBackSubject.next(1);
      })
    ).subscribe();
  }

  ngAfterViewInit(): void {
    this.shipInfo = JSON.parse(localStorage.getItem('step1EditIPCC') as string);
  }
}
