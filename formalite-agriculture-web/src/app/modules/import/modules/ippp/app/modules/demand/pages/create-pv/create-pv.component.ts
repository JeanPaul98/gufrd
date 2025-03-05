import {Component, OnInit} from '@angular/core';
import {StepModel} from "../../../../../../../../../models/step.model";
import {Location} from "@angular/common";
import {StepperService} from "@docs-components/stepper/stepper.service";

import {Router} from "@angular/router";
import {DemandService} from "../../services/demand.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {debounceTime} from "rxjs";
import {DefaultLayoutService} from "../../../../../../../../../layout/default-layout/default-layout.service";
import {CreatePVService} from "./services/create-pv.service";

@UntilDestroy()
@Component({
  selector: 'app-create-pv',
  templateUrl: './create-pv.component.html',
  styleUrl: './create-pv.component.scss'
})
export class CreatePvComponent implements OnInit {
  currentStep = JSON.parse(localStorage.getItem('currentStepPVIPPP') as string) ?? 1;

  steps: StepModel[] = [
    {title: 'Etape 1', description: 'Informations générales'},
    {title: 'Etape 2', description: 'Marchandises Reperees et Mesures Prescrites'},
    {title: 'Etape 3', description: 'Inspecteurs pour la visite'},
    {title: 'Etape 4', description: 'Recapitulatif'},
  ];

  constructor(
    private demandService: DemandService,
    private location: Location,
    private stepperService: StepperService,
    private defaultLayoutService: DefaultLayoutService,
    private createPVService: CreatePVService,
    private router: Router
  ) {}

  ngOnInit(): void {

    setTimeout(() => {
    this.defaultLayoutService.hideSidebar();
      this.demandService.isHideDemandBarSubject.next(true);
    });

    this.location.subscribe(() => {
      this.demandService.isHideDemandBarSubject.next(false);
    });


    this.router.navigate(['/importations/ippp/demand/create-pv', `step${this.currentStep}`])

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

    this.createPVService.isEndGoBackSubject.pipe(
      untilDestroyed(this),
      debounceTime(300),
    ).subscribe((res) => {
      if(res === 1) {
        this.goBackToBegin()

      }
    })
  }


  onClickNext(){
    if(this.currentStep < this.steps.length ) {
      this.currentStep++;
      localStorage.setItem('currentStepPVIPPP', JSON.stringify(this.currentStep))

      this.router.navigate(['/importations/ippp/demand/create-pv', `step${this.currentStep}`])
    }
  }
  onClickPrevious(){

    // alert(this.currentStep)
    if(this.currentStep > 1 ) {
      this.currentStep--;

      localStorage.setItem('currentStepPVIPPP', JSON.stringify(this.currentStep))
      this.router.navigate(['/importations/ippp/demand/create-pv', `step${this.currentStep}`])
    }

  }

  goBackToBegin(){
    this.demandService.back();
  }

  onClickBack() {
    // this.currentStep = 1;
    this.demandService.simpleBack()
    this.defaultLayoutService.openSidebar();
    // this.onClickPrevious()
    // localStorage.setItem('currentStepAEAA', JSON.stringify(this.currentStep))



    // this.demandService.customBack('/importations/ippp/demand/'+this.curentTab);
  }
}
