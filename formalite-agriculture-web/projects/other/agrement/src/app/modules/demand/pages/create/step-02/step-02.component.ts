import {ChangeDetectionStrategy, Component} from '@angular/core';
import {UntilDestroy} from "@ngneat/until-destroy";
import {UploadFilesModel} from "../../../../../../../../../../src/app/models/upload-files.model";
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';


@UntilDestroy()
@Component({
  selector: 'app-step-02',
  templateUrl: './step-02.component.html',
  styleUrl: './step-02.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Step02Component {
  fileUploads: UploadFilesModel = localStorage.getItem('step3AGREMENT') ?
    JSON
      .parse(localStorage.getItem('step3AGREMENT') as string)
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

    localStorage.setItem('step2AGREMENT', JSON.stringify(dataToStorage))
    this.stepperService.goToNextStep()

  }
}
