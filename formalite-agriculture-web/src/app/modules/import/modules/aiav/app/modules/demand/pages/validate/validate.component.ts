import {Location} from "@angular/common";
import {Component, OnInit} from '@angular/core';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {toast} from "ngx-sonner";
import {finalize} from "rxjs";
import {tap} from "rxjs/operators";
import {DemandAeaaCompletModel} from "../../../../../../../../../models/demand-aeaa.model";
import {DemandService} from "../../services/demand.service";

@UntilDestroy()
@Component({
  selector: 'app-validate',
  templateUrl: './validate.component.html',
  styleUrl: './validate.component.scss'
})
export class ValidateComponent implements OnInit {
  loadingSubmit: boolean = false;
  loadingDemands: boolean = false;
  modalVisible: boolean = false;
  rejectDemandForm!: UntypedFormGroup;
  modalRejectVisible: boolean = false;

  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);

  constructor(private demandService: DemandService, private location: Location, private router: Router, private _formBuilder?: UntypedFormBuilder) {
    console.log('constructor');

  }


  ngOnInit(): void {
    setTimeout(() => {
      this.demandService.isHideDemandBarSubject.next(true);
      this.location.subscribe(() => {
        this.demandService.isHideDemandBarSubject.next(false);
      });
    }, 100);

    this.initForm();
  }

  initForm(): void {
    // Initialiser le formulaire
    this.rejectDemandForm = this._formBuilder!.group({
      motif: [null, Validators.required],
    });
  }

  rejectDemand() {
    this.loadingSubmit = true;
    this.demandService.nowRejectDemand({
      idFormalite: this.demand.idFormalite,
      motifRejet: this.rejectDemandForm.value.motif,
      // numGenere: this.demand.numGenerer,
    }).pipe(
      untilDestroyed(this),
      tap(() => {
        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps: {
            texto: 'Demande rejetée',
            state: 'success'
          }
        });
      }),
      finalize(() => {
        this.modalRejectVisible = false;
        this.loadingSubmit = false;
      })
    ).subscribe((res) => {
      this.onClickBack();
    });
  }

  openModal() {

    this.modalVisible = true;
  }

  openModalReject() {
    this.modalRejectVisible = true;
  }


  onClickBack() {
    // this.currentStep = 1;
    // this.onClickPrevious()
    // localStorage.setItem('currentStepAIAV', JSON.stringify(this.currentStep))
    this.demandService.isHideDemandBarSubject.next(false);
    this.demandService.simpleBack();

  }

  onSubmit() {
    this.loadingSubmit = true;
    this.demandService.nowAcceptDemand({
      idFormalite: this.demand.idFormalite,
      // numGenere: this.demand.numGenerer,
    }).pipe(
      untilDestroyed(this),
      tap(() => {

        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps: {
            texto: 'Demande soumise',
            state: 'success'
          }
        });

      }),
      finalize(() => {
        this.modalVisible = false;
        this.loadingSubmit = false;
      })
    ).subscribe((res) => {
      this.onClickBack();
    });

  }

  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }

  onCancelReject() {
    this.modalRejectVisible = false;
    this.loadingSubmit = false;
  }
}
