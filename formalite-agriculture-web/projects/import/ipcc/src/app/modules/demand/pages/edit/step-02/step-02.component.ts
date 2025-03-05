import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, signal, WritableSignal} from '@angular/core';
import {FormArray, FormGroup, UntypedFormBuilder, UntypedFormGroup, Validators,} from '@angular/forms';
import {NgSelectConfig} from "@ng-select/ng-select";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import _ from "lodash";
import {toast} from "ngx-sonner";
import {tap} from "rxjs/operators";
import {DemandAeaaCompletModel} from "../../../../../../../../../../src/app/models/demand-aeaa.model";
import {ProductModel} from "../../../../../../../../../../src/app/models/product.model";
import {TypeProductModel} from "../../../../../../../../../../src/app/models/type-product.model";
import {NiceToastComponent} from "../../../../../../../../../../src/components/nice-toast/nice-toast.component";
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';
import {CreateService} from "../../create/services/create.service";


@UntilDestroy()
@Component({
  selector: 'app-step-02',
  templateUrl: './step-02.component.html',
  styleUrl: './step-02.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Step02Component  implements OnInit {
  step2InfoProduitsFrom!: UntypedFormGroup;
  createNewProductForm!: UntypedFormGroup;
  constructor(
    private _cdf: ChangeDetectorRef,
    private createService: CreateService,
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
  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

  typeProducts: WritableSignal<TypeProductModel[]> = signal<TypeProductModel[]>([]);
  productsList: WritableSignal<ProductModel[]> = signal<ProductModel[]>([]);
  modalVisible = false;
  loadingSubmit = false;

  fileName = '';

  onFileSelected(event: Event) {
    const target: HTMLInputElement = event.target as HTMLInputElement;

    const file: File | null | undefined = target?.files?.item(0);

    console.log('====================================');
    console.log(target?.files);
    console.log('====================================');
    if (file) {
      this.fileName = file.name;
    }
  }

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
    this.step2InfoProduitsFrom = this._formBuilder!.group({
      detAutorisationProduitDtos: this._formBuilder!.array([])
    });

    console.log('initForm', this.demand.detAutorisationProduitDtos);

    this.createNewProductGroup();
    // Récupérer les données depuis le localStorage
    const savedData = JSON.parse(localStorage.getItem('step2EditIPCC') as string)

    // Si des données existent, reconstruire le tableau de produits
    if (savedData && (savedData.detAutorisationProduitDtos)) {
      savedData.detAutorisationProduitDtos.forEach(() => {
        this.products.push(this.createProductGroup());
      });

      // Appliquer les valeurs récupérées au formulaire
      this.step2InfoProduitsFrom.patchValue(savedData);

    }else if(this.demand.detAutorisationProduitDtos) {
      const detAutorisationProduitDtos = _.map(this.demand.detAutorisationProduitDtos, (item) => {
        return {
          ..._.omit(item, ['produit']),
          libelle: item.produit.code,
        }
      });
      detAutorisationProduitDtos.forEach(() => {
        this.products.push(this.createProductGroup());
      });

      console.log('detAutorisationProduitDtos', detAutorisationProduitDtos);
      this.products.push(this.createProductGroup());
      // Appliquer les valeurs récupérées au formulaire
      this.step2InfoProduitsFrom.patchValue({
        detAutorisationProduitDtos: [...detAutorisationProduitDtos]
      });

      console.log('step2InfoProduitsFrom', this.step2InfoProduitsFrom.value);
    }

    else {
      // Ajouter un groupe par défaut si aucune donnée n'est présente
      this.products.push(this.createProductGroup());
    }
  }

  get products(): FormArray {
    return this.step2InfoProduitsFrom.get('detAutorisationProduitDtos') as FormArray;
  }

  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }

  openModal() {

    this.modalVisible = true;
  }

  createProductGroup(): FormGroup {
    return this._formBuilder!.group({
      libelle: [null, Validators.required],
      quantite: [null, Validators.required],
      unite: [null, Validators.required],
      nombreCarton: [null, Validators.required],
      origine: [null, Validators.required],
      dateProduction: [null, Validators.required],
    });
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
    return (this.step2InfoProduitsFrom.get('detAutorisationProduitDtos') as FormArray).controls;
  }

  addProduct(): void {
    this.products.push(this.createProductGroup());
    console.log(this.products.value);

  }

  removeProduct(index: number): void {
    this.products.removeAt(index);
    localStorage.setItem('step2EditIPCC', JSON.stringify(this.step2InfoProduitsFrom.value))
  }
  checkIfFormIsValid(): boolean {
    return this.step2InfoProduitsFrom.pristine || this.step2InfoProduitsFrom.invalid;
  }

  checkIfFormStep2IsValid(): boolean {
    return this.getControls().length  <= 1
  }


  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    localStorage.setItem('step2EditIPCC', JSON.stringify(this.step2InfoProduitsFrom.value))
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
}
