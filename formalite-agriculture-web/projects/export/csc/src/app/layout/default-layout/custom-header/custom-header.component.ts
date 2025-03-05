import { Component } from '@angular/core';
import { ButtonModule } from '@coreui/angular';
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-custom-header',
  standalone: true,
  imports: [ButtonModule, NgOptimizedImage,],
  templateUrl: './custom-header.component.html',
  styleUrl: './custom-header.component.scss'
})
export class CustomHeaderComponent {

}
