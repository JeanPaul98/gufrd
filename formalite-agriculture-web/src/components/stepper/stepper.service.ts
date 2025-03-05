import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StepperService {
  currentStep: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  // currentstep$ = this.currentStep.asObservable();
  nowGoToNext: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  nowGoToPrevious: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  constructor() {
    this.currentStep.next(1);
  }

  goToNextStep(): void {
    this.nowGoToNext.next(1);
  }

  goToPreviousStep(): void {
    this.nowGoToPrevious.next(1);
  }

  reinitialize(): void {
    this.currentStep.next(1);
  }
}
