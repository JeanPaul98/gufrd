import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit,} from '@angular/core';
import {FormControl, UntypedFormBuilder, UntypedFormGroup, Validators,} from '@angular/forms';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {BehaviorSubject, debounceTime, distinctUntilChanged, Observable, switchMap, tap,} from 'rxjs';
import {NavireModel} from '../../../../../../../../../../src/app/models/navire.model';
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';
import {CreateService} from '../services/create.service';


@UntilDestroy()
@Component({
  selector: 'app-step-01',
  templateUrl: './step-01.component.html',
  styleUrl: './step-01.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Step01Component implements OnInit {
  step1InfoNavireFrom!: UntypedFormGroup;

  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;

  isFindNavireInfo$: Observable<'false' | 'true' | 'null'>;
  isFindNavireInfoSubject: BehaviorSubject<'false' | 'true' | 'null'>;

  navireInfo?: NavireModel;

  numAtpControl = new FormControl();

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
      atp: [null, Validators.required],
      nomNavire: ["", Validators.required],
      imo: ["", Validators.required],
      // conteneur: [null, Validators.required],
      nomImportateur: [null, Validators.required],
      affreteur: [null, Validators.required],
      numeroAutorisation: [null, Validators.required],
      numeroBL: [null, Validators.required],
      datearrivee: [null, Validators.required],
    });

    this.step1InfoNavireFrom.get('imo')?.disable();
    this.step1InfoNavireFrom.get('nomNavire')?.disable();

    this.step1InfoNavireFrom.patchValue(JSON.parse(localStorage.getItem('step1ADT') as string) ?? {});

    this.onChangeNumAtp();
  }

  ngOnInit(): void {
    this.initForm()
  }
  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    this.step1InfoNavireFrom.get('nomNavire')?.enable();
    this.step1InfoNavireFrom.get('imo')?.enable();
    localStorage.setItem('step1ADT', JSON.stringify(this.step1InfoNavireFrom.value))
    this.stepperService.goToNextStep();
  }

  checkNavireInfoss(navNumero: string) {
    return this.createService.getNavireInfos(navNumero);
  }


  onChangeNumAtp() {
    this.step1InfoNavireFrom.get('atp')?.valueChanges
      .pipe(
        untilDestroyed(this),
        tap(() => this.isLoadingSubject.next(true)),
        debounceTime(300), // Wait for 300ms pause in events
        distinctUntilChanged(), // Only proceed if the value has changed
        switchMap((value) => {

          return this.createService.getNavireInfos(value);
        }),
        tap((data) => {
          this.isLoadingSubject.next(false);
          if (data !== null) {
            console.log('value', data);

            this.navireInfo = data as NavireModel;
            this.step1InfoNavireFrom.get('nomNavire')?.enable();
            this.step1InfoNavireFrom.get('nomNavire')?.setValue(this.navireInfo.nomNavire);
            this.step1InfoNavireFrom.get('imo')?.enable();
            this.step1InfoNavireFrom.get('imo')?.setValue(this.navireInfo.imo);

            this.step1InfoNavireFrom.get('nomNavire')?.disable();
            this.step1InfoNavireFrom.get('imo')?.disable();
            this.isFindNavireInfoSubject.next('true');
          } else {
            this.navireInfo = undefined;
            this.step1InfoNavireFrom.get('nomNavire')?.setValue("");
            this.step1InfoNavireFrom.get('imo')?.setValue("");
            this.isFindNavireInfoSubject.next('false');
          }
        })
      )
      .subscribe({
        next: (data) => {},
        error: (err) => {
          console.log('errerrerr', err);

          this.isFindNavireInfoSubject.next('null');
          this.isLoadingSubject.next(false);
          this.navireInfo = undefined;
        },
      });
  }

  checkIfFormIsValid(): boolean {
    return  this.step1InfoNavireFrom.invalid;
  }

  onSubmit() {
    console.log(this.step1InfoNavireFrom.value.imo);
  }
}
