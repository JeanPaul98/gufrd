import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AgreementModel} from "../../../../../../../../src/app/models/agrement.model";
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
      `${this.API_USERS_URL}/v1/autorisation/animauxVivant/nonsoumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getAgreementList(
  ): Observable<HttpResponseModel<AgreementModel[]>> {
    return this.http.get<HttpResponseModel<AgreementModel[]>>(
      `${this.API_USERS_URL}/v1/agrement/list`,
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
      `${this.API_USERS_URL}/v1/autorisation/animauxVivant/statistiqueDemande`,
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
      `${this.API_USERS_URL}/v1/autorisation/animauxVivant/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/animauxVivant/accepter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandRejected(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/animauxVivant/rejetee`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/autorisation/animauxVivant/traiter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

}
