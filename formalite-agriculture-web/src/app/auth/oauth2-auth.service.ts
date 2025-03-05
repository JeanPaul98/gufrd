import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {computed, inject, Injectable, signal, WritableSignal} from '@angular/core';
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import dayjs, {Dayjs} from "dayjs";
import Keycloak from "keycloak-js";
import {catchError, from, interval, Observable, of, shareReplay, Subject, switchMap} from "rxjs";
import {fromPromise} from "rxjs/internal/observable/innerFrom";
import {environment} from "../../environments/environment";
import {State} from "../models/ForAuthAndRoute/state.model";
import {ConnectedUser} from "../models/ForAuthAndRoute/user.model";


@UntilDestroy()
@Injectable({
  providedIn: 'root'
})
export class Oauth2AuthService {

  http: HttpClient = inject(HttpClient);

  notConnected: string = "NOT_CONNECTED";

  private MIN_TOKEN_VALIDITY_MILLISECONDS = 10000;

  private fetchUserHttp$ = new Observable<ConnectedUser>();

  private lastSeen$ = new Subject<State<Dayjs>>();
  lastSeen = this.lastSeen$.asObservable();

  accessToken: string | undefined;

  private keycloak: Keycloak = new Keycloak({
    url: environment.keycloak.url,
    realm: environment.keycloak.realm,
    clientId: environment.keycloak.clientId
  });


  constructor() {
    // this.initFetchUserCaching(false);
  }

  private fetchUser$: WritableSignal<State<ConnectedUser>> =
    signal(State.Builder<ConnectedUser>().forSuccess({email: this.notConnected}));
  fetchUser = computed(() => this.fetchUser$());

  public initAuthentication(): void {
    from(this.keycloak.init({
      flow: "standard",
      onLoad: "login-required",
      // redirectUri: "http://localhost:4210/",
      // silentCheckSsoRedirectUri: window.location.origin + "/assets/silent-check-sso.html"
    })).pipe(
      untilDestroyed(this)
    )
      .subscribe(isAuthenticated => {
        console.log("isAuthenticated", isAuthenticated);
        if (isAuthenticated) {
          this.accessToken = this.keycloak.token;
          this.fetch();
          this.initUpdateTokenRefresh();

          alert("You are authenticated");
          // if (this.authModalRef) {
          //   this.authModalRef.close();
          // }
          // this.sseService.subscribe(this.accessToken!);
        } else {
          alert("You are not authenticated");
          // this.authModalRef = this.modalService
          //   .open(AuthModalComponent, {centered: true, backdrop: "static"});
        }
      });
  }

  initUpdateTokenRefresh(): void {
    interval(this.MIN_TOKEN_VALIDITY_MILLISECONDS)
      .pipe(
        switchMap(() => fromPromise(this.keycloak.updateToken(this.MIN_TOKEN_VALIDITY_MILLISECONDS))),
        untilDestroyed(this)
      ).subscribe({
      next: refreshed => {
        if (refreshed) {
          this.accessToken = this.keycloak.token;
        }
      },
      error: err => console.error("Failed to refresh token" + err)
    });
  }

  initFetchUserCaching(forceResync: boolean): void {
    const params = new HttpParams().set("forceResync", forceResync);
    this.fetchUserHttp$ = this.http.get<ConnectedUser>(`${environment.apiUrl}/users/get-authenticated-user`, {params: params})
      .pipe(
        catchError(() => of({email: this.notConnected})),
        shareReplay(1)
      );
  }

  fetch(): void {
    this.fetchUserHttp$
      .subscribe({
        next: user => this.fetchUser$.set(State.Builder<ConnectedUser>().forSuccess(user)),
        error: (error: HttpErrorResponse) => {
          this.fetchUser$.set(State.Builder<ConnectedUser>().forError(error))
        }
      });
  }

  isAuthenticated(): boolean {
    return this.keycloak.authenticated!;
  }

  login(): void {
    this.keycloak.login();
  }

  logout(): void {
    this.keycloak.logout();
  }

  goToProfilePage(): void {
    this.keycloak.accountManagement();
  }

  handleLastSeen(userPublicId: string): void {
    const params = new HttpParams().set("publicId", userPublicId);
    this.http.get<Date>(`${environment.apiUrl}/users/get-last-seen`, {params})
      .subscribe({
        next: lastSeen => this.lastSeen$.next(State.Builder<Dayjs>().forSuccess(dayjs(lastSeen))),
        error: err => this.lastSeen$.next(State.Builder<Dayjs>().forError(err))
      });
  }

}
