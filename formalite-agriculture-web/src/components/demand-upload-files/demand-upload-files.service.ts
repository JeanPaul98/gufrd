import {Injectable} from '@angular/core';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {filesize} from 'filesize';

import {BehaviorSubject, catchError, finalize, map, Observable, throwError,} from 'rxjs';
import {TypePieceJointeModel, UploadFilesModel,} from '../../app/models/upload-files.model';
import {getBlobFromLocalStorage} from "../../app/utils/storage/code-decode-BS64";
import {HttpDemandUploadFilesService} from './http-demand-upload-files.service';

@UntilDestroy()
@Injectable({
  providedIn: 'root',
})
export class DemandUploadFilesService {
  isLoading$: Observable<boolean>;
  isLoadingSubject: BehaviorSubject<boolean>;
  constructor(private httpCreateService: HttpDemandUploadFilesService) {
    this.isLoadingSubject = new BehaviorSubject<boolean>(false);
    this.isLoading$ = this.isLoadingSubject.asObservable();
  }

  getTypePieces(): Observable<TypePieceJointeModel[] | undefined> {
    return this.httpCreateService.getTypePieces().pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false)),
    );
  }

  uploadFiles(dataForm: FormData): Observable<{} | undefined> {
    return this.httpCreateService.xendPieces(dataForm).pipe(
      map((res) => res.result),
      catchError((err) => {
        return throwError(() => err);
      }),
      finalize(() => this.isLoadingSubject.next(false)),
    );
  }

  public getJustFileName(file: File) {
    return file.name.split('.')[0];
  }

  public getJustFileExtension(file: File) {
    return file.name.split('.')[1];
  }

  public getJustFileSizeInMB(file: File) {
    return filesize(file.size);
  }

  saveFiles(idFormalite: number, fileUploads: UploadFilesModel) {
    //  Formater les données à send
    const formDataFiles = new FormData();
    const fileNameTypePieceJointe = fileUploads.fileNameTypePieceJointe!.map(
      (fileNameTypePieceJointeElmt) => {
        return {
          filename: fileNameTypePieceJointeElmt.fileName,
          idTypePieceJointe: fileNameTypePieceJointeElmt.idTypePieceJointe,
        };
      },
    );

    fileUploads.file!.forEach((file, index) => {
      const realBlob = getBlobFromLocalStorage(
        fileUploads.filesBase64![index],
      );
      console.log('realBlob', realBlob);
      formDataFiles.append(
        'file',
        new File(
          [realBlob as Blob],
          fileUploads.fileNameTypePieceJointe![index].fileName as string,
          { type: 'application/pdf' }
        ),
      );
    });
    // formDataFiles.append('file',fileUploads.file![0] )
    formDataFiles.append('idFormalite', idFormalite.toString());
    formDataFiles.append(
      'filenameTypePieceJointe',
      JSON.stringify(fileNameTypePieceJointe),
    );

    console.log('formDataFiles', formDataFiles, fileUploads.file![0]);

    //  Call file upload service
    this.uploadFiles(formDataFiles)
      .pipe(
        untilDestroyed(this),
        finalize(() => {
          // localStorage.removeItem('step1AEAA');
          // localStorage.removeItem('step2AEAA');
          // localStorage.removeItem('currentStepAEAA');
          // this.createService.isEndGoBackSubject.next(1);
        }),
      )
      .subscribe({});
  }
}
