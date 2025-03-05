import {Component} from '@angular/core';
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import {UploadFilesModel} from "../../../../../../../../../../../src/app/models/upload-files.model";

@Component({
  selector: 'app-ipppp-step-02',
  templateUrl: './step-03.component.html',
  styleUrl: './step-03.component.scss'
})
export class Step03Component {
  fileUploads: UploadFilesModel = localStorage.getItem('step3IPPPP') ? JSON.parse(localStorage.getItem('step3IPPPP') as string) : {};

  constructor(private stepperService: StepperService) {
  }

  onFileUpload(fileUploads: UploadFilesModel) {
    this.fileUploads = fileUploads;
  }

  stringify(obj: any) {
    const replacer: any = {};
    for (const key in obj) {
      replacer[key] = obj[key];
    }
    return replacer;
  }

  onClickPrevious() {
    this.stepperService.goToPreviousStep()
  }

  onClickNext() {
    let filesToUpload = [];
    const files = this.fileUploads.file;

    for (const file of files as File[]) {
      const json = this.stringify(file)
      filesToUpload.push(json)
    }

    // fichiers à envoyer
    let dataToStorage = {
      idFormalite: this.fileUploads.idFormalite,
      file: filesToUpload,
      filesBase64: this.fileUploads.filesBase64,
      fileNameTypePieceJointe: this.fileUploads.fileNameTypePieceJointe
    }

    localStorage.setItem('step3IPPPP', JSON.stringify(dataToStorage))
    this.stepperService.goToNextStep()

  }
}
