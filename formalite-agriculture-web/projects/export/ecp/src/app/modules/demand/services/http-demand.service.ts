import { Injectable } from '@angular/core';
import { BaseHttpService } from '../../../../../../../../src/app/services/base-http.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpResponseModel } from '../../../../../../../../src/app/models/http-response.model';
import { NavireModel } from '../../../../../../../../src/app/models/navire.model';
import { DemandAeaaCompletModel } from '../../../../../../../../src/app/models/demand-aeaa.model';
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
  ): Observable<HttpResponseModel<DemandAeaaCompletModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaCompletModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cuirspeaux/nonsoumis`,
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
      `${this.API_USERS_URL}/v1/veterinaire/cuirspeaux/statistiqueDemande`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  nowSubmitDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaCompletModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaCompletModel>>(
      `${this.API_USERS_URL}/v1/formalite/soumis`,
      submitDemand,
      {
        headers: this.httpHeader(),
      }
    );
  }

  nowDeleteDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaCompletModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaCompletModel>>(
      `${this.API_USERS_URL}/v1/formalite/annuler`,
      submitDemand,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandSubmitted(
  ): Observable<HttpResponseModel<DemandAeaaCompletModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaCompletModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cuirspeaux/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandAeaaCompletModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaCompletModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cuirspeaux/accepter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandRejected(
  ): Observable<HttpResponseModel<DemandAeaaCompletModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaCompletModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cuirspeaux/rejetee`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandAeaaCompletModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaCompletModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cuirspeaux/traiter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

}
