import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, signal, WritableSignal,} from '@angular/core';
import {FormControl, UntypedFormBuilder, UntypedFormGroup, Validators,} from '@angular/forms';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {BehaviorSubject, debounceTime, distinctUntilChanged, Observable, switchMap, tap,} from 'rxjs';
import {NavireModel} from '../../../../../../../../../../src/app/models/navire.model';
import {SocieteModel} from "../../../../../../../../../../src/app/models/societe.model";
import {TypeAgrementModel} from "../../../../../../../../../../src/app/models/type-agrement.model";
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';
import {DemandService} from "../../../services/demand.service";
import {CreateService} from '../services/create.service';


@UntilDestroy()
@Component({
  selector: 'app-step-01',
  templateUrl: './step-01.component.html',
  styleUrl: './step-01.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Step01Component implements OnInit {
  step1InfoAgrementFrom!: UntypedFormGroup;

  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;

  societes: WritableSignal<SocieteModel[]> = signal<SocieteModel[]>([]);
  agrements: WritableSignal<TypeAgrementModel[]> = signal<TypeAgrementModel[]>([]);

  isFindNavireInfo$: Observable<'false' | 'true' | 'null'>;
  isFindNavireInfoSubject: BehaviorSubject<'false' | 'true' | 'null'>;

  navireInfo?: NavireModel;

  numAtpControl = new FormControl();

  constructor(
    private stepperService: StepperService,
    private createService: CreateService,
    private demandService: DemandService,
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
    this.step1InfoAgrementFrom = this._formBuilder!.group({
      numero: [null, [Validators.required]],
      activite: [null, [Validators.required]],
      societe: [null],
      agrement:[null],
      observation: [null, [Validators.required]],
      dateAgrement: [null, [Validators.required]],
      dateExpiration: [null, [Validators.required]],
      idAgrement: [null, [Validators.required]],
      idSociete: [null, [Validators.required]],
      nomSociete: [null, [Validators.required]]
    });


    this.step1InfoAgrementFrom.patchValue(JSON.parse(localStorage.getItem('step1AGREMENT') as string) ?? {});

    this.onChangeNumAtp();
  }

  ngOnInit(): void {
    this.initForm()
    this.societeList();
    this.agrementList();
  }
  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  agrementList() {
    this.demandService.getAgrements().subscribe((res) => {
      this.agrements.set(res as TypeAgrementModel[]);
    });
  }

  societeList(): void {
     this.demandService.getSocietes().subscribe((res) => {
      this.societes.set(res as SocieteModel[]);
    });
  }

  onClickNext() {
    this.step1InfoAgrementFrom.get('nomNavire')?.enable();
    this.step1InfoAgrementFrom.get('imo')?.enable();
    localStorage.setItem('step1AGREMENT', JSON.stringify(this.step1InfoAgrementFrom.value))
    this.stepperService.goToNextStep();
  }

  checkNavireInfoss(navNumero: string) {
    return this.createService.getNavireInfos(navNumero);
  }


  onChangeNumAtp() {
    this.step1InfoAgrementFrom.get('atp')?.valueChanges
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
            this.step1InfoAgrementFrom.get('nomNavire')?.enable();
            this.step1InfoAgrementFrom.get('nomNavire')?.setValue(this.navireInfo.nomNavire);
            this.step1InfoAgrementFrom.get('imo')?.enable();
            this.step1InfoAgrementFrom.get('imo')?.setValue(this.navireInfo.imo);

            this.step1InfoAgrementFrom.get('nomNavire')?.disable();
            this.step1InfoAgrementFrom.get('imo')?.disable();
            this.isFindNavireInfoSubject.next('true');
          } else {
            this.navireInfo = undefined;
            this.step1InfoAgrementFrom.get('nomNavire')?.setValue("");
            this.step1InfoAgrementFrom.get('imo')?.setValue("");
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
    return  this.step1InfoAgrementFrom.invalid;
  }

  onSubmit() {
    console.log(this.step1InfoAgrementFrom.value.imo);
  }

  onSocieteSelectChange(societe: SocieteModel) {
    console.log("$event",societe)
    this.step1InfoAgrementFrom.get('societe')?.setValue(societe);
    this.step1InfoAgrementFrom.get('nomSociete')?.setValue(societe.raisonSociale);
    this.step1InfoAgrementFrom.get('idSociete')?.setValue(societe.id);
  }

  onAgrementSelectChange(typeAgrement: TypeAgrementModel) {
    this.step1InfoAgrementFrom.get('agrement')?.setValue(typeAgrement);
    this.step1InfoAgrementFrom.get('nomAgrement')?.setValue(typeAgrement.libelle);
    this.step1InfoAgrementFrom.get('idAgrement')?.setValue(typeAgrement.id);
  }
}
