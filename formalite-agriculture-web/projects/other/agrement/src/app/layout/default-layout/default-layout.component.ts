import {Component} from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {
  ContainerComponent,
  ShadowOnScrollDirective,
  SidebarBrandComponent,
  SidebarComponent,
  SidebarFooterComponent,
  SidebarHeaderComponent,
  SidebarNavComponent,
  SidebarToggleDirective,
  SidebarTogglerDirective
} from '@coreui/angular';

import {IconDirective} from '@coreui/icons-angular';
import {
  CommandComponent,
  EmptyDirective,
  GroupComponent,
  InputDirective,
  ItemDirective,
  ListComponent,
  SeparatorComponent
} from "@ngxpert/cmdk";
import {NgScrollbar} from 'ngx-scrollbar';
import {
  CustomClientHeaderComponent
} from "../../../../../../../src/components/header/custom-client-header/custom-client-header.component";
import {navItems} from './_nav';
import {CustomHeaderComponent} from './custom-header/custom-header.component';

import {DefaultFooterComponent, DefaultHeaderComponent} from './index';

function isOverflown(element: HTMLElement) {
  return (
    element.scrollHeight > element.clientHeight ||
    element.scrollWidth > element.clientWidth
  );
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
  styleUrls: ['./default-layout.component.scss'],
  standalone: true,
  imports: [
    SidebarComponent,
    SidebarHeaderComponent,
    SidebarBrandComponent,
    RouterLink,
    IconDirective,
    NgScrollbar,
    SidebarNavComponent,
    SidebarFooterComponent,
    SidebarToggleDirective,
    SidebarTogglerDirective,
    DefaultHeaderComponent,
    ShadowOnScrollDirective,
    ContainerComponent,
    RouterOutlet,
    DefaultFooterComponent,
    CustomHeaderComponent,
    CommandComponent,
    GroupComponent,
    InputDirective,
    ItemDirective,
    ListComponent,
    SeparatorComponent,
    EmptyDirective,
    CustomClientHeaderComponent
  ]
})

export class DefaultLayoutComponent {
  public navItems = navItems;


  onScrollbarUpdate($event: any) {
    // if ($event.verticalUsed) {
    // console.log('verticalUsed', $event.verticalUsed);
    // }
  }
}
