import {AfterViewChecked, AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {debounceTime, Observable} from "rxjs";
import {StepModel} from "../../../../../../../../../src/app/models/step.model";
import {DemandService} from "../../services/demand.service";
import {Location} from "@angular/common";
import {StepperService} from "../../../../../../../../../src/components/stepper/stepper.service";
import {CreateService} from "../create/services/create.service";
import {Router} from "@angular/router";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";

@UntilDestroy()
@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.scss'
})
export class EditComponent implements OnInit, AfterViewChecked {
  items = [1, 2, 3, 4];
  fileName = '';
  currentStep = JSON.parse(localStorage.getItem('currentStepEditAEAA') as string) ?? 1;
  urlRoute$: Observable<string> = this.demandService.currentRoute$;
  steps: StepModel[] = [
    {title: 'Etape 1', description: 'Informations sur le navire'},
    {title: 'Etape 2', description: 'Informations produits'},
    {title: 'Etape 3', description: 'Documents nécessaires'},
    {title: 'Etape 4', description: 'Recapitulatif et confirmation'}

  ];

  selectedCar: number = 1;
  cars = [
    { id: 1, name: 'Volvo' },
    { id: 2, name: 'Saab' },
    { id: 3, name: 'Opel' },
    { id: 4, name: 'Audi' },
  ];
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

  constructor(
    private demandService: DemandService,
    private location: Location,
    private stepperService: StepperService,
    private createService: CreateService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    setTimeout(() => {
      this.demandService.isHideDemandBarSubject.next(true);

    });

    this.location.subscribe(() => {
      this.demandService.isHideDemandBarSubject.next(false);
    });

    this.router.navigate([ 'demand','edit', `step${this.currentStep}`])

    this.stepperService.nowGoToNext.pipe(
      untilDestroyed(this),
      debounceTime(300),
    ).subscribe(() => {
      this.onClickNext()
    })

    this.stepperService.nowGoToPrevious.pipe(
      untilDestroyed(this),
      debounceTime(300),
    ).subscribe(() => {

      this.onClickPrevious()
    })

    this.createService.isEndGoBackSubject.pipe(
      untilDestroyed(this),
      debounceTime(300),
    ).subscribe((res) => {
      if(res === 1) {
        this.onClickBack()
      }
    })
  }

  ngAfterViewChecked(): void {

  }

  onClickBack() {
    // this.currentStep = 1;
    // this.onClickPrevious()
    // localStorage.setItem('currentStepEditAEAA', JSON.stringify(this.currentStep))
    this.demandService.isHideDemandBarSubject.next(false);
    this.createService.isEndGoBackSubject.next(0);
    this.demandService.back();
  }

  onClickNext(){
    if(this.currentStep < this.steps.length ) {
      this.currentStep++;
      localStorage.setItem('currentStepEditAEAA', JSON.stringify(this.currentStep))

      this.router.navigate([ 'demand','edit', `step${this.currentStep}`])
    }
  }
  onClickPrevious(){

    // alert(this.currentStep)
    if(this.currentStep > 1 ) {
      this.currentStep--;

      localStorage.setItem('currentStepEditAEAA', JSON.stringify(this.currentStep))
      this.router.navigate([ 'demand','edit', `step${this.currentStep}`])
    }

  }
}
