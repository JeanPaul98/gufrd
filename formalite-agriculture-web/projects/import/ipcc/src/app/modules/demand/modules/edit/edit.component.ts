import {Location} from "@angular/common";
import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {debounceTime, Observable} from "rxjs";
import {StepModel} from "../../../../../../../../../src/app/models/step.model";
import {StepperService} from "../../../../../../../../../src/components/stepper/stepper.service";
import {DemandService} from "../../services/demand.service";

@UntilDestroy()
@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.scss'
})
export class EditComponent implements OnInit, OnDestroy {
  items = [1, 2, 3, 4];
  fileName = '';
  currentStep = JSON.parse(localStorage.getItem('currentStepEditIPCC') as string) ?? 1;
  urlRoute$: Observable<string> = this.demandService.currentRoute$;
  steps: StepModel[] = [
    {title: 'Etape 1', description: 'Informations sur le navire'},
    {title: 'Etape 2', description: 'Informations produits'},
    {title: 'Etape 3', description: 'Documents nécessaires'},
    {title: 'Etape 4', description: 'Recapitulatif et confirmation'}

  ];

  constructor(
    private demandService: DemandService,
    private location: Location,
    private stepperService: StepperService,
    private createService: DemandService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
  }

  ngOnInit(): void {

    setTimeout(() => {
      this.demandService.isHideDemandBarSubject.next(true);
    });

    this.location.subscribe(() => this.demandService.isHideDemandBarSubject.next(false));

    this.router.navigate(['demand/edit', `step${this.currentStep}`])

    this.stepperService.nowGoToNext.pipe(untilDestroyed(this), debounceTime(300)).subscribe(() => {
      this.onClickNext()
    })

    this.stepperService.nowGoToPrevious.pipe(untilDestroyed(this), debounceTime(300)).subscribe(() => {
      this.onClickPrevious()
    })

    this.createService.isEndGoBackSubject.pipe(untilDestroyed(this), debounceTime(300)).subscribe((res) => {
      if (res === 1) {
        this.onClickBack()
      }
    })
  }

  onClickBack() {
    this.demandService.isHideDemandBarSubject.next(false);
    this.createService.isEndGoBackSubject.next(0);
    this.demandService.back();
  }

  onClickNext() {
    if (this.currentStep < this.steps.length) {
      this.currentStep++;
      localStorage.setItem('currentStepEditIPCC', JSON.stringify(this.currentStep))

      this.router.navigate(['demand', 'edit', `step${this.currentStep}`])
    }
  }

  onClickPrevious() {

    // alert(this.currentStep)
    if (this.currentStep > 1) {
      this.currentStep--;
      localStorage.setItem('currentStepEditIPCC', JSON.stringify(this.currentStep))
      this.router.navigate(['demand', 'edit', `step${this.currentStep}`])
    }

  }

  ngOnDestroy(): void {
    localStorage.removeItem('step1EditIPCC');
    localStorage.removeItem('step2EditIPCC');
    localStorage.removeItem('currentStepEditIPCC');
  }
}
