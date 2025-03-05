import {AsyncPipe, DatePipe} from "@angular/common";
import {Component, inject, OnInit} from '@angular/core';
import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {
  ButtonDirective,
  NavComponent,
  NavItemComponent,
  NavLinkDirective,
  TableDirective,
  TooltipDirective
} from "@coreui/angular";
import {ConfirmModalComponent} from "@docs-components/confirm-modal/confirm-modal.component";
import {LoadingBarComponent} from "@docs-components/loading-bar/loading-bar.component";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {societeActions} from "../../../../../store/societe/societe.actions";
import {selectSocietes, selectSocietesLoading} from "../../../../../store/societe/societe.selectors";
import {SocieteModel} from "../../../../models/societe.model";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  standalone: true,
  imports: [
    ButtonDirective,
    NavComponent,
    NavItemComponent,
    NavLinkDirective,
    RouterOutlet,
    RouterLink,
    ConfirmModalComponent,
    DatePipe,
    LoadingBarComponent,
    TableDirective,
    TooltipDirective,
    AsyncPipe
  ],
  styleUrl: './list.component.scss'
})
export class ListComponent implements OnInit {
  #router:Router = inject(Router)
  private store = inject(Store)
  societes$: Observable<SocieteModel[]> = this.store.select(selectSocietes);
  loading$: Observable<boolean> = this.store.select(selectSocietesLoading);

  ngOnInit(){
    this.store.dispatch(societeActions.getAll());
  }

}
