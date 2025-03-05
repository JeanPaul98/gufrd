import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {BadgesCountModel} from "../../../../../../../../src/app/models/badges-count.model";
import {DemandAeaaModel} from '../../../../../../../../src/app/models/demand-aeaa.model';
import {HttpResponseModel} from '../../../../../../../../src/app/models/http-response.model';
import {PaiementUrlModel} from "../../../../../../../../src/app/models/paiement-url.model";
import {SubmitDemandModel} from "../../../../../../../../src/app/models/submit-demand.model";
import {BaseHttpService} from '../../../../../../../../src/app/services/base-http.service';

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
      `${this.API_USERS_URL}/v1/autorisation/depotage/nonsoumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }
  getPayementUrl(idFormalite:number
  ): Observable<HttpResponseModel<PaiementUrlModel>> {
    return this.http.post<HttpResponseModel<PaiementUrlModel>>(
      `${this.API_USERS_URL}/v1/redevance/paye`,
      {
        idFormalite
      },
      {
        headers: this.httpHeader(),
      }
    );
  }
  getDemandBadgeCount(
  ): Observable<HttpResponseModel<BadgesCountModel>> {
    return this.http.get<HttpResponseModel<BadgesCountModel>>(
      `${this.API_USERS_URL}/v1/autorisation/depotage/statistiqueDemande`,
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

  nowDeleteDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaModel>>(
      `${this.API_USERS_URL}/v1/formalite/annuler`,
      submitDemand,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandSubmitted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/depotage/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/depotage/accepter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandRejected(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/depotage/rejetee`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/depotage/traiter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  generateAutorisation(idFormalite:number
  ): Observable<Blob> {
    return this.http.get<Blob>(
      `${this.API_USERS_URL}/v1/report/autorisation/pdf`,
      {
        responseType: 'blob' as 'json',
        headers: this.httpHeader(),
        params: {
          idFormalite: idFormalite
        }
      }
    );
  }

}
