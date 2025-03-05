import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {StepperService} from "@docs-components/stepper/stepper.service";

import {BehaviorSubject, Observable} from "rxjs";
import {CreatePVService} from "../services/create-pv.service";
import {DemandAeaaCompletModel} from "../../../../../../../../../../models/demand-aeaa.model";
import {DemandService} from "../../../services/demand.service";

@Component({
  selector: 'app-step01',
  templateUrl: './step01.component.html',
  styleUrl: './step01.component.scss'
})
export class Step01Component implements OnInit {
  step1InfoPVFrom!: UntypedFormGroup;
  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;

  constructor(
    private stepperService: StepperService,
    private createService: CreatePVService,
    private cdf: ChangeDetectorRef,
    private demandService: DemandService,
    private _formBuilder?: UntypedFormBuilder
  ) {
    this.isLoadingSubject = new BehaviorSubject<boolean>(false);
    this.isLoading$ = this.isLoadingSubject.asObservable();

  }

  initForm(): void {
    this.step1InfoPVFrom = this._formBuilder!.group({
      idFormalite: [this.demand.idFormalite],
      nomNavire: [null, Validators.required],
      consignataire: [null, Validators.required],
      provenance: [null, Validators.required],
      via: [null, Validators.required],
      datearrivee: [null, Validators.required],
      dateInspection: [null, Validators.required],
      dateDepartPrevue: [null, Validators.required],
      partieNavireVisitee: [null, Validators.required],
      officierNavire: [null, Validators.required],
      lieuInspection: [null, Validators.required],
      commandant: [null, Validators.required],
      datePv: [null, Validators.required],
      remarque: [null, Validators.required],
      agentPV: [null, Validators.required],

    });

    this.step1InfoPVFrom.get('nomNavire')?.disable();
    //this.step1InfoPVFrom.get('provenance')?.disable();

    this.setNavireAndProvenanceValue();

    this.step1InfoPVFrom.patchValue(JSON.parse(localStorage.getItem('step1PVIPN') as string) ?? {});

  }

  setNavireAndProvenanceValue(){
    this.step1InfoPVFrom.get('nomNavire')?.setValue(this.demand.nomNavire);

    this.step1InfoPVFrom.get('consignataire')?.setValue(this.demand.affreteur);

    this.step1InfoPVFrom.get('provenance')?.setValue(this.demand.provenance);
  }

  ngOnInit(): void {
    this.initForm()
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    this.step1InfoPVFrom.get('nomNavire')?.enable();
    localStorage.setItem('step1PVIPN', JSON.stringify(this.step1InfoPVFrom.value))
    this.stepperService.goToNextStep();
  }
}
