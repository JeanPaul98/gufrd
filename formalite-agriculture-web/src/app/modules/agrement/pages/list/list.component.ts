import {CommonModule} from "@angular/common";
import {Component, inject, OnInit} from '@angular/core';
import {Router, RouterModule} from "@angular/router";
import {ButtonDirective, NavComponent, NavItemComponent, NavLinkDirective} from "@coreui/angular";
import {Observable} from "rxjs";
import {DefaultLayoutService} from "../../../../layout/default-layout/default-layout.service";

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [
    CommonModule,
    ButtonDirective,
    NavComponent,
    NavItemComponent,
    NavLinkDirective,
    RouterModule
  ],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent implements OnInit {
  navs: {link: string, title: string, count: number}[] = [
    // {link: 'not-submitted', title: 'Non soumises', count: 4},
    {link: 'submitted', title: 'Soumises', count: 2},
    {link: 'accepted', title: 'Acceptées', count: 1},
    {link: 'processed', title: 'Traitées', count: 1},
    {link: 'rejected', title: 'Rejetées', count: 1},
  ];
  activeLink$!: Observable<string>;
  activeLink:string;

  router = inject(Router);
  defaultLayoutService = inject(DefaultLayoutService);

  isHideDemandBar$!:Observable<boolean>;

  ngOnInit(): void {
    this.activeLink = "submitted";
    this.router.navigate(["/agrement/list", this.activeLink])
    this.defaultLayoutService.openSidebar();
  }

  exportToExcell() {
    // const exportData: IExcelExport<DemandAeaaModel> = {
    //   data: this.currentDemands,
    //   fileName: 'exported-data'
    // };
    //
    // exportToExcel(exportData);
  }

  onClickNav(nav: string): void {
    this.activeLink = nav;
    this.router.navigate(["/agrement/list", nav]);
    // localStorage.setItem('currentTabsADTAdmin', nav);
    // this.getBadgeCount();
  }
}
