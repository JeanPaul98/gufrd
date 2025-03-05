import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, finalize, map, Observable, of, ReplaySubject, throwError} from 'rxjs';
import {NavireModel} from '../../../../../../../../../../src/app/models/navire.model';
import {DemandAeaaSimpleModel} from '../../../../../../../../../../src/app/models/demand-aeaa.model';
import {CreateHttpService} from "./create-http.service";
import {inits, IpRequest} from "../../../../../../../../../../src/app/requests/ip.request";
import {toast} from "ngx-sonner";
import {NiceToastComponent} from "../../../../../../../../../../src/components/nice-toast/nice-toast.component";

@Injectable({
  providedIn: 'root'
})
export class CreateService {
  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;
  isEndGoBackSubject: ReplaySubject<number> = new ReplaySubject<number>(1);
  account$: BehaviorSubject<IpRequest> = new BehaviorSubject<IpRequest>(inits);
  isCurrentFormValid$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private httpCreateService: CreateHttpService) {
    this.isLoadingSubject = new BehaviorSubject<boolean>(false);
    this.isLoading$ = this.isLoadingSubject.asObservable();
  }

  getShipInfos(number: string): Observable<NavireModel | undefined> {
    return this.httpCreateService.getShipInfos(number).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  createDemand(request: IpRequest): Observable<DemandAeaaSimpleModel | undefined> {
    return this.httpCreateService.create(request).pipe(
      map((res) => res.result),
      catchError((err) => {
        console.error(err);
        toast.custom(
          NiceToastComponent, {
            position: 'top-center',
            componentProps: {
              texto: err.error.message,
              state: 'error'
            }
          }
        );
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
