import {ChangeDetectorRef, Component, OnInit, signal, WritableSignal} from '@angular/core';
import {FormArray, FormGroup, UntypedFormBuilder, UntypedFormGroup, Validators,} from '@angular/forms';
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import _ from "lodash";
import {ProductModel} from "../../../../../../../../../../../src/app/requests/ip.request";
import {TypeProductModel} from "../../../../../../../../../../../src/app/models/type-product.model";
import {NgSelectConfig} from "@ng-select/ng-select";
import {DemandService} from "../../../../services/demand.service";


@Component({
  selector: 'app-edit-step-02',
  templateUrl: './step-02.component.html',
  styleUrl: './step-02.component.scss',
})
export class Step02Component implements OnInit {
  step2InfoProductFrom!: UntypedFormGroup;
  selectedCar: number = 1;
  cars = [
    {id: 1, name: 'Certificat Phytosanitaire'},
    {id: 2, name: 'Certificat de Fumigation'},
    {id: 3, name: 'Certificat d’origine'},
    {id: 4, name: 'Certificat de qualité et poids'},
  ];
  demand = JSON.parse(localStorage.getItem('demandDetailIPPPP') as string);
  typeProducts: WritableSignal<TypeProductModel[]> = signal<TypeProductModel[]>([]);
  productsList: WritableSignal<ProductModel[]> = signal<ProductModel[]>([]);
  modalVisible = false;
  loadingSubmit = false;
  fileName = '';

  constructor(
    private _cdf: ChangeDetectorRef,
    private service: DemandService,
    private config: NgSelectConfig,
    private stepperService: StepperService,
    private _formBuilder?: UntypedFormBuilder,
  ) {
    this.config.notFoundText = 'Aucun résultat';
    this.config.clearAllText = 'Vider';

  }

  get products(): FormArray {
    return this.step2InfoProductFrom.get('detPhytosanitaireProduitDtos') as FormArray;
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    // Initialiser le formulaire
    this.step2InfoProductFrom = this._formBuilder!.group({detPhytosanitaireProduitDtos: this._formBuilder!.array([])});

    // Récupérer les données depuis le localStorage
    const savedData = JSON.parse(localStorage.getItem('step2EditIPPPP') as string)

    // Si des données existent, reconstruire le tableau de produits
    if (savedData && (savedData.detPhytosanitaireProduitDtos)) {
      savedData.detPhytosanitaireProduitDtos.forEach(() => {
        this.products.push(this.createProductGroup());
      });

      // Appliquer les valeurs récupérées au formulaire
      this.step2InfoProductFrom.patchValue(savedData);

    } else if (this.demand.detPhytosanitaireProduitDtos) {
      const detPhytosanitaireProduitDtos = _.map(this.demand.detPhytosanitaireProduitDtos, (item) => {
        return {
          ..._.omit(item, ['produit']),
          libelle: item.produit.code,
        }
      });

      detPhytosanitaireProduitDtos.forEach(() => {
        this.products.push(this.createProductGroup());
      });

      this.products.push(this.createProductGroup());

      // Appliquer les valeurs récupérées au formulaire
      this.step2InfoProductFrom.patchValue({
        detPhytosanitaireProduitDtos: [...detPhytosanitaireProduitDtos]
      });

    } else {
      // Ajouter un groupe par défaut si aucune donnée n'est présente
      this.products.push(this.createProductGroup());
    }
  }

  createProductGroup(): FormGroup {
    return this._formBuilder!.group({
      libelle: ["", Validators.required],
      quantite: [0, Validators.required],
      fournisseur: ["", Validators.required],
      origine: ["", Validators.required],
      conteneur: ["", Validators.required],
      descriptionEnvoi: ["", Validators.required],
      nombreColis: [0, Validators.required],
    });
  }

  getControls() {
    return (this.step2InfoProductFrom.get('detPhytosanitaireProduitDtos') as FormArray).controls;
  }

  addProduct(): void {
    this.products.push(this.createProductGroup());
    console.log(this.products.value);

  }

  removeProduct(index: number): void {
    this.products.removeAt(index);
    localStorage.setItem('step2EditIPPPP', JSON.stringify(this.step2InfoProductFrom.value))
  }

  checkIfFormIsValid(): boolean {
    return this.step2InfoProductFrom.pristine || this.step2InfoProductFrom.invalid;
  }

  checkIfFormStep2IsValid(): boolean {
    return this.getControls().length <= 1
  }


  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    localStorage.setItem('step2EditIPPPP', JSON.stringify(this.step2InfoProductFrom.value))
    this.stepperService.goToNextStep();
  }

}
