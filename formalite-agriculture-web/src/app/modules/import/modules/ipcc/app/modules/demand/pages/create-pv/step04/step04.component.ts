import {ChangeDetectorRef, Component, OnInit, signal, WritableSignal} from '@angular/core';

import {
  PVIPCCModel,
  PVInspecteurModel,
  PVProduitModel, ProduitModel
} from "../../../../../../../../../../models/admin/pv-ipcc.model";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {StepperService} from "@docs-components/stepper/stepper.service";
import {Router} from "@angular/router";

import {CreatePVService} from "../services/create-pv.service";
import {finalize} from "rxjs";
import {tap} from "rxjs/operators";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";
import _ from "lodash";
import {
  AbstractControl,
  FormArray,
  FormGroup,
  UntypedFormBuilder,
  UntypedFormGroup, ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {TypeProductModel} from "../../../../../../../../../../models/type-product.model";
import {InspecteurModel} from "../../../../../../../../../../models/admin/inspecteur.model";
import {NgSelectConfig} from "@ng-select/ng-select";
import {ProductModel} from "../../../../../../../../../../models/product.model";
import {SocieteModel} from "../../../../../../../../../../models/societe.model";

@UntilDestroy()
@Component({
  selector: 'app-step04',
  templateUrl: './step04.component.html',
  styleUrl: './step04.component.scss'
})
export class Step04Component implements OnInit {
  step4InfoTraitementProduits!: UntypedFormGroup;
  createNewProductForm!: UntypedFormGroup;
  constructor(
    private _cdf: ChangeDetectorRef,
    private createService: CreatePVService,
    private config: NgSelectConfig,
    private stepperService: StepperService,
    private _formBuilder?: UntypedFormBuilder,
  ) {
    this.config.notFoundText = 'Aucun résultat';
    this.config.clearAllText = 'Vider';

  }
  selectedCar: number = 1;
  cars = [
    { id: 1, name: 'Certificat Phytosanitaire' },
    { id: 2, name: 'Certificat de Fumigation' },
    { id: 3, name: 'Certificat d’origine' },
    { id: 4, name: 'Certificat de qualité et poids' },
  ];

  typeProducts: WritableSignal<TypeProductModel[]> = signal<TypeProductModel[]>([]);
  productsList: WritableSignal<ProductModel[]> = signal<ProductModel[]>([]);
  societesList: WritableSignal<SocieteModel[]> = signal<SocieteModel[]>([]);
  modalVisible = false;
  loadingSubmit = false;

  fileName = '';


  ngOnInit(): void {
    this.initForm()
    this.getTypesProduct();
    this.getProducts();
    this.getSocietes();
  }

  getProducts(): void {
    this.createService.getProducts().subscribe((res) => {

      this.productsList.set(res as ProductModel[]);
    });
  }

  getSocietes(): void {
    this.createService.getSocietes().subscribe((res) => {
      this.societesList.set(res as SocieteModel[]);
    });
  }

  getTypesProduct(): void {
    this.createService.getTypesProduct().subscribe((res) => {
      this.typeProducts.set(res as TypeProductModel[]);
    });
  }

  initForm(): void {
    // Initialiser le formulaire
    this.step4InfoTraitementProduits = this._formBuilder!.group({
      detTraitementProduitDtos: this._formBuilder!.array([])
    });

    this.createNewProductGroup();
    // Récupérer les données depuis le localStorage
    const savedData = JSON.parse(localStorage.getItem('step4PVIPCC') as string);

    // Si des données existent, reconstruire le tableau de produits
    if (savedData && savedData.detTraitementProduitDtos) {
      savedData.detTraitementProduitDtos.forEach(() => {
        this.products.push(this.createProductGroup());
      });

      // Appliquer les valeurs récupérées au formulaire
      this.step4InfoTraitementProduits.patchValue(savedData);
    } else {
      // Ajouter un groupe par défaut si aucune donnée n'est présente
      this.products.push(this.createProductGroup());
    }
  }

  get products(): FormArray {
    return this.step4InfoTraitementProduits.get('detTraitementProduitDtos') as FormArray;
  }



  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }

  getProductLibelle(code: string): string {
    return this.productsList().find(x => x.code === code)?.libelle as string;
  }



  openModal() {

    this.modalVisible = true;
  }

  createProductGroup(): FormGroup {
    return this._formBuilder!.group({
      produit: [null],
      nomProduit: [null],
      societe: [null],
      code: [null, Validators.required],
      substanceActive: [null, Validators.required],
      temperature: [null, Validators.required],
      nomSociete: [null],
      natureTraitement: [null, Validators.required],
      heureDebTrait: [null, Validators.required],
      concentration: [null, Validators.required],
      duree: [null, Validators.required],
    });
  }


  minValueValidator(min: number): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;

      if (isNaN(value)) {
        return { notANumber: { value } }; // If the value is not a number
      }

      return value >= min ? null : { minValue: { min, actual: value } };
    };
  }

  createNewProductGroup() {
    this.createNewProductForm = this._formBuilder!.group({
      libelle: [null, Validators.required],
      code: [null, Validators.required],
      description: [null, Validators.required],
      typeProduit: [null, Validators.required],
    });
  }

  getControls() {
    return (this.step4InfoTraitementProduits.get('detTraitementProduitDtos') as FormArray).controls;
  }

  addProduct(): void {
    this.products.push(this.createProductGroup());
    console.log(this.products.value);

  }

  removeProduct(index: number): void {
    this.products.removeAt(index);
    localStorage.setItem('step4PVIPCC', JSON.stringify(this.step4InfoTraitementProduits.value))
  }
  checkIfFormIsValid(): boolean {
    return this.step4InfoTraitementProduits.pristine || this.step4InfoTraitementProduits.invalid;
  }

  checkIfFormStep2IsValid(): boolean {
    return this.getControls().length  <= 1
  }


  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    localStorage.setItem('step4PVIPCC', JSON.stringify(this.step4InfoTraitementProduits.value))
    this.stepperService.goToNextStep();
  }


  createNew() {
    this.loadingSubmit = true;
    this.createService.createProductNew(this.createNewProductForm.value)
      .pipe(
        untilDestroyed(this),
        tap({
          next: (res) => {
            toast.custom(NiceToastComponent, {
              position: 'top-center',
              componentProps:{
                texto: 'Production créée',
                state: 'success'
              }
            });
            this.getProducts();
          },
          error: (err) => {
            toast.custom(NiceToastComponent, {
              position: 'top-center',
              componentProps:{
                texto: 'Erreur lors de la création',
                state: 'error'
              }
            });
          },
          complete: () => {
            this.loadingSubmit = false;
            this.modalVisible = false;
          }
        }),


      )
      .subscribe((res) => {
        console.log(res);
      });
  }

  onProductSelectChange(product: ProductModel) {
    console.log("product",product)
    this.getControls()[this.getControls().length-1].get('nomProduit')!.setValue(product.libelle);
    this.getControls()[this.getControls().length-1].get('code')!.setValue(product.code);

    this.getControls()[this.getControls().length-1].get('produit')!.setValue({
      libelle: product.libelle,
      code: product.code
    });
  }

  onSocieteSelectChange(societe: SocieteModel) {
    console.log("societe",societe)
    this.getControls()[this.getControls().length-1].get('nomSociete')!.setValue(societe.raisonSociale);
    this.getControls()[this.getControls().length-1].get('societe')!.setValue(societe);
  }
}
