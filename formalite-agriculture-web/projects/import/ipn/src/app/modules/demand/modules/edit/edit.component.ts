import {AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {debounceTime, Observable} from "rxjs";
import {StepModel} from "../../../../../../../../../src/app/models/step.model";
import {DemandService} from "../../services/demand.service";
import {Location} from "@angular/common";
import {StepperService} from "../../../../../../../../../src/components/stepper/stepper.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";

@UntilDestroy()
@Component({
  selector: 'app-ipn-edit',
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EditComponent implements OnInit, AfterViewInit, OnDestroy {
  atp: string | null | undefined;
  items = [1, 2, 3];
  currentStep = JSON.parse(localStorage.getItem('currentStepEditIPN') as string) ?? 1;
  urlRoute$: Observable<string> = this.demandService.currentRoute$;
  steps: StepModel[] = [
    {title: 'Etape 1', description: 'Informations sur le navire'},
    {title: 'Etape 2', description: 'Documents nécessaires'},
    {title: 'Etape 3', description: 'Recapitulatif et confirmation'}
  ];

  constructor(
    private demandService: DemandService, private location: Location, private stepperService: StepperService,
    private service: DemandService, private router: Router, private route: ActivatedRoute, private cdr: ChangeDetectorRef
  ) {
  }

  ngAfterViewInit(): void {
    this.service.isHideDemandBarSubject.next(true);
    this.location.subscribe(() => this.demandService.isHideDemandBarSubject.next(false));
    this.cdr.markForCheck();
  }

  ngOnInit(): void {
    this.atp = this.route.snapshot.paramMap.get('id');
    this.router.navigate([`demand/edit/${this.atp}/step${this.currentStep}`]);

    this.stepperService.nowGoToNext.pipe(untilDestroyed(this), debounceTime(300),).subscribe(() => {
      this.onClickNext();
    });

    this.stepperService.nowGoToPrevious.pipe(untilDestroyed(this), debounceTime(300),).subscribe(() => {
      this.onClickPrevious();
    });

    this.service.isEndGoBackSubject.pipe(untilDestroyed(this), debounceTime(300),).subscribe((res) => {
      if (res === 1) {
        this.onClickBack();
      }
    });
    this.cdr.markForCheck();
  }

  ngOnDestroy() {
    localStorage.removeItem('step1EditIPN');
    localStorage.removeItem('step2EditIPN');
    localStorage.removeItem('currentStepEditIPN');
  }

  onClickBack() {
    this.service.isHideDemandBarSubject.next(false);
    this.service.isEndGoBackSubject.next(0);
    this.service.back();
  }

  onClickNext() {
    if (this.currentStep < this.steps.length) {
      this.currentStep++;
      localStorage.setItem('currentStepEditIPN', JSON.stringify(this.currentStep));
      this.router.navigate([`demand/edit/${this.atp}/step${this.currentStep}`]);
    }
  }

  onClickPrevious() {
    if (this.currentStep > 1) {
      this.currentStep--;
      localStorage.setItem('currentStepEditIPN', JSON.stringify(this.currentStep));
      this.router.navigate([`demand/edit/${this.atp}/step${this.currentStep}`]);
    }
  }
}
