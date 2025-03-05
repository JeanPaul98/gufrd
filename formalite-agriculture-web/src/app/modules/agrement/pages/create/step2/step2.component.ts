import {DatePipe} from "@angular/common";
import {Component, inject} from '@angular/core';
import {Router} from "@angular/router";
import {ButtonDirective} from "@coreui/angular";
import {ModalFileShowComponent} from "@docs-components/modal-file-show/modal-file-show.component";
import {StepperService} from "@docs-components/stepper/stepper.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {finalize} from "rxjs";
import {map} from "rxjs/operators";
import {environment} from "../../../../../../environments/environment";
import {CreateAgrementModel} from "../../../../../models/demand-agrement.model";
import {AgrementService} from "../../../services/agrement.service";

@UntilDestroy()
@Component({
  selector: 'app-step2',
  standalone: true,
  imports: [
    ButtonDirective,
    DatePipe,
    ModalFileShowComponent
  ],
  templateUrl: './step2.component.html',
  styleUrl: './step2.component.scss'
})
export class Step2Component {

  stepperService = inject(StepperService);
  router = inject(Router);
  agrementService = inject(AgrementService);


  step1LocalData: CreateAgrementModel = JSON.parse(
    localStorage.getItem('step1AGREMENT') as string
  );

  demandAGREMENT!: CreateAgrementModel;

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    this.demandAGREMENT = {
      ...this.step1LocalData,
      compteClient: environment.COMPTE_CLIENT
    };

    this.createDemand();

  }

  createDemand() {
    this.agrementService
      .createDemande(this.demandAGREMENT)
      .pipe(
        untilDestroyed(this),
        map((res) => {
          console.log('res', res);
          return res;
        }),
        finalize(() => {
          localStorage.removeItem('step1AGREMENT');
          localStorage.removeItem('step2AGREMENT');
          localStorage.removeItem('step3AGREMENT');
          localStorage.removeItem('currentStepAGREMENT');
          this.router.navigate([ 'agrement'])
          // this.agrementService.isEndGoBackSubject.next(1);
        })
      )
      .subscribe({});
  }

}
