import {AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';
import {debounceTime, Observable} from "rxjs";
import {StepModel} from "../../../../../../../../../src/app/models/step.model";
import {Location} from "@angular/common";
import {StepperService} from "../../../../../../../../../src/components/stepper/stepper.service";
import {Router} from "@angular/router";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {DemandService} from "../../services/demand.service";

@UntilDestroy()
@Component({
  selector: 'app-ipn-create',
  templateUrl: './create.component.html',
  styleUrl: './create.component.scss'
})
export class CreateComponent implements OnInit, AfterViewInit, OnDestroy {
  items = [1, 2, 3];
  currentStep = JSON.parse(localStorage.getItem('currentStepIPN') as string) ?? 1;
  urlRoute$: Observable<string> = this.service.currentRoute$;
  steps: StepModel[] = [
    {title: 'Etape 1', description: 'Informations sur le navire'},
    {title: 'Etape 2', description: 'Documents nécessaires'},
    {title: 'Etape 3', description: 'Recapitulatif et confirmation'}
  ];

  constructor(
    private service: DemandService,
    private location: Location,
    private stepperService: StepperService,
    private router: Router
  ) {
  }

  ngAfterViewInit(): void {
    this.service.isHideDemandBarSubject.next(true);
    this.location.subscribe(() => this.service.isHideDemandBarSubject.next(false));
  }

  ngOnInit(): void {
    this.router.navigate(['demand/create', `step${this.currentStep}`]);

    this.stepperService.nowGoToNext.pipe(untilDestroyed(this), debounceTime(300)).subscribe(() => {
      this.onClickNext();
    });

    this.stepperService.nowGoToPrevious.pipe(untilDestroyed(this), debounceTime(300)).subscribe(() => {
      this.onClickPrevious();
    });

    this.service.isEndGoBackSubject.pipe(untilDestroyed(this), debounceTime(300)).subscribe((res) => {
      if (res === 1) {
        this.onClickBack();
      }
    });
  }

  ngOnDestroy() {
    localStorage.removeItem('step1IPN');
    localStorage.removeItem('step2IPN');
    localStorage.removeItem('currentStepIPN');
    localStorage.removeItem('demandDetailIPN');
  }

  onClickBack() {
    this.service.isHideDemandBarSubject.next(false);
    this.service.isEndGoBackSubject.next(0);
    this.service.back();
  }

  onClickNext() {
    if (this.currentStep < this.steps.length) {
      this.currentStep++;
      localStorage.setItem('currentStepIPN', JSON.stringify(this.currentStep));
      this.router.navigate(['demand/create', `step${this.currentStep}`]);
    }
  }

  onClickPrevious() {
    if (this.currentStep > 1) {
      this.currentStep--;
      localStorage.setItem('currentStepIPN', JSON.stringify(this.currentStep));
      this.router.navigate(['demand/create', `step${this.currentStep}`]);
    }
  }
}
