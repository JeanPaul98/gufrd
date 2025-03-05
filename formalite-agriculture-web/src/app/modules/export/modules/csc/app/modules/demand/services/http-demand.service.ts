import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {BadgesCountModel} from "../../../../../../../../models/badges-count.model";
import {DemandAeaaModel} from "../../../../../../../../models/demand-aeaa.model";
import {HttpResponseModel} from "../../../../../../../../models/http-response.model";
import {SubmitDemandModel} from "../../../../../../../../models/submit-demand.model";
import {BaseHttpService} from "../../../../../../../../services/base-http.service";


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
      `${this.API_USERS_URL}/v1/veterinaire/cire/nonsoumis`,
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

  generateCertificat(idFormalite:number
  ): Observable<Blob> {
    return this.http.get<Blob>(
        `${this.API_USERS_URL}/v1/report/certificat/cire/pdf`,
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
      `${this.API_USERS_URL}/v1/veterinaire/cire/accepterdemande
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
      `${this.API_USERS_URL}/v1/veterinaire/cire/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cire/accepter`,
      {
        headers: this.httpHeader(),
      }
    );
  }



  getDemandRejected(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cire/rejetee`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/veterinaire/cire/traiter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

}
