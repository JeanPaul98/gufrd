import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot,} from '@angular/router';
import {KeycloakAuthGuard, KeycloakService} from "keycloak-angular";

@Injectable()
export class KCAuthGuard extends KeycloakAuthGuard {
  constructor(
    protected override router: Router,
    protected override keycloakAngular: KeycloakService
  ) {
    super(router, keycloakAngular);
  }

  isAccessAllowed(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean> {
    return new Promise((resolve, reject) => {
      let permission;
      console.log("route.data", route.data, this.authenticated);
      if (!this.authenticated) {
        this.keycloakAngular.login().catch((e) => console.error(e));
        return reject(false);
      }

      const requiredRoles: string[] = route.data['roles'];
      if (!requiredRoles || requiredRoles.length === 0) {
        permission = true;
      } else {
        if (!this.roles || this.roles.length === 0) {
          permission = false
        }
        // simplfy this

        permission = requiredRoles.some((role) => this.roles.indexOf(role) > -1);
      }
      if(!permission){
        // this.router.navigate(['/']).then(r => console.log("redirecting"));
        return reject(false);
      }

      console.log("permission", permission);
      console.log("requiredRoles", requiredRoles);
      console.log("this.roles", this.roles);
      resolve(permission)
    });
  }
}
