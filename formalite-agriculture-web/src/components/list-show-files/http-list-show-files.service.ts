import {HttpClient, HttpResponse} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpResponseModel} from "../../app/models/http-response.model";
import {ListFilesModel} from "../../app/models/list-files.model";
import {BaseHttpService} from "../../app/services/base-http.service";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class HttpListShowFilesService extends BaseHttpService {
   URL_FILE = environment.API_URL_FILE;

  constructor(private http: HttpClient) {
    super();
  }

  getPiecesByIdFormalite(id: number): Observable<HttpResponseModel<ListFilesModel[]>> {
    return this.http.get<HttpResponseModel<ListFilesModel[]>>(`${this.API_USERS_URL}/v1/piecejointe/depot/listpiece/{idFormalite}`, {
      params:{
        idFormalite:id
      },
      headers: this.httpHeader()
    });
  }

  downloadFileByUrl(url: string): Observable<Blob> {
    return this.http.get('/api/'+url, {
      responseType: 'blob',
    });
  }

  // Factory function to check if the file is PDF or Image based on URL or MIME type
  determineFileType(url: string): Observable<HttpResponse<'pdf' | 'image' | 'unknown'>> {
    // We use HEAD request to avoid downloading the full file
    console.log(this.getProxiedUrl(url));
    return this.http.head<'pdf' | 'image' | 'unknown'>(this.getProxiedUrl(url), {observe: 'response',
    headers: this.httpHeader()
    })
  }

  getProxiedUrl(originalUrl: string): string {
    const url = new URL(originalUrl); // Crée un objet URL à partir de l'URL originale
    const path = url.pathname.slice(1) + url.search; // Récupère le chemin et les paramètres de requête
    console.log("url", path);
    return `${'/atd/'}${path}`; // Concatène avec l'URL proxy
  }


}
