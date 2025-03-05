import { Injectable } from '@angular/core';
import { BaseHttpService } from '../../../../../../../../../../src/app/services/base-http.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpResponseModel } from '../../../../../../../../../../src/app/models/http-response.model';
import { NavireModel } from '../../../../../../../../../../src/app/models/navire.model';
import {DemandiocavModel, DemandiocavSimpleModel} from '../../../../../../../../../../src/app/models/demand-iocav.model';
import {TypeProductModel} from "../../../../../../../../../../src/app/models/type-product.model";
import {
  ProductModel,
  ProductNewModel,
  ProductObjModel
} from "../../../../../../../../../../src/app/models/product.model";
@Injectable({
  providedIn: 'root'
})
export class HttpCreateService extends BaseHttpService {

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

  getTypesProduct(): Observable<HttpResponseModel<TypeProductModel[] >> {
    return this.http.get<HttpResponseModel<TypeProductModel[] >>(`${this.API_USERS_URL}/v1/typeProduit/list`, {
      headers: this.httpHeader()
    });
  }

  createDemande(demandInfos: DemandiocavSimpleModel): Observable<HttpResponseModel<number>> {
    return this.http.post<HttpResponseModel<number>>(`${this.API_USERS_URL}/v1/veterinaire/cuirspeaux/insert`, demandInfos, {
      headers: this.httpHeader()
    });

  }
  editDemande(demandInfos: DemandiocavSimpleModel): Observable<HttpResponseModel<DemandiocavSimpleModel>> {
    return this.http.post<HttpResponseModel<DemandiocavSimpleModel>>(`${this.API_USERS_URL}/v1/veterinaire/cuirspeaux/validerdemande
`, demandInfos, {
      headers: this.httpHeader()
    });
  }
}
