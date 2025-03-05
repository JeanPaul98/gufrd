import {Component} from '@angular/core';
import {UploadFilesModel} from "../../../../../../../../../../src/app/models/upload-files.model";
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';

@Component({
  selector: 'app-step-02',
  templateUrl: './step-03.component.html',
  styleUrl: './step-03.component.scss'
})
export class Step03Component {
  fileUploads: UploadFilesModel = localStorage.getItem('step3iocav') ?
    JSON
    .parse(localStorage.getItem('step3iocav') as string)
   : {};

  fileName = '';

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
    let  filess= []
    const files = this.fileUploads.file
    console.log("files", files);
    for (const file of files as File[]) {
      const json = this.stringify(file)
      console.log("json",json);

      filess.push(json)
    }

    // fichiers à envoyer
    let dataToStorage = {
      idFormalite: this.fileUploads.idFormalite,
      file: filess,
      filesBase64: this.fileUploads.filesBase64,
      fileNameTypePieceJointe: this.fileUploads.fileNameTypePieceJointe
    }

    localStorage.setItem('step3iocav', JSON.stringify(dataToStorage))
    this.stepperService.goToNextStep()

  }
}
