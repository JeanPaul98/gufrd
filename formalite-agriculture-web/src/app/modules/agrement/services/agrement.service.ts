import {inject, Injectable} from '@angular/core';
import {BehaviorSubject, catchError, finalize, Observable, throwError} from "rxjs";
import {map} from "rxjs/operators";
import {DemandAgrementModel} from "../../../models/agrement.model";
import {DemandAeaaModel} from "../../../models/demand-aeaa.model";
import {CreateAgrementModel} from "../../../models/demand-agrement.model";

import {SocieteModel} from "../../../models/societe.model";
import {TypeAgrementModel} from "../../../models/type-agrement.model";
import {HttpAgrementService} from "./http-agrement.service";

@Injectable({
  providedIn: 'root'
})
export class AgrementService {

  currentDemandsSubject = new BehaviorSubject<DemandAeaaModel[]>([]);


  isLoadingSubject!: BehaviorSubject<boolean>;
  httpAgrementService = inject(HttpAgrementService);

  getSocietes(): Observable<SocieteModel[] | undefined> {
    return this.httpAgrementService.getSocietes().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getAgrements(): Observable<TypeAgrementModel[] | undefined> {
    return this.httpAgrementService.getAgrements().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  createDemande(demandInfos: CreateAgrementModel): Observable<number | undefined> {
    return this.httpAgrementService.createDemande(demandInfos).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandSubmitted(): Observable<DemandAgrementModel[] | undefined> {
    return this.httpAgrementService.getDemandSubmitted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandAccepted(): Observable<DemandAgrementModel[] | undefined> {
    return this.httpAgrementService.getDemandAccepted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandProcessed(): Observable<DemandAgrementModel[] | undefined> {
    return this.httpAgrementService.getDemandProcessed().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }
}
