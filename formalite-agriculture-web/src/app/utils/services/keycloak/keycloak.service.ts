import {Injectable} from '@angular/core';
import Keycloak from "keycloak-js";
import {environment} from "../../../../environments/environment";
import {JwtModel} from "../../../models/jwt.model";
import {KeycloakProfile} from "./KeycloakProfile";

export const keycloakConfig = {
  url: environment.KEYCLOAK_URL,
  realm: environment.KEYCLOAK_REALM,
  clientId: environment.KEYCLOAK_CLIENT_ID
};

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  // constructor(private authService: AuthService) {
  // }

  private _keycloak: Keycloak | undefined;

  get keycloak() {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: keycloakConfig.url,
        realm: keycloakConfig.realm,
        clientId: keycloakConfig.clientId
      });
    }
    return this._keycloak;
  }

  private _profile: KeycloakProfile | undefined;

  get profile(): KeycloakProfile | undefined {
    return this._profile;
  }

  set profile(value: KeycloakProfile | undefined) {
    this._profile = value;
  }

  async init() {
    console.log('Initializing Keycloak...');
    const authenticated = await this.keycloak?.init({onLoad: 'login-required'});
    if (authenticated) {
      // @ts-ignore
      let jwt = new JwtModel();
      this.profile = (await this.keycloak?.loadUserProfile()) as KeycloakProfile;
      this.profile.token = this.keycloak?.token;
      jwt.accessToken = this.keycloak?.token!!;
      jwt.tokenType = "Bearer ";
      localStorage.setItem(environment.USERDATA_KEY, JSON.stringify(jwt));
    }
  }

  login() {
    return this.keycloak?.login();
  }

  logout() {
    return this.keycloak?.logout({redirectUri: 'http://localhost:4210/'});
  }

  getUserRoles(){
    return this.keycloak?.tokenParsed?.resource_access?.['GUFORD-AGRI-AGENT']?.roles;
  }
}
