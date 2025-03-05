import { Component, OnInit } from '@angular/core';
import { DemandService } from '../../services/demand.service';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { NgSelectConfig } from '@ng-select/ng-select';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrl: './create.component.scss',
})
export class CreateComponent implements OnInit {
  items = [1, 2, 3, 4];
  fileName = '';
  urlRoute$: Observable<string> = this.demandService.currentRoute$;
  selectedCar: number = 1;

  cars = [
      { id: 1, name: 'Cirés' },
      { id: 2, name: 'Café' },
      { id: 3, name: 'Fruits' },
      { id: 4, name: 'Légumes' },
  ];

  selectedPieceJustificative: number = 1;
  pieceJustificatives: any[] = [
    { id: 1, name: 'Formulaire de demande FD' },
    { id: 2, name: 'Certificat de Fumigation CF' },
    { id: 3, name: 'Connaissement CNSM' },
  ];

  constructor(
    private demandService: DemandService,
    private location: Location,
  ) {
  }

  ngOnInit(): void {
    this.demandService.isHideDemandBarSubject.next(true);
    this.location.subscribe(() => {
      this.demandService.isHideDemandBarSubject.next(false);
    });
  }

  onClickBack() {
    this.demandService.isHideDemandBarSubject.next(false);
    this.demandService.back();
  }

  onFileSelected(event: Event) {
    const target: HTMLInputElement = event.target as HTMLInputElement;

    const file: File | null | undefined = target?.files?.item(0);

    console.log('====================================');
    console.log(target?.files);
    console.log('====================================');
    if (file) {
      this.fileName = file.name;
    }
  }
}
