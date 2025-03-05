import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, finalize, map, Observable, ReplaySubject, throwError} from 'rxjs';
import {InspecteurModel} from "../../../../../../../../../../models/admin/inspecteur.model";
import {PVIPPPModel} from "../../../../../../../../../../models/admin/pv-ippp.model";
import {DemandAeaaSimpleModel} from "../../../../../../../../../../models/demand-aeaa.model";
import {NavireModel} from "../../../../../../../../../../models/navire.model";
import {ProductModel, ProductNewModel} from "../../../../../../../../../../models/product.model";
import {TypeProductModel} from "../../../../../../../../../../models/type-product.model";
import {HttpCreatePVService} from "./http-create-pv.service";

@Injectable({
  providedIn: 'root'
})
export class CreatePVService {
  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;

  isEndGoBackSubject: ReplaySubject<number> = new ReplaySubject<number>(1);


  constructor(private httpCreateService: HttpCreatePVService) {
    this.isLoadingSubject = new BehaviorSubject<boolean>(false);
    this.isLoading$ = this.isLoadingSubject.asObservable();
  }

  getNavireInfos(navNumero: string): Observable<NavireModel | undefined> {
    return this.httpCreateService.getNavireInfos(navNumero).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  createDemande(demandInfos: DemandAeaaSimpleModel): Observable<number | undefined> {
    return this.httpCreateService.createDemande(demandInfos).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getInspecteurs(): Observable<InspecteurModel[] | undefined> {
    return this.httpCreateService.getInspecteurs().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  createPV(pvInfos: PVIPPPModel): Observable<PVIPPPModel | undefined> {
    return this.httpCreateService.createPV(pvInfos).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getProducts(): Observable<ProductModel[] | undefined> {
    return this.httpCreateService.getProducts().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getTypesProduct(): Observable<TypeProductModel[] | undefined> {
    return this.httpCreateService.getTypesProduct().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  createProductNew(product: ProductNewModel): Observable<ProductModel> {
    return this.httpCreateService.createProductNew(product).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  editDemande(demandInfos: DemandAeaaSimpleModel): Observable<DemandAeaaSimpleModel | undefined> {
    return this.httpCreateService.editDemande(demandInfos).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }
}
