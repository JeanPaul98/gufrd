import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {tap} from "rxjs/operators";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "../../../../../../../../../src/components/nice-toast/nice-toast.component";
import {finalize} from "rxjs";
import {DemandService} from "../../services/demand.service";
import {DemandAeaaCompletModel} from "../../../../../../../../../src/app/models/demand-aeaa.model";
import {Location} from "@angular/common";

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

  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetailIPPPP') as string);

  constructor(private service: DemandService, private location: Location, private cdr: ChangeDetectorRef) {
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.service.isHideDemandBarSubject.next(true);
      this.location.subscribe(() => {
        this.service.isHideDemandBarSubject.next(false);
      });
    }, 100);
  }

  openModal() {
    this.modalVisible = true;
  }


  onClickBack() {
    this.service.isHideDemandBarSubject.next(false);
    this.service.simpleBack();
  }

  onSubmit() {
    this.loadingSubmit = true;
    this.service.submitDemand({idFormalite: this.demand.idFormalite}).pipe(
      untilDestroyed(this),
      tap(() => {
        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps: {
            texto: 'La demande a été soumise avec succès',
            state: 'success'
          }
        });
      }),
      finalize(() => {
        this.modalVisible = false;
        this.loadingSubmit = false;
      })
    ).subscribe(() => this.onClickBack());
  }

  onCancel() {
    this.modalVisible = false;
    this.loadingSubmit = false;
  }
}
