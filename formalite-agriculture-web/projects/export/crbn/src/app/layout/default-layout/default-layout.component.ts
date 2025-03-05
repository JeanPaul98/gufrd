import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {ContainerComponent} from '@coreui/angular';
import {
  CustomClientHeaderComponent
} from "../../../../../../../src/components/header/custom-client-header/custom-client-header.component";
import {CustomHeaderComponent} from "./custom-header/custom-header.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
  styleUrls: ['./default-layout.component.scss'],
  standalone: true,
  imports: [
    ContainerComponent,
    RouterOutlet,
    CustomHeaderComponent,
    CustomClientHeaderComponent
  ]
})
export class DefaultLayoutComponent {
}
