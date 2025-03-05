import { Injectable } from '@angular/core';
import { BaseHttpService } from '../../../../../../../../src/app/services/base-http.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpResponseModel } from '../../../../../../../../src/app/models/http-response.model';
import { DemandiocavModel } from '../../../../../../../../src/app/models/demand-iocav.model';
import {SubmitDemandModel} from "../../../../../../../../src/app/models/submit-demand.model";
import {BadgesCountModel} from "../../../../../../../../src/app/models/badges-count.model";
import {PaiementUrlModel} from "../../../../../../../../src/app/models/paiement-url.model";

@Injectable({
  providedIn: 'root',
})
export class HttpDemandService extends BaseHttpService {
  constructor(private http: HttpClient) {
    super();
  }

  getDemandNotSubmitted(
  ): Observable<HttpResponseModel<DemandiocavModel[]>> {
    return this.http.get<HttpResponseModel<DemandiocavModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cire/nonsoumis`,
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
      `${this.API_USERS_URL}/v1/veterinaire/cire/statistiqueDemande`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  nowSubmitDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandiocavModel>> {
    return this.http.post<HttpResponseModel<DemandiocavModel>>(
      `${this.API_USERS_URL}/v1/formalite/soumis`,
      submitDemand,
      {
        headers: this.httpHeader(),
      }
    );
  }

  nowDeleteDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandiocavModel>> {
    return this.http.post<HttpResponseModel<DemandiocavModel>>(
      `${this.API_USERS_URL}/v1/formalite/annuler`,
      submitDemand,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandSubmitted(
  ): Observable<HttpResponseModel<DemandiocavModel[]>> {
    return this.http.get<HttpResponseModel<DemandiocavModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cire/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandiocavModel[]>> {
    return this.http.get<HttpResponseModel<DemandiocavModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cire/accepter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandRejected(
  ): Observable<HttpResponseModel<DemandiocavModel[]>> {
    return this.http.get<HttpResponseModel<DemandiocavModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cire/rejetee`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandiocavModel[]>> {
    return this.http.get<HttpResponseModel<DemandiocavModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cire/traiter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

}
