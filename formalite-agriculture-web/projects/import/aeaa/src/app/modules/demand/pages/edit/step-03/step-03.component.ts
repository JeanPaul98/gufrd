import {Component, OnInit} from '@angular/core';
import {DemandAeaaCompletModel} from "../../../../../../../../../../src/app/models/demand-aeaa.model";
import {UploadFilesModel} from "../../../../../../../../../../src/app/models/upload-files.model";
import {
  ListShowFilesService
} from "../../../../../../../../../../src/components/list-show-files/list-show-files.service";
import {StepperService} from '../../../../../../../../../../src/components/stepper/stepper.service';

@Component({
  selector: 'app-step-02',
  templateUrl: './step-03.component.html',
  styleUrl: './step-03.component.scss'
})
export class Step03Component implements OnInit {
  demand: DemandAeaaCompletModel = JSON.parse(localStorage.getItem('demandDetail') as string);


  constructor(private stepperService: StepperService, private listShowFilesService: ListShowFilesService){}

  ngOnInit(): void {
    // this.getListFiles();
  }


  fileUploads: UploadFilesModel = localStorage.getItem('step3EditAEAA') ?
    JSON
      .parse(localStorage.getItem('step3EditAEAA') as string)
    : {};

  getListFiles(){
    this.listShowFilesService.getPiecesByIdFormalite(this.demand.idFormalite).subscribe((res) => {
      console.log("res", res);
      this.fileUploads = res as UploadFilesModel;
    });
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

    localStorage.setItem('step3EditAEAA', JSON.stringify(dataToStorage));
    this.stepperService.goToNextStep()

  }
}
