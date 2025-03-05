import {Injectable} from '@angular/core';
import {BaseHttpService} from '../../../../../../../../../../src/app/services/base-http.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HttpResponseModel} from '../../../../../../../../../../src/app/models/http-response.model';
import {NavireModel} from '../../../../../../../../../../src/app/models/navire.model';
import {DemandAeaaModel, DemandAeaaSimpleModel} from '../../../../../../../../../../src/app/models/demand-aeaa.model';
import {IpRequest} from "../../../../../../../../../../src/app/requests/ip.request";

@Injectable({
  providedIn: 'root'
})
export class CreateHttpService extends BaseHttpService {

  constructor(private http: HttpClient) {
    super();
  }

  getShipInfos(number: string): Observable<HttpResponseModel<NavireModel>> {
    return this.http.get<HttpResponseModel<NavireModel>>(`${this.API_CHECK_NAV}/escale/numeroAan?numeroAan=${number}`, {
      headers: this.httpHeader()
    });
  }

  create(request: IpRequest): Observable<HttpResponseModel<DemandAeaaSimpleModel>> {
    return this.http.post<HttpResponseModel<DemandAeaaSimpleModel>>(`${this.API_USERS_URL}/v1/phytosanitaire/cargaison/insert`, request, {
      headers: this.httpHeader()
    });
  }
}
