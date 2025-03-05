import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, catchError, finalize, map, Observable, throwError } from 'rxjs';
import { HttpDemandService } from './http-demand.service';
import {DemandAeaaCompletModel, DemandAeaaModel} from "../../../../../../../../models/demand-aeaa.model";
import {BadgesCountModel} from "../../../../../../../../models/badges-count.model";
import {SubmitDemandModel} from "../../../../../../../../models/submit-demand.model";
import {tap} from "rxjs/operators";


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
    this.router.navigate(['/importations/aeaa/demand', 'submitted'])
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

  nowAcceptDemand(submitDemandModel: SubmitDemandModel): Observable<DemandAeaaModel | undefined> {
    return this.httpDemandService.nowAcceptDemand(submitDemandModel).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  nowRejectDemand(submitDemandModel: SubmitDemandModel): Observable<DemandAeaaModel | undefined> {
    return this.httpDemandService.nowRejectDemand(submitDemandModel).pipe(
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

  generateAutorisation(idFormalite:number): Observable<string | undefined> {
    return this.httpDemandService.generateAutorisation(idFormalite).pipe(
        map((res) => res),
        catchError((err) => {
          return throwError(() => err);
        }),
        tap((blob) => {

          console.log('res', blob);
        }),
        // Map the blob response to a downloadable action
        map((blob) => {
          const url = window.URL.createObjectURL(blob as Blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = `FORMALITE_${idFormalite}_AUTORISATION_ENLEVEMENT.pdf`;

          document.body.appendChild(a);
          a.click(); // Trigger download
          window.open(url); // Open in new tab for preview

          document.body.removeChild(a); // Clean up the anchor element
          window.URL.revokeObjectURL(url); // Clean up the blob URL

          return url; // Return the URL for further use if necessary
        }),
        finalize(() => this.isLoadingSubject.next(false))
    );
  }
}
