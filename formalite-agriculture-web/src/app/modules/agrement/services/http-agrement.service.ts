import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {DemandAgrementModel} from "../../../models/agrement.model";
import {CreateAgrementModel} from "../../../models/demand-agrement.model";
import {HttpResponseModel} from "../../../models/http-response.model";
import {NavireModel} from "../../../models/navire.model";
import {ProductModel, ProductNewModel} from "../../../models/product.model";
import {SocieteModel} from "../../../models/societe.model";
import {TypeAgrementModel} from "../../../models/type-agrement.model";
import {TypeProductModel} from "../../../models/type-product.model";
import {BaseHttpService} from "../../../services/base-http.service";

@Injectable({
  providedIn: 'root'
})
export class HttpAgrementService extends BaseHttpService{

  constructor(private http: HttpClient) {
    super();
  }

  getNavireInfos(navNumero: string): Observable<HttpResponseModel<NavireModel>> {
    return this.http.get<HttpResponseModel<NavireModel>>(`${this.API_CHECK_NAV}/escale/numeroAan?numeroAan=${navNumero}`, {
      headers: this.httpHeader()
    });
  }

  createProductNew(product: ProductNewModel): Observable<HttpResponseModel<ProductModel>> {
    return this.http.post<HttpResponseModel<ProductModel>>(`${this.API_USERS_URL}/v1/produit/create`, product, {
      headers: this.httpHeader()
    });
  }
  getProducts(): Observable<HttpResponseModel<ProductModel[]>> {
    return this.http.get<HttpResponseModel<ProductModel[]>>(`${this.API_USERS_URL}/v1/produit/list`, {
      headers: this.httpHeader()
    });
  }



  getDemandSubmitted(
  ): Observable<HttpResponseModel<DemandAgrementModel[]>> {
    return this.http.get<HttpResponseModel<DemandAgrementModel[]>>(
      `${this.API_USERS_URL}/v1/demande/agrement/soumis`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandAccepted(
  ): Observable<HttpResponseModel<DemandAgrementModel[]>> {
    return this.http.get<HttpResponseModel<DemandAgrementModel[]>>(
      `${this.API_USERS_URL}/v1/demande/agrement/accepte`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getDemandProcessed(
  ): Observable<HttpResponseModel<DemandAgrementModel[]>> {
    return this.http.get<HttpResponseModel<DemandAgrementModel[]>>(
      `${this.API_USERS_URL}/v1/demande/agrement/traite`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getTypesProduct(): Observable<HttpResponseModel<TypeProductModel[] >> {
    return this.http.get<HttpResponseModel<TypeProductModel[] >>(`${this.API_USERS_URL}/v1/typeProduit/list`, {
      headers: this.httpHeader()
    });
  }

  getSocietes(): Observable<HttpResponseModel<SocieteModel[]>> {
    return this.http.get<HttpResponseModel<SocieteModel[]>>(
      `${this.API_USERS_URL}/v1/societe/list`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  getAgrements(): Observable<HttpResponseModel<TypeAgrementModel[]>> {
    return this.http.get<HttpResponseModel<TypeAgrementModel[]>>(
      `${this.API_USERS_URL}/v1/type/agrement/list`,
      {
        headers: this.httpHeader(),
      }
    );
  }

  createDemande(demandInfos: CreateAgrementModel): Observable<HttpResponseModel<number>> {
    return this.http.post<HttpResponseModel<number>>(`${this.API_USERS_URL}/v1/demande/agrement/existant`, demandInfos, {
      headers: this.httpHeader()
    });

  }
}
