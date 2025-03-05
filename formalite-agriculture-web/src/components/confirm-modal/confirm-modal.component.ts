import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ButtonModule, ModalModule, SpinnerModule} from "@coreui/angular";

@Component({
  selector: 'app-confirm-modal',
  standalone: true,
  imports: [CommonModule, ModalModule, ButtonModule, SpinnerModule],
  templateUrl: './confirm-modal.component.html',
  styleUrl: './confirm-modal.component.scss'
})
export class ConfirmModalComponent {
  @Input() title: string = 'Confirmer la soumission';
  @Input() message: string = 'Cliquer sur le bouton "Confirmer" pour soumettre la demande!';
  @Input() confirmButton: string = 'Confirmer';
  @Input() cancelButton: string = 'Annuler';
  @Input() isLoading: boolean = false;
  @Input() visible: boolean = false;
  @Input() type: string = 'confirm';

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
