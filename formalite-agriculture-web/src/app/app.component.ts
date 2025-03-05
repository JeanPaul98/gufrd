import {Component, inject, OnInit} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import {Ability, PureAbility} from "@casl/ability";
import {AbilityModule} from "@casl/angular";

import {IconSetService} from '@coreui/icons-angular';
// import {Client} from "@novu/framework";
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";
import {NgxSonnerToaster} from "ngx-sonner";
import {Oauth2AuthService} from "./auth/oauth2-auth.service";
import {iconSubset} from './icons/icon-subset';

@Component({
  selector: 'app-root',
  template: '<router-outlet /><ngx-sonner-toaster position="top-center"></ngx-sonner-toaster>',
  standalone: true,
  imports: [RouterOutlet, NgxSonnerToaster, AbilityModule],
  providers: [
    { provide: Ability, useValue: new Ability() },
    { provide: PureAbility, useExisting: Ability }
  ]
})
export class AppComponent implements OnInit {
  title = 'CoreUI Angular Admin Template';
  private oauth2Service = inject(Oauth2AuthService);
  private isLoggedIn!: boolean;
  public userProfile: KeycloakProfile | null = null;

  constructor(
    private router: Router,
    private titleService: Title,
    private iconSetService: IconSetService,
    private readonly keycloak: KeycloakService
  ) {
    this.titleService.setTitle(this.title);
    // iconSet singleton
    this.iconSetService.icons = { ...iconSubset };

  }

  public async ngOnInit() {
    // this.initAuthentication();



    this.isLoggedIn = await this.keycloak.isLoggedIn();

    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
      localStorage.setItem('user-profile', JSON.stringify(this.userProfile))
    }else{
      this.login();
    }
    this.router.events.subscribe((evt) => {


      if (!(evt instanceof NavigationEnd)) {
        return;
      }
    });
  }

  private initAuthentication(): void {
    this.oauth2Service.initAuthentication();
  }

  public login() {
    this.keycloak.login();
  }

  public logout() {
    this.keycloak.logout();
  }
}
