import {Injectable} from '@angular/core';
import {CreateHttpService} from './create-http.service';
import {BehaviorSubject, catchError, finalize, map, Observable, of, ReplaySubject, throwError} from 'rxjs';
import {NavireModel} from '../../../../../../../../../../src/app/models/navire.model';
import {inits, IpRequest} from "../../../../../../../../../../src/app/requests/ip.request";
import {DemandAeaaSimpleModel} from "../../../../../../../../../../src/app/models/demand-aeaa.model";
import {ProductModel} from "../../../../../../../../../../src/app/models/product.model";

@Injectable({
  providedIn: 'root'
})
export class CreateService {
  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;
  isEndGoBackSubject: ReplaySubject<number> = new ReplaySubject<number>(1);
  account$: BehaviorSubject<IpRequest> = new BehaviorSubject<IpRequest>(inits);
  isCurrentFormValid$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private httpService: CreateHttpService) {
    this.isLoadingSubject = new BehaviorSubject<boolean>(false);
    this.isLoading$ = this.isLoadingSubject.asObservable();
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

  createDemand(request: IpRequest): Observable<number | undefined> {
    return this.httpService.create(request).pipe(
      map((res) => res.result),
      catchError((err) => {
        console.error(err);
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
}
