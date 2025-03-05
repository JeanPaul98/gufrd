import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit,} from '@angular/core';
import {FormControl, UntypedFormBuilder, UntypedFormGroup, Validators,} from '@angular/forms';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {BehaviorSubject, debounceTime, distinctUntilChanged, Observable, switchMap, tap,} from 'rxjs';
import {DemandAeaaCompletModel} from "../../../../../../../../../../src/app/models/demand-aeaa.model";
import {NavireModel} from '../../../../../../../../../../src/app/models/navire.model';
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';
import {CreateService} from "../../create/services/create.service";


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
  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

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
      atp: [this.demand.atp, Validators.required],
      nomNavire: [this.demand.nomNavire, Validators.required],
      imo: [this.demand.imo, Validators.required],
      conteneur: [this.demand.conteneur, Validators.required],
      affreteur: [this.demand.affreteur, Validators.required],
      datearrivee: [this.demand.datearrivee, Validators.required],
    });

    this.step1InfoNavireFrom.get('imo')?.disable();
    this.step1InfoNavireFrom.get('nomNavire')?.disable();

    this.step1InfoNavireFrom.patchValue(JSON.parse(localStorage.getItem('step1EditIPCC') as string) ?? {});

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
    localStorage.setItem('step1EditIPCC', JSON.stringify(this.step1InfoNavireFrom.value))
    this.stepperService.goToNextStep();
  }

  checkNavireInfoss(navNumero: string) {
    // This should return an observable
    return this.createService.getNavireInfos(navNumero);
  }

  // checkNavireInfos(navireInfos: string) {
  //   this.isLoadingSubject.next(true);
  //   this.createService.getNavireInfos(navireInfos).subscribe({
  //     next: (data) => {
  //       console.log("data: ", data);
  //       this.navireInfo = data as NavireModel;
  //       this.isFindNavireInfoSubject.next("true");
  //       this.cdf.detectChanges();
  //     },
  //     error: (err: any) => {
  //       console.log(err);
  //       this.isFindNavireInfoSubject.next("false");
  //       this.isLoadingSubject.next(false);
  //       this.navireInfo = undefined;
  //     },
  //     complete: () => {
  //       console.log('complete');
  //       this.isLoadingSubject.next(false);

  //     }
  //   });
  // }

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
