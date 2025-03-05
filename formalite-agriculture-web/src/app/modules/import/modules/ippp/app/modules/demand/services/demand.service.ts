import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, catchError, finalize, map, Observable, throwError } from 'rxjs';
import { HttpDemandService } from './http-demand.service';
import {DemandAeaaCompletModel, DemandAeaaModel} from "../../../../../../../../models/demand-aeaa.model";
import {BadgesCountModel} from "../../../../../../../../models/badges-count.model";
import {SubmitDemandModel} from "../../../../../../../../models/submit-demand.model";


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
  activeLink:string = localStorage.getItem('currentTabsIPPPLAdmin') ?? "not-submitted";


  //Badges Type  Demand counts
  demandNotSubmittedCountSubject!:BehaviorSubject<number>;
  demandSubmittedCountSubject!:BehaviorSubject<number>;
  demandAcceptedCountSubject!:BehaviorSubject<number>;
  demandRejectedCountSubject!:BehaviorSubject<number>;
  demandProcessedCountSubject!:BehaviorSubject<number>;

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
    this.router.navigate(['/importations/ippp/demand', this.activeLink])
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

  customBack(link: string) {
    this.router.navigate([link]);
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

  acceptDemand(submitDemandModel: SubmitDemandModel): Observable<DemandAeaaModel | undefined> {
    return this.httpDemandService.acceptDemand(submitDemandModel).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }
}
