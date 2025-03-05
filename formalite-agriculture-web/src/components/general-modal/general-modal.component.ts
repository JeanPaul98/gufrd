import {CommonModule} from "@angular/common";
import {Component, EventEmitter, Input, Output} from '@angular/core';
import {
  ButtonDirective,
  ModalBodyComponent,
  ModalComponent,
  ModalFooterComponent,
  ModalHeaderComponent, ModalModule,
  ModalTitleDirective,
  SpinnerComponent
} from "@coreui/angular";

@Component({
  selector: 'app-general-modal',
  standalone: true,
  imports: [
    CommonModule,
    ButtonDirective,
    ModalModule,
    SpinnerComponent
  ],
  templateUrl: './general-modal.component.html',
  styleUrl: './general-modal.component.scss'
})
export class GeneralModalComponent {
  @Input() titleMdl: string = 'Confirmer la soumission';
  @Input() message: string = 'Cliquer sur le bouton "Confirmer" pour soumettre la demande!';
  @Input() confirmButton: string = 'Confirmer';
  @Input() cancelButton: string = 'Annuler';
  @Input() isLoading: boolean = false;
  @Input() visible: boolean = false;
  @Input() disabled: boolean = false;
  @Input() danger: boolean = false;
  @Input() size: 'sm' | 'lg' | 'xl' | undefined  = undefined;
  @Input('isConfirm') isConfirm: boolean = false;

  @Output() onConfirm: EventEmitter<any> = new EventEmitter();
  @Output() onCancel: EventEmitter<any> = new EventEmitter();

  constructor() {
  }

  onConfirmClick() {
    this.onConfirm.emit();
  }

  onCancelClick() {
    this.onCancel.emit();
  }
}
