import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
import {ReactiveFormsModule, UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ButtonDirective, FormControlDirective} from "@coreui/angular";
import {StepperService} from "@docs-components/stepper/stepper.service";
import {NgLabelTemplateDirective, NgOptionTemplateDirective, NgSelectComponent} from "@ng-select/ng-select";
import {SocieteModel} from "../../../../../models/societe.model";
import {TypeAgrementModel} from "../../../../../models/type-agrement.model";
import {AgrementService} from "../../../services/agrement.service";

@Component({
  selector: 'app-step1',
  standalone: true,
  imports: [
    ButtonDirective,
    FormControlDirective,
    NgLabelTemplateDirective,
    NgOptionTemplateDirective,
    NgSelectComponent,
    ReactiveFormsModule
  ],
  templateUrl: './step1.component.html',
  styleUrl: './step1.component.scss'
})
export class Step1Component implements OnInit{
  societes: WritableSignal<SocieteModel[]> = signal<SocieteModel[]>([]);
  agrements: WritableSignal<TypeAgrementModel[]> = signal<TypeAgrementModel[]>([]);

  step1InfoAgrementFrom!: UntypedFormGroup;

  agrementService = inject(AgrementService);
  _formBuilder = inject(UntypedFormBuilder);
  stepperService = inject(StepperService);
  router = inject(Router);

  ngOnInit(): void {
    this.initForm()
    this.societeList();
    this.agrementList();
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

    // this.onChangeNumAtp();
  }

  agrementList() {
    this.agrementService.getAgrements().subscribe((res) => {
      this.agrements.set(res as TypeAgrementModel[]);
    });
  }

  societeList(): void {
    this.agrementService.getSocietes().subscribe((res) => {
      this.societes.set(res as SocieteModel[]);
    });
  }

  onClickNext() {
    localStorage.setItem('step1AGREMENT', JSON.stringify(this.step1InfoAgrementFrom.value))
    this.stepperService.goToNextStep();

  }


  checkIfFormIsValid(): boolean {
    return  this.step1InfoAgrementFrom.invalid;
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

  onSubmit() {
    console.log(this.step1InfoAgrementFrom.value);
  }

}
