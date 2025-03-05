import {Component} from '@angular/core';
import {StepperService} from "../../../../../../../../../../../src/components/stepper/stepper.service";
import {UploadFilesModel} from "../../../../../../../../../../../src/app/models/upload-files.model";

@Component({
  selector: 'app-ipn-step-02',
  templateUrl: './step-02.component.html',
  styleUrl: './step-02.component.scss'
})
export class Step02Component {
  fileUploads: UploadFilesModel = localStorage.getItem('step2IPN') ? JSON.parse(localStorage.getItem('step2IPN') as string) : {};

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
    let filess = [];
    const files = this.fileUploads.file;
    for (const file of files as File[]) {
      const json = this.stringify(file);
      filess.push(json);
    }

    // fichiers à envoyer
    let dataToStorage = {
      idFormalite: this.fileUploads.idFormalite,
      file: filess,
      filesBase64: this.fileUploads.filesBase64,
      fileNameTypePieceJointe: this.fileUploads.fileNameTypePieceJointe
    }

    localStorage.setItem('step2IPN', JSON.stringify(dataToStorage));
    this.stepperService.goToNextStep();
  }

}
