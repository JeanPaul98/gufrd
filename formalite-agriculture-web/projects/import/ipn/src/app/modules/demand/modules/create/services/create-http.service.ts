import {Injectable} from '@angular/core';
import {BaseHttpService} from '../../../../../../../../../../src/app/services/base-http.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HttpResponseModel} from '../../../../../../../../../../src/app/models/http-response.model';
import {NavireModel} from '../../../../../../../../../../src/app/models/navire.model';
import {IpRequest} from "../../../../../../../../../../src/app/requests/ip.request";
import {ProductModel} from "../../../../../../../../../../src/app/models/product.model";

@Injectable({
  providedIn: 'root'
})
export class CreateHttpService extends BaseHttpService {

  constructor(private http: HttpClient) {
    super();
  }

  shipInfos(number: string): Observable<HttpResponseModel<NavireModel>> {
    return this.http.get<HttpResponseModel<NavireModel>>(`${this.API_CHECK_NAV}/escale/numeroAan?numeroAan=${number}`, {
      headers: this.httpHeader()
    });
  }

  create(request: IpRequest): Observable<HttpResponseModel<number>> {
    return this.http.post<HttpResponseModel<number>>(`${this.API_USERS_URL}/v1/phytosanitaire/navire/insert`, request, {
      headers: this.httpHeader()
    });
  }

  products(): Observable<HttpResponseModel<ProductModel[]>> {
    return this.http.get<HttpResponseModel<ProductModel[]>>(`${this.API_USERS_URL}/v1/produit/list`, {
      headers: this.httpHeader()
    });
  }
}
