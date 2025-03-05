import {Location} from "@angular/common";
import {Component, inject, OnInit, signal, WritableSignal} from '@angular/core';
import {ReactiveFormsModule, UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {ButtonDirective, FormControlDirective} from "@coreui/angular";
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";
import {NgLabelTemplateDirective, NgOptionTemplateDirective, NgSelectComponent} from "@ng-select/ng-select";
import {toast} from "ngx-sonner";
import {TypeSocieteModel} from "../../../../models/societe.model";
import {SocietyService} from "../../services/society.service";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  standalone: true,
  imports: [
    ButtonDirective,
    NgLabelTemplateDirective,
    NgOptionTemplateDirective,
    NgSelectComponent,
    ReactiveFormsModule,
    FormControlDirective
  ],
  styleUrl: './create.component.scss'
})
export class CreateComponent implements OnInit {
  societeForm: UntypedFormGroup;

  typeSocietyService = inject(SocietyService);
  location = inject(Location);
  societyService = inject(SocietyService);
  _formBuilder = inject(UntypedFormBuilder);
  router = inject(ActivatedRoute);
  typeSocietyList: WritableSignal<TypeSocieteModel[]> = signal([]);

  ngOnInit(): void {
    this.initForm();
    this.getSocietyList();
  }

  getSocietyList() {
    this.typeSocietyService.getTypeSocietyCreated().subscribe(res => {
      console.log("getTypeSocietyCreated", res);
      this.typeSocietyList.set(res);
    })
  }

  initForm() {
    this.societeForm = this._formBuilder!.group({
      raisonSociale: [null, [Validators.required]],
      numeroEnreg: [null, [Validators.required]],
      typeSocieteFm: [null],
      typeSociete:[null],
      nif: [null, [Validators.required]],
      adresse: [null, [Validators.required]],
      contact: [null, [Validators.required]],
      email: [null, [Validators.required]],
    });
  }

  checkIfFormIsValid() {
    return this.societeForm.invalid;
  }

  onSocieteSelectChange(typeSociete: TypeSocieteModel) {
    this.societeForm.get('typeSocieteFm')?.setValue(typeSociete);
    this.societeForm.get('typeSociete')?.setValue(typeSociete.id);
  }

  onSubmit() {
    console.log("onSubmit", this.societeForm.value);
    this.societyService.createSociety(this.societeForm.value).subscribe(
      {
        next: data => {
          console.log(data);
          toast.custom(NiceToastComponent, {
            position: 'top-right',
            componentProps:{
              texto: 'Nouvelle Société crée avec succès',
              state: 'success'
            }
          });
        },
        error: (err) => {
          console.log("err", err);
          toast.custom(NiceToastComponent, {
            position: 'top-right',
            componentProps:{
              texto: 'Une erreur est survenue lors de la création de la société, veuillez réessayer !',
              state: 'error'
            }
          });
        },
        complete: () => {

          this.location.back();

        }
      }
    );
  }
}
