import {Component} from '@angular/core';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {of, switchMap} from 'rxjs';
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import {IpRequest} from "../../../../../../../../../../../src/app/requests/ip.request";
import {UploadFilesModel} from "../../../../../../../../../../../src/app/models/upload-files.model";
import {
  DemandUploadFilesService
} from "../../../../../../../../../../../src/components/demand-upload-files/demand-upload-files.service";
import {DemandService} from "../../../../services/demand.service";
import {map} from "rxjs/operators";

@UntilDestroy()
@Component({
  selector: 'app-ipn-step-04',
  templateUrl: './step-03.component.html',
  styleUrl: './step-03.component.scss',
})
export class Step03Component {
  step1LocalData: Partial<IpRequest> = JSON.parse(localStorage.getItem('step1IPN') as string);
  step2LocalData: UploadFilesModel = JSON.parse(localStorage.getItem('step2IPN') as string);

  constructor(
    private stepperService: StepperService,
    private service: DemandService,
    private filesService: DemandUploadFilesService
  ) {
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    this.service.updateAccount(this.step1LocalData, true);
    this.service.updateAccount(this.service.account$.value, true);
    this.createDemand(this.service.account$.value);
  }

  private createDemand(data: IpRequest) {
    this.service.createDemand(data).pipe(
      untilDestroyed(this),
      switchMap((responses) => {
        this.filesService.saveFiles(responses as number, this.step2LocalData,);
        return of(responses);
      }),
      map((res) => {
        console.log('res', res);
        return res;
      }),
    ).subscribe({
      next: () => {
        localStorage.removeItem('step1IPN');
        localStorage.removeItem('step2IPN');
        localStorage.removeItem('currentStepIPN');
        this.service.isEndGoBackSubject.next(1);
      },
      error: (err) => {
        console.log('err', err);
      },
    });
  }
}
