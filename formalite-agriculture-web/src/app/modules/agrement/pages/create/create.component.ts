import {CommonModule, Location} from "@angular/common";
import {Component, inject, OnInit} from '@angular/core';
import {Router, RouterModule} from "@angular/router";
import {ButtonDirective} from "@coreui/angular";
import {StepperComponent} from "@docs-components/stepper/stepper.component";
import {StepperService} from "@docs-components/stepper/stepper.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {debounceTime} from "rxjs";
import {DefaultLayoutService} from "../../../../layout/default-layout/default-layout.service";
import {StepModel} from "../../../../models/step.model";

@UntilDestroy()
@Component({
  selector: 'app-create',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    ButtonDirective,
    StepperComponent,
  ],
  templateUrl: './create.component.html',
  styleUrl: './create.component.scss'
})
export class CreateComponent implements OnInit {
  currentStep: number = 0;
  router = inject(Router);


  stepperService = inject(StepperService);
  steps: StepModel[] = [
    {title: 'Etape 1', description: 'Informations sur l\'agrément'},
    {title: 'Etape 2', description: 'Recapitulatif et confirmation'}
  ];

  location = inject(Location);
  defaultLayoutService = inject(DefaultLayoutService);

  onClickBack() {
    this.location.back();
  }

  ngOnInit(): void {
    this.defaultLayoutService.hideSidebar();
    // this.defaultLayoutService.activatedNavItem(this.defaultLayoutService.findNavItemByCode('agrement'));

    this.stepperService.nowGoToNext.pipe(
      untilDestroyed(this),
      debounceTime(300),
    ).subscribe(() => {
      console.log('nowGoToNext', this.currentStep)
      this.onClickNext()
    })

    this.stepperService.nowGoToPrevious.pipe(
      untilDestroyed(this),
      debounceTime(300),
    ).subscribe(() => {

      this.onClickPrevious()
    })
  }


  onClickNext(){
    if(this.currentStep < this.steps.length ) {
      this.currentStep++;
      localStorage.setItem('currentStepAGREMENT', JSON.stringify(this.currentStep))

      this.router.navigate([ 'agrement','create', `step${this.currentStep}`])
    }
  }
  onClickPrevious() {

    // alert(this.currentStep)
    if (this.currentStep > 1) {
      this.currentStep--;

      localStorage.setItem('currentStepAGREMENT', JSON.stringify(this.currentStep))
      this.router.navigate(['agrement', 'create', `step${this.currentStep}`])
    }
  }




}
