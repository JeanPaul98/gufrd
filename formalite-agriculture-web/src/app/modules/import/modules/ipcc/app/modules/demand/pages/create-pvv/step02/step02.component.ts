import {ChangeDetectorRef, Component, OnInit, signal, WritableSignal} from '@angular/core';
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
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";
import {StepperService} from "@docs-components/stepper/stepper.service";
import {NgSelectConfig} from "@ng-select/ng-select";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {toast} from "ngx-sonner";
import {tap} from "rxjs/operators";
import {ProductModel} from "../../../../../../../../../../models/product.model";
import {TypeProductModel} from "../../../../../../../../../../models/type-product.model";
import {CreatePVService} from "../services/create-pv.service";

@UntilDestroy()
@Component({
  selector: 'app-step02',
  templateUrl: './step02.component.html',
  styleUrl: './step02.component.scss'
})
export class Step02Component implements OnInit {
  step2InfoProduitsForm!: UntypedFormGroup;
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
  modalVisible = false;
  loadingSubmit = false;

  fileName = '';


  ngOnInit(): void {
    this.initForm()
    this.getTypesProduct();
    this.getProducts();
  }

  getProducts(): void {
    this.createService.getProducts().subscribe((res) => {

      this.productsList.set(res as ProductModel[]);
    });
  }

  getTypesProduct(): void {
    this.createService.getTypesProduct().subscribe((res) => {
      this.typeProducts.set(res as TypeProductModel[]);
    });
  }

  initForm(): void {
    // Initialiser le formulaire
    this.step2InfoProduitsForm = this._formBuilder!.group({
      detPvProduitDtoList: this._formBuilder!.array([])
    });

    this.createNewProductGroup();
    // Récupérer les données depuis le localStorage
    const savedData = JSON.parse(localStorage.getItem('step2PVIPCC') as string);

    // Si des données existent, reconstruire le tableau de produits
    if (savedData && savedData.detPvProduitDtoList) {
      savedData.detPvProduitDtoList.forEach(() => {
        this.products.push(this.createProductGroup());
      });

      // Appliquer les valeurs récupérées au formulaire
      this.step2InfoProduitsForm.patchValue(savedData);
    } else {
      // Ajouter un groupe par défaut si aucune donnée n'est présente
      this.products.push(this.createProductGroup());
    }
  }

  get products(): FormArray {
    return this.step2InfoProduitsForm.get('detPvProduitDtoList') as FormArray;
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
      quantite: [null, this.minValueValidator(1)],
      emplacement: [null, Validators.required],
      nomProduit: [null],
      descriptionEnvoie: [null, Validators.required],
      mesure: [null, Validators.required],
      codProduit: [null],
      origine: [null, Validators.required]
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
    return (this.step2InfoProduitsForm.get('detPvProduitDtoList') as FormArray).controls;
  }

  addProduct(): void {
    this.products.push(this.createProductGroup());
    console.log(this.products.value);

  }

  removeProduct(index: number): void {
    this.products.removeAt(index);
    localStorage.setItem('step2PVIPCC', JSON.stringify(this.step2InfoProduitsForm.value))
  }
  checkIfFormIsValid(): boolean {
    return this.step2InfoProduitsForm.pristine || this.step2InfoProduitsForm.invalid;
  }

  checkIfFormStep2IsValid(): boolean {
    return this.getControls().length  <= 1
  }


  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    localStorage.setItem('step2PVIPCC', JSON.stringify(this.step2InfoProduitsForm.value))
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

  onProductSelectChange($event: ProductModel) {
    console.log("$event",$event)
    this.getControls()[this.getControls().length-1].get('nomProduit')!.setValue($event.libelle);
    this.getControls()[this.getControls().length-1].get('codProduit')!.setValue($event.code);
  }
}
