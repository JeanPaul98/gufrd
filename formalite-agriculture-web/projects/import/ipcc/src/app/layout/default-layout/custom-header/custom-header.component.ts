import { Component } from '@angular/core';
import { ButtonModule } from '@coreui/angular';

@Component({
  selector: 'app-custom-header',
  standalone: true,
  imports: [ButtonModule, ],
  templateUrl: './custom-header.component.html',
  styleUrl: './custom-header.component.scss'
})
export class CustomHeaderComponent {

}
