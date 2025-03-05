import {CommonModule} from "@angular/common";
import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {ButtonDirective, TableModule, TooltipDirective} from "@coreui/angular";
import {UntilDestroy} from "@ngneat/until-destroy";
import {PdfViewerModule} from "ng2-pdf-viewer";
import {ListFilesModel} from "../../app/models/list-files.model";
import {TippyTooltipDirective} from "../../directives/tippy-tooltip/tippy-tooltip.directive";
import {GeneralModalComponent} from "../general-modal/general-modal.component";
import {LoadingBarComponent} from "../loading-bar/loading-bar.component";
import {ListShowFilesService} from "./list-show-files.service";


@UntilDestroy()
@Component({
  selector: 'app-list-show-files',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    ButtonDirective,
    TooltipDirective,
    TippyTooltipDirective,
    GeneralModalComponent,
    PdfViewerModule,
    LoadingBarComponent
  ],
  templateUrl: './list-show-files.component.html',
  styleUrl: './list-show-files.component.scss',
})
export class ListShowFilesComponent implements OnInit {

  @Input("idFormalite") idFormalite: number = 0;
  @Input("listePieces") listFiles: ListFilesModel[] = [];
  openModal: boolean = false;
  selectedFile: ListFilesModel | null = null;
  titleModal: string = '';

  page: number = 1;
  totalPages!: number;
  isLoaded: boolean = false;
  pdfSrc!: Uint8Array;

  urlPdf: string = '';
  isPdf: boolean = true;
  fileType: string = '';


  constructor(private listShowFilesService: ListShowFilesService, private cdr: ChangeDetectorRef) {}


  ngOnInit(): void {
    if (this.idFormalite === 0 || this.idFormalite === undefined) {

      return;
    }
    this.listShowFilesService.getPiecesByIdFormalite(this.idFormalite).subscribe((res) => {
      this.listFiles = res as ListFilesModel[];
      console.log('ATD-acl', this.listFiles)
      this.cdr.detectChanges();
    });
  }

  private checkFileType(url: string) {
    this.isLoaded = false;
    this.fileType = '';
    this.listShowFilesService.determineFileType(url).subscribe((type) => {
      this.isLoaded = true;
      console.log(type);
      this.fileType = type
    });
  }

  onOpenModal(file: ListFilesModel) {
    this.page = 1;
    this.totalPages = 1;
    this.selectedFile = file;
    // this.checkFileType(this.selectedFile.urlPj);
    console.log(this.selectedFile);
    this.titleModal = 'Detail fichier : ' + this.selectedFile?.nomOriginePieceJointe;
    this.openModal = true;
  }

  onCloseModal() {
    this.openModal = false;
  }

  afterLoadComplete(pdfData: any) {
    this.totalPages = pdfData.numPages;
    this.isLoaded = true;
  }

  nextPage() {
    this.page++;
  }

  prevPage() {
    this.page--;
  }


  getPDF(file: ListFilesModel) {
    this.page = 1;
    this.totalPages = 1;
    this.listShowFilesService.downloadFileByUrl(file.nomOriginePieceJointe).subscribe((res) => {
      const tempBlob = new Blob([res as Blob], { type: 'application/pdf' });
      /*this.filePreviewUrl = URL.createObjectURL(
        new File([res as Blob], file.nomOriginePieceJointe as string, { type: 'application/pdf' }),
      );*/

      const fileReader = new FileReader();
      fileReader.onload = () => {
        this.pdfSrc = new Uint8Array(fileReader.result as ArrayBuffer);
        // this.onOpenModal(file);
        this.onOpenModal(file);

      };
      fileReader.readAsArrayBuffer(tempBlob);
    });
    /*this.listShowFilesService.getPDF(file.urlPj).subscribe((res) => {
      const blob = new Blob([res], {type: "application/pdf"});
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = url;
      link.download = `${'olivier'}.pdf`;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    });*/
  }

  openPdf(id: number) {
    window.open(`/api/files/${id}`, "_blank");
  }

}
