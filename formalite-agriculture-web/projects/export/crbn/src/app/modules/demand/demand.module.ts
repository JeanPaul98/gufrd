import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DemandRoutingModule } from './demand-routing.module';
import { DemandComponent } from './demand.component';
import {
  AccordionButtonDirective, AccordionComponent, AccordionItemComponent,
  ButtonDirective,
  DropdownComponent,
  DropdownItemDirective, DropdownMenuDirective, DropdownToggleDirective, FormControlDirective, FormSelectDirective,
  NavComponent,
  NavItemComponent,
  NavLinkDirective, TableDirective, TemplateIdDirective
} from "@coreui/angular";
import { NotSubmittedComponent } from './pages/not-submitted/not-submitted.component';
import { CreateComponent } from './pages/create/create.component';
import { ProcessedComponent } from './pages/processed/processed.component';
import { AcceptedComponent } from './pages/accepted/accepted.component';
import { SubmittedComponent } from './pages/submitted/submitted.component';
import { RejectedComponent } from './pages/rejected/rejected.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    DemandComponent,
    NotSubmittedComponent,
    CreateComponent,
    ProcessedComponent,
    AcceptedComponent,
    SubmittedComponent,
    RejectedComponent
  ],
  imports: [
    CommonModule,
    DemandRoutingModule,
    NavComponent,
    NavItemComponent,
    NavLinkDirective,
    ButtonDirective,
    DropdownComponent,
    DropdownItemDirective,
    DropdownMenuDirective,
    DropdownToggleDirective,
    TableDirective,
    AccordionButtonDirective,
    AccordionComponent,
    AccordionItemComponent,
    FormControlDirective,
    FormSelectDirective,
    ReactiveFormsModule,
    TemplateIdDirective
  ]
})
export class DemandModule { }
