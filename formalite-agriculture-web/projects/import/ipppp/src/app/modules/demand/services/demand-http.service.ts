import {Injectable} from '@angular/core';
import {BaseHttpService} from "../../../../../../../../src/app/services/base-http.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpResponseModel} from "../../../../../../../../src/app/models/http-response.model";
import {DemandAeaaModel, DemandAeaaSimpleModel} from "../../../../../../../../src/app/models/demand-aeaa.model";
import {SubmitDemandModel} from "../../../../../../../../src/app/models/submit-demand.model";
import {NavireModel} from "../../../../../../../../src/app/models/navire.model";
import {IpRequest} from "../../../../../../../../src/app/requests/ip.request";
import {ProductModel} from "../../../../../../../../src/app/models/product.model";
import {BadgesCountModel} from "../../../../../../../../src/app/models/badges-count.model";

@Injectable({
  providedIn: 'root'
})
export class DemandHttpService extends BaseHttpService {

  constructor(private http: HttpClient) {
    super();
  }

  notSubmitted(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/nonsoumis`, {
      headers: this.httpHeader(),
    });
  }

  submitted(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/soumis`, {
      headers: this.httpHeader(),
    });
  }

  accepted(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/accepter`, {
      headers: this.httpHeader(),
    });
  }

  rejected(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/rejetee`, {
      headers: this.httpHeader(),
    });
  }

  processed(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/traiter`, {
      headers: this.httpHeader(),
    });
  }

  submit(request: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaModel>>(`${this.API_USERS_URL}/v1/formalite/soumis`, request, {
      headers: this.httpHeader(),
    });
  }

  shipInfos(number: string): Observable<HttpResponseModel<NavireModel>> {
    return this.http.get<HttpResponseModel<NavireModel>>(`${this.API_CHECK_NAV}/escale/numeroAan?numeroAan=${number}`, {
      headers: this.httpHeader()
    });
  }

  create(request: IpRequest): Observable<HttpResponseModel<number>> {
    return this.http.post<HttpResponseModel<number>>(`${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/insert`, request, {
      headers: this.httpHeader()
    });
  }

  update(request: IpRequest): Observable<HttpResponseModel<DemandAeaaSimpleModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaSimpleModel>>(`${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/updateDemande`, request, {
      headers: this.httpHeader()
    });
  }

  products(): Observable<HttpResponseModel<ProductModel[]>> {
    return this.http.get<HttpResponseModel<ProductModel[]>>(`${this.API_USERS_URL}/v1/produit/list`, {
      headers: this.httpHeader()
    });
  }

  badgeCount(): Observable<HttpResponseModel<BadgesCountModel>>  {
    return this.http.get<HttpResponseModel<BadgesCountModel>>(`${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/statistiqueDemande`, {
      headers: this.httpHeader()
    });
  }
}
