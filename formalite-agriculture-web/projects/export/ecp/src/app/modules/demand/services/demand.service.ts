import {Location} from '@angular/common';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {BehaviorSubject, catchError, finalize, map, Observable, of, switchMap, throwError} from 'rxjs';
import {HttpDemandService} from './http-demand.service';
import {DemandAeaaCompletModel, DemandAeaaModel} from '../../../../../../../../src/app/models/demand-aeaa.model';
import {SubmitDemandModel} from "../../../../../../../../src/app/models/submit-demand.model";
import {BadgesCountModel} from "../../../../../../../../src/app/models/badges-count.model";
import {PaiementUrlModel} from "../../../../../../../../src/app/models/paiement-url.model";

@Injectable({
  providedIn: 'root',
})
export class DemandService {
  currentRouteSubject: BehaviorSubject<string>;
  currentRoute$: Observable<string>;
  isHideDemandBarSubject: BehaviorSubject<boolean>;
  isHideDemandBar$: Observable<boolean>;
  currentDemandsSubject = new BehaviorSubject<DemandAeaaModel[]>([]);
  currentDemandSubject = new BehaviorSubject<DemandAeaaCompletModel>({} as DemandAeaaCompletModel);
  activeLink:string = localStorage.getItem('currentTabsAAEA') ?? "not-submitted";


  //Badges Type  Demand counts
  demandNotSubmittedCountSubject!: BehaviorSubject<number>;
  demandSubmittedCountSubject!: BehaviorSubject<number>;
  demandAcceptedCountSubject!: BehaviorSubject<number>;
  demandRejectedCountSubject!: BehaviorSubject<number>;
  demandProcessedCountSubject!: BehaviorSubject<number>;

  isLoadingSubject!: BehaviorSubject<boolean>;

  constructor(private router: Router, private location: Location, private httpDemandService: HttpDemandService) {

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
    this.activeLink = localStorage.getItem('currentTabsAAEA') ?? "not-submitted";
    this.router.navigate(['demand', this.activeLink])

    // this.location.onUrlChange((url: string) => {
    //   console.log("urlurlurl ",url);

    //   if (url.includes('demand/create')) {
    //     this.router.navigate(['demand', 'not-submitted']);
    //   }
    // });
  }

  simpleBack() {
    this.location.back();
  }

  getDemandBadgeCount(): Observable<BadgesCountModel | undefined> {
    return this.httpDemandService.getDemandBadgeCount().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getPayementUrl(idFormalite: number
  ): Observable<PaiementUrlModel | undefined> {
    return this.httpDemandService.getPayementUrl(idFormalite).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandNotSubmitted(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpDemandService.getDemandNotSubmitted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandSubmitted(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpDemandService.getDemandSubmitted().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandAccepted(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpDemandService.getDemandAccepted().pipe(
      map((res) => res.result),
      switchMap((res) => {
        const demandAeaaWithUrl = res.map((demand: DemandAeaaModel) => {
          this.getPayementUrl(demand.idFormalite).pipe(
            map((resUrl) => {
              demand.urlPaiement = resUrl?.url as string;
            })
          ).subscribe();
          return demand
        });
        return of(demandAeaaWithUrl);
      }),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandRejected(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpDemandService.getDemandRejected().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  getDemandProcessed(): Observable<DemandAeaaModel[] | undefined> {
    return this.httpDemandService.getDemandProcessed().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  nowSubmitDemand(submitDemandModel: SubmitDemandModel): Observable<DemandAeaaModel | undefined> {
    return this.httpDemandService.nowSubmitDemand(submitDemandModel).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  nowDeleteDemand(submitDemandModel: SubmitDemandModel): Observable<DemandAeaaModel | undefined> {
    return this.httpDemandService.nowDeleteDemand(submitDemandModel).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }
}
