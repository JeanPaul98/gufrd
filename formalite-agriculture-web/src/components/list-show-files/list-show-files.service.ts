import {Injectable} from '@angular/core';

import {UntilDestroy} from "@ngneat/until-destroy";

import {BehaviorSubject, catchError, finalize, map, Observable, of, throwError} from "rxjs";
import {ListFilesModel} from "../../app/models/list-files.model";
import {HttpListShowFilesService} from "./http-list-show-files.service";

@UntilDestroy()
@Injectable({
  providedIn: 'root'
})
export class ListShowFilesService {

  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;
  constructor(private httpCreateService: HttpListShowFilesService) {
    this.isLoadingSubject = new BehaviorSubject<boolean>(false);
    this.isLoading$ = this.isLoadingSubject.asObservable();
  }

  getPiecesByIdFormalite(id: number): Observable<ListFilesModel[] | undefined> {
    return this.httpCreateService.getPiecesByIdFormalite(id).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }

  determineFileType(url: string): Observable<'pdf' | 'image' | 'unknown'> {
    // We use HEAD request to avoid downloading the full file
    return (this.httpCreateService.determineFileType(url).pipe(
        map(response => {
          const contentType = response.headers.get('Content-Type');
          if (contentType) {
            if (contentType.includes('pdf')) {
              return 'pdf';
            } else if (contentType.includes('image')) {
              return 'image';
            }
          }
          // Fallback: determine from the URL extension
          const extension = url.split('.').pop();
          if (extension === 'pdf') {
            return 'pdf';
          } else if (['jpg', 'jpeg', 'png', 'gif'].includes(extension || '')) {
            return 'image';
          }
          return 'unknown';
        }),
        catchError(() => of('unknown')) // Handle errors gracefully by returning 'unknown'
      )) as Observable<'pdf' | 'image' | 'unknown'>

  }

  downloadFileByUrl(url: string): Observable<Blob> {
    return this.httpCreateService.downloadFileByUrl(url).pipe(
      map((res) => res),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false))
    );
  }



}
