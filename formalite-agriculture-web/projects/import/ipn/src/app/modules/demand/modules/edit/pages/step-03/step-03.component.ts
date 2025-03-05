import {Component} from '@angular/core';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import {IpRequest} from "../../../../../../../../../../../src/app/requests/ip.request";
import {DemandService} from "../../../../services/demand.service";

@UntilDestroy()
@Component({
  selector: 'app-ipn-step-03',
  templateUrl: './step-03.component.html',
  styleUrl: './step-03.component.scss',
})
export class Step03Component {
  step1LocalData: Partial<IpRequest> = JSON.parse(localStorage.getItem('step1EditIPN') as string);

  constructor(
    private stepperService: StepperService,
    private service: DemandService
  ) {
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    this.service.updateAccount(this.step1LocalData, true);
    this.service.updateAccount(this.service.account$.value, true);
    this.updateDemand(this.service.account$.value);
  }

  private updateDemand(data: IpRequest) {
    this.service.updateDemand(data).pipe(untilDestroyed(this)).subscribe((data) => {
      if (data) {
        localStorage.removeItem('step1EditIPN');
        localStorage.removeItem('step2EditIPN');
        localStorage.removeItem('currentStepEditIPN');
        this.service.isEndGoBackSubject.next(1);
      }
    });
  }
}
