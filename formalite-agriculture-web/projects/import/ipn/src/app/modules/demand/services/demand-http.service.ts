import {Injectable} from '@angular/core';
import {BaseHttpService} from "../../../../../../../../src/app/services/base-http.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpResponseModel} from "../../../../../../../../src/app/models/http-response.model";
import {DemandAeaaModel, DemandAeaaSimpleModel} from "../../../../../../../../src/app/models/demand-aeaa.model";
import {SubmitDemandModel} from "../../../../../../../../src/app/models/submit-demand.model";
import {NavireModel} from "../../../../../../../../src/app/models/navire.model";
import {IpRequest} from "../../../../../../../../src/app/requests/ip.request";
import {PaiementUrlModel} from "../../../../../../../../src/app/models/paiement-url.model";
import {BadgesCountModel} from "../../../../../../../../src/app/models/badges-count.model";

@Injectable({
  providedIn: 'root'
})
export class DemandHttpService extends BaseHttpService {

  constructor(private http: HttpClient) {
    super();
  }

  shipInfos(number: string): Observable<HttpResponseModel<NavireModel>> {
    return this.http.get<HttpResponseModel<NavireModel>>(`${this.API_CHECK_NAV}/escale/numeroAan?numeroAan=${number}`, {
      headers: this.httpHeader()
    });
  }

  notSubmitted(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/navire/nonsoumis`, {
      headers: this.httpHeader(),
    });
  }

  submitted(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/navire/soumis`, {
      headers: this.httpHeader(),
    });
  }

  create(request: IpRequest): Observable<HttpResponseModel<number>> {
    return this.http.post<HttpResponseModel<number>>(`${this.API_USERS_URL}/v1/phytosanitaire/navire/insert`, request, {
      headers: this.httpHeader()
    });
  }

  update(request: IpRequest): Observable<HttpResponseModel<DemandAeaaSimpleModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaSimpleModel>>(`${this.API_USERS_URL}/v1/phytosanitaire/navire/updateDemande`, request, {
      headers: this.httpHeader()
    });
  }

  accepted(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/navire/accepter`, {
      headers: this.httpHeader(),
    });
  }

  rejected(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/navire/rejetee`, {
      headers: this.httpHeader(),
    });
  }

  processed(): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(`${this.API_USERS_URL}/v1/phytosanitaire/navire/traiter`, {
      headers: this.httpHeader(),
    });
  }

  submit(request: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaModel>>(`${this.API_USERS_URL}/v1/formalite/soumis`, request, {
      headers: this.httpHeader(),
    });
  }

  delete(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaModel>>(`${this.API_USERS_URL}/v1/formalite/annuler`, submitDemand, {
      headers: this.httpHeader(),
    });
  }

  paymentUrl(idFormality: number): Observable<HttpResponseModel<PaiementUrlModel>> {
    return this.http.post<HttpResponseModel<PaiementUrlModel>>(`${this.API_USERS_URL}/v1/redevance/paye`, {idFormalite: idFormality}, {
      headers: this.httpHeader()
    });
  }

  badgeCount(): Observable<HttpResponseModel<BadgesCountModel>> {
    return this.http.get<HttpResponseModel<BadgesCountModel>>(`${this.API_USERS_URL}/v1/phytosanitaire/navire/statistiqueDemande`, {
      headers: this.httpHeader()
    });
  }
}

