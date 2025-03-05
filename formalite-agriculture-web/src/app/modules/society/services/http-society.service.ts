import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpResponseModel} from "../../../models/http-response.model";
import {SocieteModel, TypeSocieteModel} from "../../../models/societe.model";
import {BaseHttpService} from "../../../services/base-http.service";

@Injectable({
  providedIn: 'root'
})
export class HttpSocietyService extends  BaseHttpService{
  constructor(private http: HttpClient) {
    super();
  }

  createSociete(societe: SocieteModel): Observable<HttpResponseModel<SocieteModel>> {
    return this.http.post<HttpResponseModel<SocieteModel>>(
      `${this.API_USERS_URL}/v1/societe/create`,
      societe,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getSocietesCreated(
  ): Observable<HttpResponseModel<SocieteModel[]>> {
    return this.http.get<HttpResponseModel<SocieteModel[]>>(
      `${this.API_USERS_URL}/v1/societe/list`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getTypeSocietes():Observable<HttpResponseModel<TypeSocieteModel[]>> {
    return this.http.get<HttpResponseModel<TypeSocieteModel[]>>(
      `${this.API_USERS_URL}/v1/typesociete/list`,
      {
        headers: this.httpHeader(),
      }
    );
  }
}
