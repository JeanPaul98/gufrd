import { Injectable } from '@angular/core';
import {BaseHttpService} from "../../../../../../../../services/base-http.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpResponseModel} from "../../../../../../../../models/http-response.model";
import {DemandAeaaModel} from "../../../../../../../../models/demand-aeaa.model";
import {BadgesCountModel} from "../../../../../../../../models/badges-count.model";
import {SubmitDemandModel} from "../../../../../../../../models/submit-demand.model";



@Injectable({
  providedIn: 'root',
})
export class HttpDemandService extends BaseHttpService {
  constructor(private http: HttpClient) {
    super();
  }

  getDemandNotSubmitted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/nonsoumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }
  getDemandBadgeCount(
  ): Observable<HttpResponseModel<BadgesCountModel>> {
    return this.http.get<HttpResponseModel<BadgesCountModel>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/statistiqueDemande`,
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

  acceptDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaModel>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/accepterdemande
`,
      submitDemand,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandSubmitted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/accepter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandRejected(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/rejetee`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/pharmaceutique/traiter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

}
