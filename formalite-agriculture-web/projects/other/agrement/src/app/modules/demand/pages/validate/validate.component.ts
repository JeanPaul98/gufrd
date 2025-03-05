import {Location} from "@angular/common";
import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {toast} from "ngx-sonner";
import {finalize} from "rxjs";
import {tap} from "rxjs/operators";
import {DemandAeaaCompletModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {NiceToastComponent} from "../../../../../../../../../src/components/nice-toast/nice-toast.component";
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


  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);
  constructor(private demandService: DemandService,private location: Location, private router: Router, private cdr: ChangeDetectorRef) {
    console.log('constructor');

  }


  ngOnInit(): void {
    setTimeout(() => {
    this.demandService.isHideDemandBarSubject.next(true);
      this.location.subscribe(() => {
        this.demandService.isHideDemandBarSubject.next(false);
      });
    }, 100);
  }

  openModal() {

    this.modalVisible = true;
  }



  onClickBack() {
    // this.currentStep = 1;
    // this.onClickPrevious()
    // localStorage.setItem('currentStepAGREMENT', JSON.stringify(this.currentStep))
    this.demandService.isHideDemandBarSubject.next(false);
    this.demandService.simpleBack();

  }

  onSubmit() {
    this.loadingSubmit = true;
    this.demandService.nowSubmitDemand({
      idFormalite: this.demand.idFormalite,
      // numGenere: this.demand.numGenerer,
    }).pipe(
      untilDestroyed(this),
      tap(() => {

        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps:{
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
}
