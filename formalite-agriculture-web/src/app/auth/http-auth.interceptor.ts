import {HttpInterceptorFn} from '@angular/common/http';
import {inject} from "@angular/core";
import {KeycloakService} from "keycloak-angular";
import {Oauth2AuthService} from "./oauth2-auth.service";

export const httpAuthInterceptor: HttpInterceptorFn = (req, next) => {
  const oauth2Service = inject(Oauth2AuthService);
  const keycloak = inject(KeycloakService);
  const token = keycloak.getKeycloakInstance().token;
  if(token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  return next(req);
};
