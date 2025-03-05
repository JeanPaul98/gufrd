import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
import {FormArray, FormGroup, UntypedFormBuilder, UntypedFormGroup, Validators,} from '@angular/forms';
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import {ProductModel} from "../../../../../../../../../../../src/app/models/product.model";
import {DemandService} from "../../../../services/demand.service";


@Component({
  selector: 'app-step-02',
  templateUrl: './step-02.component.html',
  styleUrl: './step-02.component.scss',
})
export class Step02Component implements OnInit {
  step2InfoProduitsFrom: UntypedFormGroup = new FormGroup({});
  productsList: WritableSignal<ProductModel[]> = signal<ProductModel[]>([]);
  private service = inject(DemandService);

  constructor(private stepperService: StepperService, private _formBuilder?: UntypedFormBuilder) {
  }

  get products(): FormArray {
    return this.step2InfoProduitsFrom!.get('detPhytosanitaireProduitDtos') as FormArray;
  }

  ngOnInit(): void {
    this.initForm();
    this.getProducts();
  }

  initForm(): void {
    // Initialiser le formulaire
    this.step2InfoProduitsFrom = this._formBuilder!.group({detPhytosanitaireProduitDtos: this._formBuilder!.array([])});

    // Récupérer les données depuis le localStorage
    const savedData = JSON.parse(localStorage.getItem('step2IPPPP') as string);

    // Si des données existent, reconstruire le tableau de produits
    if (savedData && savedData.detPhytosanitaireProduitDtos) {
      savedData.detPhytosanitaireProduitDtos.forEach(() => this.products.push(this.createProductGroup()));

      // Appliquer les valeurs récupérées au formulaire
      this.step2InfoProduitsFrom.patchValue(savedData);
    } else {
      // Ajouter un groupe par défaut si aucune donnée n'est présente
      this.products.push(this.createProductGroup());
    }
  }

  getProductName(code: string): string {
    return this.productsList().find((x) => x.code === code)?.libelle as string;
  }

  createProductGroup(): FormGroup {
    return this._formBuilder!.group({
      libelle: ["", Validators.required],
      quantite: [0, Validators.required],
      fournisseur: ["", Validators.required],
      origine: ["", Validators.required],
      descriptionEnvoi: ["", Validators.required],
      produit: [null],
    });
  }

  getControls() {
    return (this.step2InfoProduitsFrom!.get('detPhytosanitaireProduitDtos') as FormArray).controls;
  }

  getProducts(): void {
    this.service.listProducts().subscribe((res) => {

      this.productsList.set(res as ProductModel[]);
    });
  }

  addProduct(): void {
    this.products.push(this.createProductGroup());
    console.log(this.products.value);

  }

  removeProduct(index: number): void {
    this.products.removeAt(index);
    localStorage.setItem('step2IPPPP', JSON.stringify(this.step2InfoProduitsFrom!.value))
  }

  checkIfFormStep2IsValid(): boolean {
    return this.getControls().length <= 1
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    localStorage.setItem('step2IPPPP', JSON.stringify(this.step2InfoProduitsFrom!.value))
    this.stepperService.goToNextStep();
  }

  // private createNewProductGroup() {
  //   this.createNewProductForm = this._formBuilder!.group({
  //     libelle: ["", Validators.required],
  //     quantite: [0, Validators.required],
  //     fournisseur: ["", Validators.required],
  //     origine: ["", Validators.required],
  //     descriptionEnvoi: ["", Validators.required],
  //   });
  // }
}
