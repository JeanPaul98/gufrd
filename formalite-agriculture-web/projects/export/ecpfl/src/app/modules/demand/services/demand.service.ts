import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class DemandService {
  currentRouteSubject = new BehaviorSubject<string>('');
  currentRoute$ = this.currentRouteSubject.asObservable();
  constructor(private router: Router, private locator: Location) {}

  back() {
    // this.router.navigate(['/demands']);
    this.locator.back();
  }
}
