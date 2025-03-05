import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, finalize, map, Observable, of, ReplaySubject, throwError} from "rxjs";
import {Router} from "@angular/router";
import {
  DemandAeaaCompletModel,
  DemandAeaaModel,
  DemandAeaaSimpleModel
} from "../../../../../../../../src/app/models/demand-aeaa.model";
import {DemandHttpService} from "./demand-http.service";
import {SubmitDemandModel} from "../../../../../../../../src/app/models/submit-demand.model";
import {NavireModel} from "../../../../../../../../src/app/models/navire.model";
import {inits, IpRequest} from "../../../../../../../../src/app/requests/ip.request";
import {ProductModel} from "../../../../../../../../src/app/models/product.model";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "../../../../../../../../src/components/nice-toast/nice-toast.component";
import {BadgesCountModel} from "../../../../../../../../src/app/models/badges-count.model";
import {Location} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class DemandService {
  currentRoute$: Observable<string>;
  isHideDemandBar$: Observable<boolean>;
  isLoadingSubject!: BehaviorSubject<boolean>;
  currentRouteSubject: BehaviorSubject<string>;
  isHideDemandBarSubject: BehaviorSubject<boolean>;
  currentDemandsSubject = new BehaviorSubject<DemandAeaaModel[]>([]);
  isEndGoBackSubject: ReplaySubject<number> = new ReplaySubject<number>(1);
  account$: BehaviorSubject<IpRequest> = new BehaviorSubject<IpRequest>(inits);
  isCurrentFormValid$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentDemandSubject = new BehaviorSubject<DemandAeaaCompletModel>({} as DemandAeaaCompletModel);

  constructor(private router: Router, private httpService: DemandHttpService, private location: Location) {
    this.currentRouteSubject = new BehaviorSubject<string>('');
    this.currentRoute$ = this.currentRouteSubject.asObservable();

    this.isHideDemandBarSubject = new BehaviorSubject<boolean>(false);
    this.isHideDemandBar$ = this.isHideDemandBarSubject.asObservable();
  }

  back() {
    this.router.navigate(['demand/not-submitted'])
  }

  simpleBack() {
    this.location.back();
  }

  getShipInfos(number: string): Observable<NavireModel | undefined> {
    return this.httpService.shipInfos(number).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  submitDemand(submitDemandModel: SubmitDemandModel): Observable<DemandAeaaModel | undefined> {
    return this.httpService.submit(submitDemandModel).pipe(
      map((res) => res.result),
      catchError((err) => {
        console.error(err);
        this.errorHandler(err);
        return of(undefined);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandNotSubmitted(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.notSubmitted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandSubmitted(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.submitted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandAccepted(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.accepted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandRejected(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.rejected().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandProcessed(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.processed().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  updateDemand(request: IpRequest): Observable<DemandAeaaSimpleModel | undefined> {
    // this.isLoadingSubject.next(true);
    const demand = JSON.parse(localStorage.getItem('demandDetailIPPPP') as string);
    request.idFormalite = demand.idFormalite;
    request.typeInspPhyto = demand.typeInspPhyto;
    request.refTypeInspectionPhyto = demand.refTypeInspectionPhyto;
    request.numGenerer = demand.numGenerer;
    return this.httpService.update(request).pipe(
      map((res) => res.result),
      catchError((err) => {
        console.error(err);
        this.errorHandler(err);
        return of(undefined);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  updateAccount = (part: Partial<IpRequest>, isFormValid: boolean) => {
    const currentAccount = this.account$.value;
    const updatedAccount = {...currentAccount, ...part};
    this.account$.next(updatedAccount);
    this.isCurrentFormValid$.next(isFormValid);
  };

  listProducts(): Observable<ProductModel[] | undefined> {
    return this.httpService.products().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  createDemand(request: IpRequest): Observable<number | undefined> {
    return this.httpService.create(request).pipe(
      map((res) => res.result),
      catchError((err) => {
        console.error(err);
        this.errorHandler(err);
        return of(undefined);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  successHandler(message: string) {
    toast.custom(
      NiceToastComponent, {
        position: 'top-center',
        componentProps: {
          texto: message,
          state: 'success'
        }
      }
    );
  }

  getDemandBadgeCount(): Observable<BadgesCountModel | undefined> {
    return this.httpService.badgeCount().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  private errorHandler(err: any) {
    toast.custom(
      NiceToastComponent, {
        position: 'top-center',
        componentProps: {
          texto: err.error.message,
          state: 'error'
        }
      }
    );
  }
}
