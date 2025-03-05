import {Injectable} from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class HashUrlParserService {

  constructor(private router: Router) {}

  redirectHashUrl(): void {
    const url = window.location.href;
    window.location.replace(url.replace('#/', ''));
  }

  isHashUrl(): boolean {
    return window.location.href.indexOf('#/') > -1;
  }
}
