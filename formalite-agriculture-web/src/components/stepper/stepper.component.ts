import { Component, Input, OnInit } from '@angular/core';
import { StepperService } from './stepper.service';
import { Observable } from 'rxjs';
import { StepModel } from '../../app/models/step.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-stepper',
  templateUrl: './stepper.component.html',
  styleUrl: './stepper.component.scss',
  standalone: true,
  imports: [
    CommonModule,
  ],
})
export class StepperComponent  {
  @Input('currentStep') currentStep:number = 1;

  @Input('steps') steps: StepModel[] = [
    {title: 'Etape 1', description: 'Informations sur le navire'},
    {title: 'Etape 2', description: 'Informations complémentaires'},
    {title: 'Etape 3', description: 'Documents nécessaires'},
  ];
  constructor(private stepperService: StepperService) {
    // this.currentStep$ = this.stepperService.currentStep.asObservable();
    
  }


}
