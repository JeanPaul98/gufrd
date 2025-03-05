import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {inject} from "@angular/core";
import {tap} from "rxjs/operators";
import {Oauth2AuthService} from "./oauth2-auth.service";

export const authExpiredInterceptor: HttpInterceptorFn = (req, next) => {
  const oauth2Service = inject(Oauth2AuthService);
  return next(req).pipe(
    tap({
      error: (err: HttpErrorResponse) => {
        if (err.status === 401 && err.url && oauth2Service.isAuthenticated()) {
          oauth2Service.login();
        }
      }
    })
  );
};
