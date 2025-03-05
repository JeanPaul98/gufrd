import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {finalize, of, switchMap} from "rxjs";
import {map} from "rxjs/operators";
import {DemandAgrementModel} from "../../../../../../../../../../src/app/models/demand-agrement.model";
import {UploadFilesModel} from "../../../../../../../../../../src/app/models/upload-files.model";
import {
  DemandUploadFilesService
} from "../../../../../../../../../../src/components/demand-upload-files/demand-upload-files.service";
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';
import {environment} from "../../../../../../../../../../src/environments/environment";
import {CreateService} from "../services/create.service";

@UntilDestroy()
@Component({
  selector: 'app-step-02',
  templateUrl: './step-03.component.html',
  styleUrl: './step-03.component.scss'
})
export class Step03Component {
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

  step1LocalData: DemandAgrementModel = JSON.parse(
    localStorage.getItem('step1AGREMENT') as string
  );
  // step2LocalData: { detAutorisationProduitDtos: ProductModel[] } = JSON.parse(
  //   localStorage.getItem('step2AGREMENT') as string
  // );
  step2LocalData: UploadFilesModel = JSON.parse(
    localStorage.getItem('step2AGREMENT') as string
  );

  demandAGREMENT!: DemandAgrementModel;

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
      .createDemande(this.demandAGREMENT)
      .pipe(
        untilDestroyed(this),
        switchMap((responses) => {
          this.demandUploadFilesService.saveFiles(responses as number, this.step2LocalData);
          return of(responses);
        }),
        map((res) => {
          console.log('res', res);
          return res;
        }),
        finalize(() => {
          localStorage.removeItem('step1AGREMENT');
          localStorage.removeItem('step2AGREMENT');
          localStorage.removeItem('step3AGREMENT');
          localStorage.removeItem('currentStepAGREMENT');
          this.createService.isEndGoBackSubject.next(1);
        })
      )
      .subscribe({});
  }


  // getProductLibelle(code: string): string {
  //   return this.step2LocalData.detAutorisationProduitDtos.find(x => x.libelle === code)?.libelle as string;
  // }
  onClickNext() {
    this.demandAGREMENT = {
      ...this.step1LocalData,
      compteClient: environment.COMPTE_CLIENT
    };

    console.log('ddddddddddddd ', this.demandAGREMENT);

    this.createDemand();

    // this.stepperService.goToNextStep()

    // localStorage.removeItem('step1AGREMENT');
    // localStorage.removeItem('step2AGREMENT');
    // localStorage.removeItem('currentStepAGREMENT');
    // this.createService.isEndGoBackSubject.next(1);
  }
}
