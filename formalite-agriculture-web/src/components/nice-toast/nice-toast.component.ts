import {Component, Input} from '@angular/core';
import {CommonModule} from "@angular/common";
import {toast} from "ngx-sonner";

@Component({
  selector: 'app-nice-toast',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './nice-toast.component.html',
  styleUrl: './nice-toast.component.scss'
})
export class NiceToastComponent {

  @Input() texto: string = '';
  @Input() state: 'success' | 'error' | 'warning' | 'info' | 'default' | 'loading' = 'default';
  close() {
    toast.dismiss();
  }
}
