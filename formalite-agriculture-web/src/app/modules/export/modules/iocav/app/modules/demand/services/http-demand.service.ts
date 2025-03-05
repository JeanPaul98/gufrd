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
      `${this.API_USERS_URL}/v1/veterinaire/animauxvivant/nonsoumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }
  getDemandBadgeCount(
  ): Observable<HttpResponseModel<BadgesCountModel>> {
    return this.http.get<HttpResponseModel<BadgesCountModel>>(
      `${this.API_USERS_URL}/v1/veterinaire/animauxvivant/statistiqueDemande`,
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

  nowRejectDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaModel>>(
      `${this.API_USERS_URL}/v1/formalite/rejeter`,
      submitDemand,
      {
        headers: this.httpHeader(),
      }
    );
  }

  nowAcceptDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaModel>>(
      `${this.API_USERS_URL}/v1/formalite/accepter`,
      submitDemand,
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

  acceptDemand(submitDemand: SubmitDemandModel): Observable<HttpResponseModel<DemandAeaaModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaModel>>(
      `${this.API_USERS_URL}/v1/veterinaire/animauxvivant/accepterdemande
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
      `${this.API_USERS_URL}/v1/veterinaire/animauxvivant/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/animauxvivant/accepter`,
      {
        headers: this.httpHeader(),
      }
    );
  }



  getDemandRejected(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/animauxvivant/rejetee`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/animauxvivant/traiter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

}
