import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {
  AbstractControl,
  FormArray,
  FormGroup,
  UntypedFormBuilder,
  UntypedFormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {CreatePVService} from "../services/create-pv.service";
import {NgSelectConfig} from "@ng-select/ng-select";
import {StepperService} from "@docs-components/stepper/stepper.service";
import {TypeProductModel} from "../../../../../../../../../../models/type-product.model";
import {UntilDestroy} from "@ngneat/until-destroy";
import {InspecteurModel} from "../../../../../../../../../../models/admin/inspecteur.model";
import _ from "lodash";


@UntilDestroy()
@Component({
  selector: 'app-step03',
  templateUrl: './step03.component.html',
  styleUrl: './step03.component.scss'
})
export class Step03Component implements OnInit {
  step3InfoInspecteurForm!: UntypedFormGroup;
  selectedCar: number = 1;
  typeProducts: WritableSignal<TypeProductModel[]> = signal<TypeProductModel[]>([]);
  inspecteurList: WritableSignal<InspecteurModel[]> = signal<InspecteurModel[]>([]);
  modalVisible = false;
  loadingSubmit = false;
  fileName = '';

  constructor(
    private createPVService: CreatePVService,
    private config: NgSelectConfig,
    private stepperService: StepperService,
    private _formBuilder?: UntypedFormBuilder,
  ) {
    this.config.notFoundText = 'Aucun résultat';
    this.config.clearAllText = 'Vider';
  }

  get inspecteurs(): FormArray {
    return this.step3InfoInspecteurForm.get('detPVInspecteurDtos') as FormArray;
  }

  ngOnInit(): void {
    this.initForm()
    this.getInspecteurs();
  }

  getInspecteurs(): void {
    this.createPVService.getInspecteurs().subscribe((res) => {

      this.inspecteurList.set(res as InspecteurModel[]);
    });
  }

  initForm(): void {
    // Initialiser le formulaire
    this.step3InfoInspecteurForm = this._formBuilder!.group({
      detPVInspecteurDtos: this._formBuilder!.array([])
    });

    // Récupérer les données depuis le localStorage
    const savedData = JSON.parse(localStorage.getItem('step3PVIPCC') as string);

    // Si des données existent, reconstruire le tableau de produits
    if (savedData && savedData.detPVInspecteurDtos) {
      savedData.detPVInspecteurDtos.forEach(() => {
        this.inspecteurs.push(this.createInspecteurGroup());
      });

      // Appliquer les valeurs récupérées au formulaire
      this.step3InfoInspecteurForm.patchValue(savedData);
    } else {
      // Ajouter un groupe par défaut si aucune donnée n'est présente
      this.inspecteurs.push(this.createInspecteurGroup());
    }
  }

  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }

  getInspecteurName(id: string): string {
    return this.inspecteurList().find(x => x.id === _.toNumber(id))?.nomInspecteur as string;
  }


  openModal() {

    this.modalVisible = true;
  }

  createInspecteurGroup(): FormGroup {
    return this._formBuilder!.group({
      idInspecteur: [null, Validators.required],
      fonction: [null, Validators.required],
      nomInspecteur: [null]
    });
  }


  setNomInspecteurByForm(formId: string, nom: string) {

  }

  triggerInspecteurIdChange(id: string) {

  }


  minValueValidator(min: number): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;

      if (isNaN(value)) {
        return {notANumber: {value}}; // If the value is not a number
      }

      return value >= min ? null : {minValue: {min, actual: value}};
    };
  }


  getControls() {
    return (this.step3InfoInspecteurForm.get('detPVInspecteurDtos') as FormArray).controls;
  }

  addProduct(): void {
    this.inspecteurs.push(this.createInspecteurGroup());
    /*this.getControls()[this.getControls().length-1].get('nomInspecteur')!.setValue();*/
    console.log("addtion",this.getControls());

  }

  removeProduct(index: number): void {
    this.inspecteurs.removeAt(index);
    localStorage.setItem('step3PVIPCC', JSON.stringify(this.step3InfoInspecteurForm.value))
  }


  checkIfFormStep2IsValid(): boolean {
    return this.getControls().length <= 1
  }


  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    localStorage.setItem('step3PVIPCC', JSON.stringify(this.step3InfoInspecteurForm.value))
    this.stepperService.goToNextStep();
  }


  onInspecteurSelectChange($event: InspecteurModel) {
    console.log("$event",$event)
    this.getControls()[this.getControls().length-1].get('nomInspecteur')!.setValue($event.nomInspecteur);
    this.getControls()[this.getControls().length-1].get('idInspecteur')!.setValue($event.id);
  }
}
