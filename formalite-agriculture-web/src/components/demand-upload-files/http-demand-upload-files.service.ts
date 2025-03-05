import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpResponseModel} from "../../app/models/http-response.model";
import {NavireModel} from "../../app/models/navire.model";
import {BaseHttpService} from "../../app/services/base-http.service";
import {TypePieceJointeModel} from "../../app/models/upload-files.model";
import {UntilDestroy} from "@ngneat/until-destroy";


@Injectable({
  providedIn: 'root'
})
export class HttpDemandUploadFilesService extends BaseHttpService {

  constructor(private http: HttpClient) {
    super();
  }

  getTypePieces(): Observable<HttpResponseModel<TypePieceJointeModel[]>> {
    return this.http.get<HttpResponseModel<TypePieceJointeModel[]>>(`${this.API_USERS_URL}/v1/typepiecejointe/list`, {
      headers: this.httpHeader()
    });
  }

  xendPieces(formData: FormData): Observable<HttpResponseModel<{}>> {
    return this.http.post<HttpResponseModel<{}>>(`${this.API_USERS_URL}/v1/piecejointe/depot/file`,formData, {
      headers: this.httpHeaderForFormData()
    });
  }
}
