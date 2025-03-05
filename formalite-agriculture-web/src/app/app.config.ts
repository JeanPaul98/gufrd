import {LocationStrategy, PathLocationStrategy} from "@angular/common";
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptors} from "@angular/common/http";
import {APP_INITIALIZER, ApplicationConfig, importProvidersFrom, provideZoneChangeDetection} from '@angular/core';
import {provideAnimations} from '@angular/platform-browser/animations';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {
  provideRouter,
  withEnabledBlockingInitialNavigation,
  withInMemoryScrolling,
  withRouterConfig,
  withViewTransitions
} from '@angular/router';

import {DropdownModule, SidebarModule} from '@coreui/angular';
import {IconSetService} from '@coreui/icons-angular';
import {provideSvgIcons} from '@ngneat/svg-icon';
import {provideEffects} from "@ngrx/effects";
import {provideState, provideStore} from "@ngrx/store";
import {provideStoreDevtools} from "@ngrx/store-devtools";
import {Novu} from "@novu/js";
import {KeycloakAngularModule, KeycloakBearerInterceptor, KeycloakService} from "keycloak-angular";
import {environment} from "../environments/environment";
import {societesGetAll$} from "../store/societe/societe.effect";
import {societesFeature} from "../store/societe/societe.reducer";
import {routes} from './app.routes';
import {httpAuthInterceptor} from "./auth/http-auth.interceptor";

import {fawSettingsIcon} from "./svg/settings";
import {KCAuthGuard} from "./utils/guards/kc-auth.guard";
import {HashUrlParserService} from "./utils/urls-manager/hash-url-parser.service";

// export function kcFactory(keycloakService: KeycloakService) {
//   return () => keycloakService.init();
// }
const keycloakService = new KeycloakService();

export function initializeApp(hashUrlRedirectionService: HashUrlParserService,init: HashUrlParserService) {
  //  if (hashUrlRedirectionService.isHashUrl()) {
  //   return hashUrlRedirectionService.redirectHashUrl();
  // }

  if(hashUrlRedirectionService.isHashUrl()) {
    return () => hashUrlRedirectionService.redirectHashUrl();
  }
  return () => {};
}

export function initNovu() {
  return async () => {
    const novu = new Novu({
      subscriberId: environment.novu.subscriberId,
      applicationIdentifier: environment.novu.applicationIdentifier

    });
    const response = await novu.notifications.list({
      limit: 30,
    });

    const notifications = response?.data?.notifications
    console.log("notifications", notifications)

    novu.on("notifications.notification_received", (data) => {
      console.log("new notification =>", data);
    });

    novu.on("notifications.unread_count_changed", (data) => {
      console.log("new unread notifications count =>", data);
    });
  }
}

export function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: environment.keycloak.url,
        realm: environment.keycloak.realm,
        clientId: environment.keycloak.clientId,
      },
      initOptions: {
        onLoad: 'check-sso',
        checkLoginIframe: false,

        pkceMethod: 'S256',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silentCheckSsoRedirectUri.html'
      },
      enableBearerInterceptor: true,
      bearerPrefix: 'Bearer',

      shouldAddToken: (request) => {
        const { method, url } = request;

        const isGetRequest = 'GET' === method.toUpperCase();
        const acceptablePaths = ['/assets', '/clients/public'];
        const isAcceptablePathMatch = acceptablePaths.some((path) =>
          url.includes(path)
        );

        return !(isGetRequest && isAcceptablePathMatch);
      }
    });
}


export const appConfig: ApplicationConfig = {
  providers: [
    KCAuthGuard,
    provideStore(),
    provideState(societesFeature),
    provideEffects({
      societesGetAll$: societesGetAll$
    }),
    provideStoreDevtools(),
    {
      provide: KeycloakService,
      useValue: keycloakService
    },
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    },
    HashUrlParserService,
    {provide: APP_INITIALIZER, useFactory: initializeApp, deps: [HashUrlParserService], multi: true},
    { provide: LocationStrategy, useClass: PathLocationStrategy },
    { provide: HTTP_INTERCEPTORS, useClass: KeycloakBearerInterceptor, multi: true },
    // {provide: APP_INITIALIZER, deps: [KeycloakService], useFactory: kcFactory, multi: true},
    provideRouter(routes,
      withRouterConfig({
        onSameUrlNavigation: 'reload'
      }),
      withInMemoryScrolling({
        scrollPositionRestoration: 'top',
        anchorScrolling: 'enabled'
      }),
      withEnabledBlockingInitialNavigation(),
      withViewTransitions(),

      // withHashLocation()
    ),
    importProvidersFrom(SidebarModule, DropdownModule, KeycloakAngularModule),
    IconSetService,
    provideAnimations(),
    provideHttpClient(withInterceptors([httpAuthInterceptor])),
    provideZoneChangeDetection({eventCoalescing: true}),
    provideAnimationsAsync(),
    provideHttpClient(),
    provideSvgIcons([fawSettingsIcon])
  ]
};
