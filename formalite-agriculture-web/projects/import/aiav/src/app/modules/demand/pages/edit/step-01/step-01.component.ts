import {Component, OnInit, signal, WritableSignal,} from '@angular/core';
import {FormControl, UntypedFormBuilder, UntypedFormGroup, Validators,} from '@angular/forms';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {BehaviorSubject, debounceTime, distinctUntilChanged, Observable, switchMap, tap,} from 'rxjs';
import {AgreementModel} from "../../../../../../../../../../src/app/models/agrement.model";
import {DemandAeaaCompletModel} from "../../../../../../../../../../src/app/models/demand-aeaa.model";
import {NavireModel} from '../../../../../../../../../../src/app/models/navire.model';
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';
import {DemandService} from "../../../services/demand.service";
import {CreateService} from "../../create/services/create.service";


@UntilDestroy()
@Component({
  selector: 'app-step-01',
  templateUrl: './step-01.component.html',
  styleUrl: './step-01.component.scss'
})
export class Step01Component implements OnInit {
  step1InfoNavireFrom!: UntypedFormGroup;

  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;

  isFindNavireInfo$: Observable<'false' | 'true' | 'null'>;
  isFindNavireInfoSubject: BehaviorSubject<'false' | 'true' | 'null'>;
  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

  navireInfo?: NavireModel;

  agreementList: WritableSignal<AgreementModel[]> = signal<AgreementModel[]>([]);

  numAtpControl = new FormControl();

  constructor(
    private stepperService: StepperService,
    private createService: CreateService,
    private demandService: DemandService,
    private _formBuilder?: UntypedFormBuilder
  ) {
    this.isLoadingSubject = new BehaviorSubject<boolean>(false);
    this.isLoading$ = this.isLoadingSubject.asObservable();
    this.isFindNavireInfoSubject = new BehaviorSubject<
      'false' | 'true' | 'null'
    >('null');
    this.isFindNavireInfo$ = this.isFindNavireInfoSubject.asObservable();
  }




  formatDate(dateString: string): string {
    const date = new Date(dateString);
    // Format as YYYY-MM-DDTHH:MM for input[type="datetime-local"]
    return date.toISOString().slice(0, 16);
  }

  initForm(): void {
    this.step1InfoNavireFrom = this._formBuilder!.group({
      numeroAgrement: [this.demand.numeroAgrement, Validators.required],
      agrement: [null],
      dateEmbarquement: [this.formatDate(this.demand.dateEmbarquement), Validators.required],
      provenance: [this.demand.provenance, Validators.required],
      raisonSocial: [this.demand.nomSociete, Validators.required],
      activite: [this.demand.activite, Validators.required],
    });

    this.step1InfoNavireFrom.get('raisonSocial')?.disable();
    this.step1InfoNavireFrom.get('activite')?.disable();

    this.step1InfoNavireFrom.patchValue(JSON.parse(localStorage.getItem('step1EditAEAA') as string) ?? {});
    this.step1InfoNavireFrom.get('dateEmbarquement')?.setValue(this.formatDate(this.demand.dateEmbarquement));
    this.onChangeNumAtp();
  }

  customSearchFn(term: string, item: AgreementModel) {
    term = term.toLocaleLowerCase();
    return item.numero.toLocaleLowerCase().indexOf(term) > -1 ||
      item.raisonSocialSociete.toLocaleLowerCase().indexOf(term) > -1;
  }

  onProductSelectChange(event: AgreementModel) {
    console.log(event);
    if(event === null || event === undefined){
      this.step1InfoNavireFrom.get('numeroAgrement')?.setValue("");
      this.step1InfoNavireFrom.get('raisonSocial')?.setValue("");
      this.step1InfoNavireFrom.get('activite')?.setValue("");
      return;
    }

    this.step1InfoNavireFrom.get('agrement')?.setValue(event);
    this.step1InfoNavireFrom.get('numeroAgrement')?.setValue(event.numero);
    this.step1InfoNavireFrom.get('raisonSocial')?.setValue(event.raisonSocialSociete);
    this.step1InfoNavireFrom.get('activite')?.setValue(event.activite);
  }


  ngOnInit(): void {
    this.initForm()
    this.getAgreementList();
  }

  getAgreementList(): void {
    this.demandService.getAgreementList().subscribe((res) => {
      this.agreementList.set(res as AgreementModel[]);
    });
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    this.step1InfoNavireFrom.get('raisonSocial')?.enable();
    this.step1InfoNavireFrom.get('activite')?.enable();
    localStorage.setItem('step1EditAEAA', JSON.stringify(this.step1InfoNavireFrom.value))
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
