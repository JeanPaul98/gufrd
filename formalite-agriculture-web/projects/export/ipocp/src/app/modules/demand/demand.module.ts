import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DemandComponent } from './demand.component';
import { DemandRoutingModule } from './demand-routing.module';
import { ProcessedComponent } from './pages/processed/processed.component';
import { RejectedComponent } from './pages/rejected/rejected.component';
import { CreateComponent } from './pages/create/create.component';
import { AccordionModule, ButtonModule, DropdownModule, FormModule, NavModule, SharedModule, TableModule, TabsModule } from '@coreui/angular';
import { FormsModule } from '@angular/forms';
import { AcceptedComponent } from './pages/accepted/accepted.component';
import { SubmittedComponent } from './pages/submitted/submitted.component';
import { NotSubmittedComponent } from './pages/not-submitted/not-submitted.component';
import { NgSelectModule } from '@ng-select/ng-select';


@NgModule({
  declarations: [
    
    DemandComponent,
    ProcessedComponent,
    RejectedComponent,
    CreateComponent,
    AcceptedComponent,
    SubmittedComponent,
    NotSubmittedComponent,
  ],
  imports: [
    CommonModule,
    DemandRoutingModule,
    ButtonModule,
    TabsModule,
    NavModule,
    TableModule,
    DropdownModule,
    AccordionModule,
    SharedModule,
    FormsModule,
    FormModule,
    NgSelectModule,
     FormsModule
  ]
})
export class DemandModule { }
