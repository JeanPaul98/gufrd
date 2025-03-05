import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  OnInit,
} from '@angular/core';
import { CreateService } from '../services/create.service';
import {
  Observable,
  BehaviorSubject,
} from 'rxjs';
import { NavireModel } from '../../../../../../../../../../src/app/models/navire.model';
import {
  UntypedFormBuilder,
  UntypedFormGroup,
  Validators,
} from '@angular/forms';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { StepperService } from '../../../../../../../../../../src/components/stepper/stepper.service';



@UntilDestroy()
@Component({
  selector: 'app-step-01',
  templateUrl: './step-01.component.html',
  styleUrl: './step-01.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Step01Component implements OnInit { step1InfoNavireFrom!: UntypedFormGroup;

  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;

  isFindNavireInfo$: Observable<'false' | 'true' | 'null'>;
  isFindNavireInfoSubject: BehaviorSubject<'false' | 'true' | 'null'>;

  navireInfo?: NavireModel;

  constructor(
    private stepperService: StepperService,
    private createService: CreateService,
    private cdf: ChangeDetectorRef,
    private _formBuilder?: UntypedFormBuilder
  ) {
    this.isLoadingSubject = new BehaviorSubject<boolean>(false);
    this.isLoading$ = this.isLoadingSubject.asObservable();
    this.isFindNavireInfoSubject = new BehaviorSubject<
      'false' | 'true' | 'null'
    >('null');
    this.isFindNavireInfo$ = this.isFindNavireInfoSubject.asObservable();
  }

  initForm(): void {
    this.step1InfoNavireFrom = this._formBuilder!.group({
      paysExpediteur: [null, Validators.required],
      expediteur: [null, Validators.required],
      moyenTransport: [null, Validators.required],
      paysOrigine: [null, Validators.required],
      lieuOrigine: [null, Validators.required],
      paysDestination: [null, Validators.required],
      nomDestinataire: [null, Validators.required],
      lieuChargement: [null, Validators.required],
      dateDepart: [null, Validators.required],
      posteFrontalier: [null, Validators.required],
    });

    // Chargement des données depuis le localStorage si présentes
    const savedData = JSON.parse(localStorage.getItem('step1AEAA') as string);
    if (savedData) {
      this.step1InfoNavireFrom.patchValue(savedData);
    }
  }

  ngOnInit(): void {
    this.initForm();
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    if (this.checkIfFormIsValid()) {
      // Sauvegarde des valeurs dans le localStorage avant de passer à l'étape suivante
      localStorage.setItem(
        'step1iocav',
        JSON.stringify(this.step1InfoNavireFrom.value)
      );
      this.stepperService.goToNextStep();
    } else {
      alert('Veuillez remplir tous les champs requis.');
    }
  }

  checkIfFormIsValid(): boolean {
    // Retourne 'true' si le formulaire est valide (tous les champs requis sont remplis)
    return this.step1InfoNavireFrom.valid;
  }

  onSubmit() {
    if (this.checkIfFormIsValid()) {
      console.log(this.step1InfoNavireFrom.value);
    } else {
      alert('Veuillez remplir tous les champs requis.');
    }
  }

}
