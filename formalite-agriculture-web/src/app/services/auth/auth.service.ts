import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';

export const JWT_NAME = 'token';
const API_URL = environment.apiUrl + '/auth/';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // currentUserSubject!: BehaviorSubject<AuthModel>;
  // currentUser!: Observable<AuthModel>;

  constructor(
    private http: HttpClient,
    private router: Router,
    // private jwtHelperService: JwtHelperService
  ) {
    this.initCurrentUser();
  }

  // login(data: LoginModel): Observable<LoginResponse> {
  //   return this.http.post<LoginResponse>(API_URL + 'login', data);
  // }

  logout(): void {
    //this.currentUserSubject.next();
    window.localStorage.removeItem(JWT_NAME);
    window.localStorage.removeItem('current-user');
    location.reload();
  }

  initCurrentUser(): void {
    const utilisateur = JSON.parse(
      localStorage.getItem('current-user') || '{}'
    );
    // this.currentUserSubject = new BehaviorSubject<AuthModel>(utilisateur);
    // this.currentUser = this.currentUserSubject.asObservable();
  }

  // isAuthenticated(): boolean {
  //   const token = localStorage.getItem(JWT_NAME);
  //   if (!token) {
  //     return false;
  //   }
  //
  //   return !this.jwtHelperService.isTokenExpired(token);
  //
  // }

  // getObservableCurrentUser(): Observable<AuthModel> {
  //   return of(this.currentUserSubject.getValue());
  // }
  //
  // getCurrentUser(): AuthModel {
  //   return this.currentUserSubject.getValue();
  // }

  redirectToLogin(): void {
    this.router.navigate(['/auth/login']);
  }

  /**
   * Reset password
   * @param email email
   */
  resetPassword(email: string) {
    return email;
  }
}
