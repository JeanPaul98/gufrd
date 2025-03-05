import {ApplicationConfig, importProvidersFrom, provideZoneChangeDetection} from '@angular/core';
import {
  provideRouter,
  withEnabledBlockingInitialNavigation,
  withHashLocation,
  withInMemoryScrolling,
  withRouterConfig,
  withViewTransitions
} from '@angular/router';

import {routes} from './app.routes';
import {DropdownModule, SidebarModule} from "@coreui/angular";
import {IconSetService} from "@coreui/icons-angular";
import {provideAnimations} from "@angular/platform-browser/animations";
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import {provideSvgIcons} from "@ngneat/svg-icon";
import {settingsIcon} from "../../../../../src/app/svg/settings";
import {httpInterceptor} from "../../../../../src/app/utils/interceptors/http.interceptor";

export const appConfig: ApplicationConfig = {
  providers: [
    importProvidersFrom(SidebarModule, DropdownModule),
    IconSetService,
    provideAnimations(),
    provideHttpClient(withInterceptors([httpInterceptor])),
    provideSvgIcons([settingsIcon]),
    provideZoneChangeDetection({eventCoalescing: true}),
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
      withHashLocation()
    )
  ]
};
