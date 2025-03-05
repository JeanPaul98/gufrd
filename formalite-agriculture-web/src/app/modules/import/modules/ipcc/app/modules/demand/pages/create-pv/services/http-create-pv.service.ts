import {Injectable} from '@angular/core';
import {BaseHttpService} from "../../../../../../../../../../services/base-http.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpResponseModel} from "../../../../../../../../../../models/http-response.model";
import {NavireModel} from "../../../../../../../../../../models/navire.model";
import {ProductModel, ProductNewModel} from "../../../../../../../../../../models/product.model";
import {TypeProductModel} from "../../../../../../../../../../models/type-product.model";
import {DemandAeaaSimpleModel} from "../../../../../../../../../../models/demand-aeaa.model";
import {InspecteurModel} from "../../../../../../../../../../models/admin/inspecteur.model";
import {PVIPCCModel} from "../../../../../../../../../../models/admin/pv-ipcc.model";
import {SocieteModel} from "../../../../../../../../../../models/societe.model";

@Injectable({
  providedIn: 'root'
})
export class HttpCreatePVService extends BaseHttpService {

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

  getInspecteurs(): Observable<HttpResponseModel<InspecteurModel[]>> {
    return this.http.get<HttpResponseModel<InspecteurModel[]>>(`${this.API_USERS_URL}/v1/inspecteur/list`, {
      headers: this.httpHeader()
    });
  }

  getProducts(): Observable<HttpResponseModel<ProductModel[]>> {
    return this.http.get<HttpResponseModel<ProductModel[]>>(`${this.API_USERS_URL}/v1/produit/list`, {
      headers: this.httpHeader()
    });
  }

  getTypesProduct(): Observable<HttpResponseModel<TypeProductModel[]>> {
    return this.http.get<HttpResponseModel<TypeProductModel[]>>(`${this.API_USERS_URL}/v1/typeProduit/list`, {
      headers: this.httpHeader()
    });
  }

  createPV(pvInfos: PVIPCCModel): Observable<HttpResponseModel<PVIPCCModel>> {
    return this.http.post<HttpResponseModel<PVIPCCModel>>(`${this.API_USERS_URL}/v1/pv/phytoCargo/insert`, pvInfos, {
      headers: this.httpHeader()
    });
  }

  getSocietes(): Observable<HttpResponseModel<SocieteModel[]>> {
    return this.http.get<HttpResponseModel<SocieteModel[]>>(`${this.API_USERS_URL}/v1/societe/list`, {
      headers: this.httpHeader()
    });
  }

  createDemande(demandInfos: DemandAeaaSimpleModel): Observable<HttpResponseModel<number>> {
    return this.http.post<HttpResponseModel<number>>(`${this.API_USERS_URL}/v1/autorisation/enlevement/insert`, demandInfos, {
      headers: this.httpHeader()
    });

  }

  editDemande(demandInfos: DemandAeaaSimpleModel): Observable<HttpResponseModel<DemandAeaaSimpleModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaSimpleModel>>(`${this.API_USERS_URL}/v1/autorisation/enlevement/validerdemande
`, demandInfos, {
      headers: this.httpHeader()
    });
  }
}
