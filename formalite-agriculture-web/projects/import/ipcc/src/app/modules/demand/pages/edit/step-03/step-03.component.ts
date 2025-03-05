import { Component } from '@angular/core';
import { StepperService } from '../../../../../../../../../../src/components/stepper/stepper.service';

@Component({
  selector: 'app-step-02',
  templateUrl: './step-03.component.html',
  styleUrl: './step-03.component.scss'
})
export class Step03Component {
  constructor(private stepperService: StepperService){}
  selectedCar: number = 1;
  cars = [
    { id: 1, name: 'Certificat Phytosanitaire' },
    { id: 2, name: 'Certificat de Fumigation' },
    { id: 3, name: 'Certificat d’origine' },
    { id: 4, name: 'Certificat de qualité et poids' },
  ];
  fileName = '';

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

  onClickPrevious(){
    this.stepperService.goToPreviousStep()
  }

  onClickNext(){
    this.stepperService.goToNextStep()

  }
}
