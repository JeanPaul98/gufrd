import { Injectable } from '@angular/core';
import { BaseHttpService } from '../../../../../../../../src/app/services/base-http.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpResponseModel } from '../../../../../../../../src/app/models/http-response.model';
import { NavireModel } from '../../../../../../../../src/app/models/navire.model';
import { DemandAeaaModel } from '../../../../../../../../src/app/models/demand-aeaa.model';
import {SubmitDemandModel} from "../../../../../../../../src/app/models/submit-demand.model";
import {BadgesCountModel} from "../../../../../../../../src/app/models/badges-count.model";


@Injectable({
  providedIn: 'root',
})
export class HttpDemandService extends BaseHttpService {
  constructor(private http: HttpClient) {
    super();
  }

  getDemandBadgeCount(
  ): Observable<HttpResponseModel<BadgesCountModel>> {
    return this.http.get<HttpResponseModel<BadgesCountModel>>(
      `${this.API_USERS_URL}/v1/autorisation/medicament/statistiqueDemande`,
      {
        headers: this.httpHeader(),
      }
    );
  }
  getDemandNotSubmitted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/medicament/nonsoumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  nowSubmitDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaModel>>(
      `${this.API_USERS_URL}/v1/formalite/soumis`,
      submitDemand,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandSubmitted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/medicament/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/medicament/accepter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandRejected(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/medicament/rejetee`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/medicament/traiter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

}
