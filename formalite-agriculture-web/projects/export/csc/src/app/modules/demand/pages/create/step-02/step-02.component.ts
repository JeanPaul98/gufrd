import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  OnInit,
  signal,
  Signal,
  WritableSignal
} from '@angular/core';
import { StepperService } from '../../../../../../../../../../src/components/stepper/stepper.service';
import {
  FormArray,
  FormGroup,
  UntypedFormBuilder,
  UntypedFormGroup,
  Validators,
} from '@angular/forms';
import {NgSelectConfig} from "@ng-select/ng-select";
import {TypeProductModel} from "../../../../../../../../../../src/app/models/type-product.model";
import {ProductModel} from "../../../../../../../../../../src/app/models/product.model";
import {CreateService} from "../services/create.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {tap} from "rxjs/operators";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "../../../../../../../../../../src/components/nice-toast/nice-toast.component";
import {finalize} from "rxjs";


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

  typeProducts: WritableSignal<TypeProductModel[]> = signal<TypeProductModel[]>([]);
  productsList: WritableSignal<ProductModel[]> = signal<ProductModel[]>([]);
  modalVisible = false;
  loadingSubmit = false;
  unites: string[] = ['kg', 'L','Tonne'];

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
      detCertificatProduitDtos: this._formBuilder!.array([])
    });

    this.createNewProductGroup();
    // Récupérer les données depuis le localStorage
    const savedData = JSON.parse(localStorage.getItem('step2iocav') as string);

    // Si des données existent, reconstruire le tableau de produits
    if (savedData && savedData.detCertificatProduitDtos) {
      savedData.detCertificatProduitDtos.forEach(() => {
        this.products.push(this.createProductGroup());
      });

      // Appliquer les valeurs récupérées au formulaire
      this.step2InfoProduitsFrom.patchValue(savedData);
    } else {
      // Ajouter un groupe par défaut si aucune donnée n'est présente
      this.products.push(this.createProductGroup());
    }
  }

  get products(): FormArray {
    return this.step2InfoProduitsFrom.get('detCertificatProduitDtos') as FormArray;
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
      // libelle: [null, Validators.required],
      code: [null],
      nomProduit: [null],
      quantite: [null, Validators.required],
      // unite: [null, Validators.required],
      origine: [null, Validators.required],
      conteneur: [null, Validators.required],
      poidsTotal: [null, Validators.required],
      natureProduit: [null, Validators.required],
      // sexe: [null, Validators.required],
      // nombre: [null, Validators.required],
      // race: [null, Validators.required],
      // espece: [null, Validators.required],  
      // nombreCarton: [null, Validators.required],
      // origine: [null, Validators.required],
      // dateProduction: [null, Validators.required],
      // fournisseur: [null, Validators.required],
      // descriptionEnvoi: [null, Validators.required],
      // nombreColis: [null, Validators.required],
      // conditionnement: [null, Validators.required],
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
    return (this.step2InfoProduitsFrom.get('detCertificatProduitDtos') as FormArray).controls;
  }

  addProduct(): void {
    this.products.push(this.createProductGroup());
    console.log(this.products.value);

  }

  removeProduct(index: number): void {
    this.products.removeAt(index);
    localStorage.setItem('step2iocav', JSON.stringify(this.step2InfoProduitsFrom.value))
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
    localStorage.setItem('step2iocav', JSON.stringify(this.step2InfoProduitsFrom.value))
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
    this.getControls()[this.getControls().length-1].get('code')!.setValue($event.code);
  }
}
