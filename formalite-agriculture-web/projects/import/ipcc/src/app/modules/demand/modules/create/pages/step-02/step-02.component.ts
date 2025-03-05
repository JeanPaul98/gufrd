import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
import {FormArray, FormGroup, UntypedFormBuilder, UntypedFormGroup, Validators,} from '@angular/forms';
import {ProductModel} from "../../../../../../../../../../../src/app/models/product.model";
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
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
    const savedData = JSON.parse(localStorage.getItem('step2IPCC') as string);

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


  createProductGroup(): FormGroup {
    return this._formBuilder!.group({
      libelle: [null],
      code: [null],
      quantite: [0, Validators.required],
      fournisseur: ["", Validators.required],
      origine: ["", Validators.required],
      // conteneur: ["", Validators.required],
      // descriptionEnvoi: ["", Validators.required],
      // nombreColis: [0, Validators.required],
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
    localStorage.setItem('step2IPCC', JSON.stringify(this.step2InfoProduitsFrom!.value))
  }

  checkIfFormStep2IsValid(): boolean {
    return this.getControls().length <= 1
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep();
  }

  onClickNext() {
    localStorage.setItem('step2IPCC', JSON.stringify(this.step2InfoProduitsFrom!.value))
    this.stepperService.goToNextStep();
  }

  onProductSelectChange($event: ProductModel) {
    console.log("$event",$event)
    this.getControls()[this.getControls().length-1].get('libelle')!.setValue($event.libelle);
    this.getControls()[this.getControls().length-1].get('code')!.setValue($event.code);
  }
}
