import {CommonModule} from '@angular/common';
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ButtonModule, TableModule} from '@coreui/angular';
import {NgSelectModule} from '@ng-select/ng-select';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import _ from 'lodash';
import {toast} from 'ngx-sonner';
import {
  FileNameTypePieceJointeModel,
  TypePieceJointeModel,
  UploadFilesModel,
} from '../../app/models/upload-files.model';
import {convertFileToBase64} from "../../app/utils/storage/code-decode-BS64";
import {TippyTooltipDirective} from '../../directives/tippy-tooltip/tippy-tooltip.directive';
import {NiceToastComponent} from '../nice-toast/nice-toast.component';
import {DemandUploadFilesService} from './demand-upload-files.service';

// import {fileTypeFromFile, fileTypeFromBlob} from 'file-type';

@UntilDestroy()
@Component({
  selector: 'app-demand-upload-files',
  standalone: true,
  imports: [
    CommonModule,
    NgSelectModule,
    ReactiveFormsModule,
    FormsModule,
    ButtonModule,
    TableModule,
    TippyTooltipDirective,
  ],
  templateUrl: './demand-upload-files.component.html',
  styleUrl: './demand-upload-files.component.scss',
})
export class DemandUploadFilesComponent implements OnInit {
  @Input('idFormalite') idFormalite!: number;
  @Input()
  @Input()
  fileUploads!: UploadFilesModel;
  requiredFileType: string = 'application/pdf';
  @Output('onFileUpload') onFileUpload = new EventEmitter<UploadFilesModel>();

  selectedCar: number = 1;
  selectedTypePiece!: TypePieceJointeModel;
  cars = [
    { id: 1, name: 'Certificat Phytosanitaire' },
    { id: 2, name: 'Certificat de Fumigation' },
    { id: 3, name: 'Certificat d’origine' },
    { id: 4, name: 'Certificat de qualité et poids' },
  ];

  typePieces: TypePieceJointeModel[] = [];
  fileName = '';
  constructor(public demandUploadFilesService: DemandUploadFilesService) {}

  ngOnInit(): void {
    this.demandUploadFilesService
      .getTypePieces()
      .pipe(untilDestroyed(this))
      .subscribe((res) => {
        this.typePieces = res as TypePieceJointeModel[];
        console.log(res);
      });

    console.log('this.fileUploads', this.fileUploads);
  }

  async setFileUploads(file: File): Promise<void> {
    try {
      // Attendre que le fichier soit converti en Base64
      const fileBase64 = await convertFileToBase64(file);

      // Vérifier si fileUploads est vide
      if (_.isEmpty(this.fileUploads)) {
        // Initialiser l'objet fileUploads s'il est vide
        this.fileUploads = {
          idFormalite: 1,
          file: [file], // Pas besoin de forcer le type ici avec "as File"
          filesBase64: [fileBase64],
          fileNameTypePieceJointe: [
            {
              typePieceJointe: this.selectedTypePiece,
              fileName: file.name, // file?.name fonctionne aussi
              idTypePieceJointe: this.selectedTypePiece.id,
            },
          ],
        };
      } else {
        // Mettre à jour fileUploads s'il existe déjà
        this.fileUploads = {
          idFormalite: 1,
          ...this.fileUploads,
          file: [...(this.fileUploads.file as File[]), file as File], // Concaténation des fichiers
          filesBase64: [
            ...(this.fileUploads.filesBase64 as string[]),
            fileBase64,
          ],
          fileNameTypePieceJointe: [
            ...(this.fileUploads
              .fileNameTypePieceJointe as FileNameTypePieceJointeModel[]),
            {
              fileName: file?.name as string,
              typePieceJointe: this.selectedTypePiece,
              idTypePieceJointe: this.selectedTypePiece.id,
            },
          ],
        };
      }
    } catch (error) {
      console.error('Erreur lors du traitement du fichier:', error);
    }
  }

  removeFileUpload(index: number) {
    const deletedFile = this.fileUploads.file![index];
    this.fileUploads.file?.splice(index, 1);
    this.fileUploads.fileNameTypePieceJointe?.splice(index, 1);
    this.fileUploads.filesBase64?.splice(index, 1);

    toast.custom(NiceToastComponent, {
      position: 'top-center',
      componentProps: {
        texto: `fichier "${deletedFile.name}" supprimé!`,
        state: 'success',
      },
    });
  }

  async onFileSelected(event: Event): Promise<void> {
    const target: HTMLInputElement = event.target as HTMLInputElement;
    const file: File   = target?.files?.item(0) as File;

    if (file) {
      try {
        // Attendre que le fichier soit traité dans setFileUploads
        await this.setFileUploads(file);

        // Émettre l'événement après avoir uploadé le fichier
        this.onFileUpload.emit(this.fileUploads);
        console.log("this.fileUploads, ", this.fileUploads)
        // Afficher une notification de succès
        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps: {
            texto: `fichier "${file.name}" ajouté!`,
            state: 'success',
          },
        });
      } catch (error) {
        toast.custom(NiceToastComponent, {
          position: 'top-center',
          componentProps: {
            texto: `fichier "${file.name}" non ajouté!`,
            state: 'error',
          },
        });
        console.error('Erreur lors du traitement du fichier:', error);
      }
    } else {
      console.error('Aucun fichier sélectionné.');
    }
  }
}
