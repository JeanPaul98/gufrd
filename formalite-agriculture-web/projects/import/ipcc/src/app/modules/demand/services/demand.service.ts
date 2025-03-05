import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {toast} from "ngx-sonner";
import {BehaviorSubject, catchError, finalize, map, Observable, of, ReplaySubject, throwError} from "rxjs";
import {BadgesCountModel} from "../../../../../../../../src/app/models/badges-count.model";
import {
  DemandAeaaCompletModel,
  DemandAeaaModel,
  DemandAeaaSimpleModel
} from "../../../../../../../../src/app/models/demand-aeaa.model";
import {NavireModel} from "../../../../../../../../src/app/models/navire.model";
import {PaiementUrlModel} from "../../../../../../../../src/app/models/paiement-url.model";
import {ProductModel} from "../../../../../../../../src/app/models/product.model";
import {SubmitDemandModel} from "../../../../../../../../src/app/models/submit-demand.model";
import {inits, IpRequest} from "../../../../../../../../src/app/requests/ip.request";
import {
  DemandUploadFilesService
} from "../../../../../../../../src/components/demand-upload-files/demand-upload-files.service";
import {NiceToastComponent} from "../../../../../../../../src/components/nice-toast/nice-toast.component";
import {DemandHttpService} from "./demand-http.service";


@Injectable({
  providedIn: 'root',
})
export class DemandService {
  currentRouteSubject:BehaviorSubject<string>;
  currentRoute$: Observable<string>;
  isHideDemandBarSubject:BehaviorSubject<boolean>;
  isHideDemandBar$:Observable<boolean>;
  currentDemandsSubject = new BehaviorSubject<DemandAeaaModel[]>([]);
  currentDemandSubject = new BehaviorSubject<DemandAeaaCompletModel>({} as DemandAeaaCompletModel);
  activeLink:string = localStorage.getItem('currentTabsIPCC') ?? "not-submitted";



  //Badges Type  Demand counts
  demandNotSubmittedCountSubject!:BehaviorSubject<number>;
  demandSubmittedCountSubject!:BehaviorSubject<number>;
  demandAcceptedCountSubject!:BehaviorSubject<number>;
  demandRejectedCountSubject!:BehaviorSubject<number>;
  demandProcessedCountSubject!:BehaviorSubject<number>;
  isEndGoBackSubject: ReplaySubject<number> = new ReplaySubject<number>(1);
  isCurrentFormValid$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  account$: BehaviorSubject<IpRequest> = new BehaviorSubject<IpRequest>(inits);


  isLoadingSubject!: BehaviorSubject<boolean>;

  constructor(private router: Router, private httpService: DemandHttpService,private demandUploadFilesService: DemandUploadFilesService) {

    this.demandNotSubmittedCountSubject = new BehaviorSubject<number>(0);
    this.demandSubmittedCountSubject = new BehaviorSubject<number>(0);
    this.demandAcceptedCountSubject = new BehaviorSubject<number>(0);
    this.demandRejectedCountSubject = new BehaviorSubject<number>(0);
    this.demandProcessedCountSubject = new BehaviorSubject<number>(0);


    this.currentRouteSubject = new BehaviorSubject<string>('');
    this.currentRoute$ = this.currentRouteSubject.asObservable();

    this.isHideDemandBarSubject = new BehaviorSubject<boolean>(false);
    this.isHideDemandBar$ = this.isHideDemandBarSubject.asObservable();
  }

  back() {
    // this.location.back();
    this.isHideDemandBarSubject.next(false);
    this.activeLink = localStorage.getItem('currentTabsIPCC') ?? "not-submitted";
    this.router.navigate(['demand', this.activeLink])
    // this.location.onUrlChange((url: string) => {
    //   console.log("urlurlurl ",url);

    //   if (url.includes('demand/create')) {
    //     this.router.navigate(['demand', 'not-submitted']);
    //   }
    // });
  }

  getDemandBadgeCount(): Observable<BadgesCountModel | undefined> {
    return this.httpService.getDemandBadgeCount().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
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

  createDemand(request: IpRequest): Observable<number | undefined> {
    return this.httpService.create(request).pipe(
      map((res) => res.result),
      catchError((err) => {
        console.error(err);
        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps: {
            texto: err.error.error,
            state: 'error'
          }
        });
        return of(undefined);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }



  getDemandNotSubmitted(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.getDemandNotSubmitted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandSubmitted(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.getDemandSubmitted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandAccepted(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.getDemandAccepted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getPayementUrl(idFormalite: number
  ): Observable<PaiementUrlModel | undefined> {
    return this.httpService.getPayementUrl(idFormalite).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
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

  listProducts(): Observable<ProductModel[] | undefined> {
    return this.httpService.products().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  updateDemand(request: IpRequest): Observable<DemandAeaaSimpleModel | undefined> {
    // this.isLoadingSubject.next(true);
    const demand = JSON.parse(localStorage.getItem('demandDetailIPCC') as string);
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

  getDemandRejected(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.getDemandRejected().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandProcessed(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpService.getDemandProcessed().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  nowSubmitDemand(submitDemandModel: SubmitDemandModel): Observable<DemandAeaaModel | undefined> {
    return this.httpService.nowSubmitDemand(submitDemandModel).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
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
