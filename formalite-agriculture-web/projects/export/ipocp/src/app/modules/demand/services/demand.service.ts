import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DemandService {
  currentRouteSubject = new BehaviorSubject<string>('');
  currentRoute$ = this.currentRouteSubject.asObservable();
  isHideDemandBarSubject = new BehaviorSubject<boolean>(false);
  isHideDemandBar$ = this.isHideDemandBarSubject.asObservable();
  constructor(private router: Router, private location: Location) {}

  back() {
    this.location.back();
    // this.location.onUrlChange((url: string) => {
    //   if (url.includes('demand/create')) {
    //   }
    // });
  }
}
