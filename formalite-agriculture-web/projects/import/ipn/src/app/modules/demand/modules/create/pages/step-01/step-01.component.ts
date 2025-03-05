import {Component, OnInit} from '@angular/core';
import {CreateService} from '../../services/create.service';
import {BehaviorSubject, debounceTime, distinctUntilChanged, Observable, switchMap, tap} from 'rxjs';
import {NavireModel} from '../../../../../../../../../../../src/app/models/navire.model';
import {FormGroup, UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import {DemandService} from "../../../../services/demand.service";

@UntilDestroy()
@Component({
  selector: 'app-step-01',
  templateUrl: './step-01.component.html',
  styleUrl: './step-01.component.scss',
})
export class Step01Component implements OnInit {
  shipInfo?: NavireModel;
  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;
  isFindShipInfo$: Observable<'false' | 'true' | 'null'>;
  step1InfoShipFrom: UntypedFormGroup = new FormGroup({});
  isFindShipInfoSubject: BehaviorSubject<'false' | 'true' | 'null'>;

  constructor(private stepperService: StepperService, private service: DemandService, private fb: UntypedFormBuilder) {
    this.isLoadingSubject = new BehaviorSubject<boolean>(false);
    this.isLoading$ = this.isLoadingSubject.asObservable();
    this.isFindShipInfoSubject = new BehaviorSubject<'false' | 'true' | 'null'>('null');
    this.isFindShipInfo$ = this.isFindShipInfoSubject.asObservable();
  }

  initForm(): void {
    this.step1InfoShipFrom = this.fb.group({
      atp: ["", Validators.required],
      imo: ["", Validators.required],
      nomNavire: ["", Validators.required],
      provenance: [""],
      destination: [""],
      affreteur: ["", Validators.required],
      nomDemandeur: ["", Validators.required],
      lieuInspection: ["", Validators.required],
      adresseDemandeur: ["", Validators.required],
      professionDemandeur: ["", Validators.required],
      datePrevueInspection: ["", Validators.required],
    });

    this.step1InfoShipFrom.get('imo')?.disable();
    this.step1InfoShipFrom.get('nomNavire')?.disable();
    this.step1InfoShipFrom.get('provenance')?.disable();
    this.step1InfoShipFrom.get('destination')?.disable();

    this.step1InfoShipFrom.patchValue(JSON.parse(localStorage.getItem('step1IPN') as string) ?? {});

    this.onChangeNumAtp();
  }

  ngOnInit(): void {
    this.initForm()
  }

  onClickNext() {
    this.step1InfoShipFrom.get('nomNavire')?.enable();
    this.step1InfoShipFrom.get('imo')?.enable();
    localStorage.setItem('step1IPN', JSON.stringify(this.step1InfoShipFrom.value))
    this.stepperService.goToNextStep();
  }

  onChangeNumAtp() {
    this.step1InfoShipFrom.get('atp')?.valueChanges.pipe(
      untilDestroyed(this),
      tap(() => this.isLoadingSubject.next(true)),
      debounceTime(300), // Wait for 300ms pause in events
      distinctUntilChanged(), // Only proceed if the value has changed
      switchMap((value) => this.service.getShipInfos(value)),
      tap((data) => {
        this.isLoadingSubject.next(false);
        if (data !== null) {
          this.shipInfo = data as NavireModel;
          this.step1InfoShipFrom.get('nomNavire')?.enable();
          this.step1InfoShipFrom.get('nomNavire')?.setValue(this.shipInfo.nomNavire);
          this.step1InfoShipFrom.get('imo')?.enable();
          this.step1InfoShipFrom.get('imo')?.setValue(this.shipInfo.imo);
          this.step1InfoShipFrom.get('provenance')?.enable();
          this.step1InfoShipFrom.get('provenance')?.setValue(this.shipInfo.portProvenance);
          this.step1InfoShipFrom.get('destination')?.enable();
          this.step1InfoShipFrom.get('destination')?.setValue(this.shipInfo.portDestination);

          this.step1InfoShipFrom.get('nomNavire')?.disable();
          this.step1InfoShipFrom.get('imo')?.disable();
          this.step1InfoShipFrom.get('provenance')?.disable();
          this.step1InfoShipFrom.get('destination')?.disable();
          this.isFindShipInfoSubject.next('true');
        } else {
          this.shipInfo = undefined;
          this.step1InfoShipFrom.get('nomNavire')?.setValue("");
          this.step1InfoShipFrom.get('imo')?.setValue("");
          this.step1InfoShipFrom.get('provenance')?.setValue("");
          this.step1InfoShipFrom.get('destination')?.setValue("");
          this.isFindShipInfoSubject.next('false');
        }
      })
    ).subscribe({
      next: () => {
      },
      error: (err) => {
        console.error('error', err);
        this.isFindShipInfoSubject.next('null');
        this.isLoadingSubject.next(false);
        this.shipInfo = undefined;
      },
    });
  }
}
