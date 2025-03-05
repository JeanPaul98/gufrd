import {Injectable} from '@angular/core';
import {catchError, map, throwError} from "rxjs";
import {SocieteModel} from "../../../models/societe.model";
import {HttpSocietyService} from "./http-society.service";

@Injectable({
  providedIn: 'root'
})
export class SocietyService {

  constructor(private httpSocietyService: HttpSocietyService) { }

  createSociety(society: SocieteModel){
    return this.httpSocietyService.createSociete(society).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      })
    );
  }
  getSocietyCreated(){
    return this.httpSocietyService.getSocietesCreated().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      })
    );
  }

  getTypeSocietyCreated(){
    return this.httpSocietyService.getTypeSocietes().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      })
    );
  }
}
