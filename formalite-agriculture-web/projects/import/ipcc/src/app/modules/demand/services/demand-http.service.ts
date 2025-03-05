import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {BadgesCountModel} from "../../../../../../../../src/app/models/badges-count.model";
import {DemandAeaaModel, DemandAeaaSimpleModel} from "../../../../../../../../src/app/models/demand-aeaa.model";
import {HttpResponseModel} from "../../../../../../../../src/app/models/http-response.model";
import {NavireModel} from "../../../../../../../../src/app/models/navire.model";
import {PaiementUrlModel} from "../../../../../../../../src/app/models/paiement-url.model";
import {ProductModel} from "../../../../../../../../src/app/models/product.model";
import {SubmitDemandModel} from "../../../../../../../../src/app/models/submit-demand.model";
import {IpRequest} from "../../../../../../../../src/app/requests/ip.request";
import {BaseHttpService} from "../../../../../../../../src/app/services/base-http.service";


@Injectable({
  providedIn: 'root'
})
export class DemandHttpService extends BaseHttpService{

  constructor(private http: HttpClient) {
    super();
  }

  getDemandNotSubmitted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/cargaison/nonsoumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }
  getDemandBadgeCount(
  ): Observable<HttpResponseModel<BadgesCountModel>> {
    return this.http.get<HttpResponseModel<BadgesCountModel>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/cargaison/statistiqueDemande`,
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

  update(request: IpRequest): Observable<HttpResponseModel<DemandAeaaSimpleModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaSimpleModel>>(`${this.API_USERS_URL}/v1/phytosanitaire/cargaison/updateDemande`, request, {
      headers: this.httpHeader()
    });
  }

  getDemandSubmitted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/cargaison/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/cargaison/accepter`,
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

  create(request: IpRequest): Observable<HttpResponseModel<number>> {
    return this.http.post<HttpResponseModel<number>>(`${this.API_USERS_URL}/v1/phytosanitaire/cargaison/insert`, request, {
      headers: this.httpHeader()
    });
  }
  getDemandRejected(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/cargaison/rejetee`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  shipInfos(number: string): Observable<HttpResponseModel<NavireModel>> {
    return this.http.get<HttpResponseModel<NavireModel>>(`${this.API_CHECK_NAV}/escale/numeroAan?numeroAan=${number}`, {
      headers: this.httpHeader()
    });
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandAeaaModel[]>> {
    return this.http.get<HttpResponseModel<DemandAeaaModel[]>>(
      `${this.API_USERS_URL}/v1/phytosanitaire/cargaison/traiter`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  products(): Observable<HttpResponseModel<ProductModel[]>> {
    return this.http.get<HttpResponseModel<ProductModel[]>>(`${this.API_USERS_URL}/v1/produit/list`, {
      headers: this.httpHeader()
    });
  }

}
