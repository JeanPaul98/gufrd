import { Injectable } from '@angular/core';
import { HttpCreateService } from './http-create.service';
import { BehaviorSubject, catchError, finalize, map, Observable, of, ReplaySubject, throwError } from 'rxjs';
import { NavireModel } from '../../../../../../../../../../src/app/models/navire.model';
import { DemandAeaaCompletModel } from '../../../../../../../../../../src/app/models/demand-aeaa.model';
import {TypeProductModel} from "../../../../../../../../../../src/app/models/type-product.model";
import {ProductModel, ProductNewModel} from "../../../../../../../../../../src/app/models/product.model";

@Injectable({
  providedIn: 'root'
})
export class CreateService {
  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;

  isEndGoBackSubject: ReplaySubject<number> = new ReplaySubject<number>(1);


  constructor(private httpCreateService: HttpCreateService) {
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

  createDemande(demandInfos: DemandAeaaCompletModel): Observable<DemandAeaaCompletModel> {
    return this.httpCreateService.createDemande(demandInfos).pipe(
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



//   editDemande(demandInfos: DemandAeaaCompletModel): Observable<DemandAeaaCompletModel | undefined> {
//     return this.httpCreateService.editDemande(demandInfos).pipe(
//       map((res) => res.result),
//       catchError((err) => {
//         return throwError(() => err);
//       }),
//       finalize(() => this.isLoadingSubject.next(false))
//     );
//   }
}
