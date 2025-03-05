import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Event, NavigationEnd, Router } from '@angular/router';
import { filter, Observable, Subscription } from 'rxjs';
import { DemandService } from './services/demand.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import {DemandAeaaModel} from "../../../../../../../models/demand-aeaa.model";
import {BadgesCountModel} from "../../../../../../../models/badges-count.model";
import {exportToExcel, IExcelExport} from "../../../../../../../utils/xlsx-export.utils";


@UntilDestroy()
@Component({
  selector: 'app-demand',
  templateUrl: './demand.component.html',
  styleUrl: './demand.component.scss'
})
export class DemandComponent implements OnInit, OnDestroy {
  activeRoute = inject(ActivatedRoute);
  // router = inject(Router);
  activeLink:string = localStorage.getItem('currentTabsAAEAAdmin') ?? "submitted";
  badgeCount!: BadgesCountModel;
  navs: {link: string, title: string, count: number}[] = [
    // {link: 'not-submitted', title: 'Non soumises', count: 4},
   {link: 'submitted', title: 'Soumises', count: 2},
    {link: 'accepted', title: 'Acceptées', count: 1},
    {link: 'processed', title: 'Traitées', count: 1},
    {link: 'rejected', title: 'Rejetées', count: 1},
  ];

  currentDemands: DemandAeaaModel[] = [];

  activeLink$!: Observable<string>;
  isHideDemandBar$!:Observable<boolean>;
  urlRoute: string =  localStorage.getItem('currentRoute') ?? "";
  private routerSubscription: Subscription = new Subscription();

  constructor(private router: Router, private demandService: DemandService) {
      this.activeLink$ = this.demandService.currentRouteSubject.asObservable();
      this.isHideDemandBar$ = this.demandService.isHideDemandBarSubject.asObservable();
  }
  ngOnInit(): void {
    this.router.navigate(['importations/aeaa/demand', this.activeLink])
    this.listenerRouteChange()
    this.getBadgeCount();
    // this.updateBadge();
    this.demandService.isHideDemandBarSubject.subscribe((res) => {
      console.log('isHideDemandBarSubject', res)
    })
    this.demandService.currentDemandsSubject.subscribe((res) => {
      this.currentDemands = res;
    })
  }

  getBadgeCount() {
    this.demandService.getDemandBadgeCount().subscribe((res) => {
      this.badgeCount = res as BadgesCountModel;
      this.demandService.demandNotSubmittedCountSubject.next(this.badgeCount.nonSoumise);
      this.demandService.demandSubmittedCountSubject.next(this.badgeCount.soumise);
      this.demandService.demandAcceptedCountSubject.next(this.badgeCount.accepter);
      this.demandService.demandRejectedCountSubject.next(this.badgeCount.rejeter);
      this.demandService.demandProcessedCountSubject.next(this.badgeCount.traiter);
      this.updateBadge();
      console.log('getBadgeCount', res)
    })
  }



  exportToExcell() {
    const exportData: IExcelExport<DemandAeaaModel> = {
      data: this.currentDemands,
      fileName: 'exported-data'
    };

    exportToExcel(exportData);
  }

  updateBadge() {
    // Pour mettre à jour le nombre de demandes non soumises
    /*this.demandService.demandNotSubmittedCountSubject.pipe(
      untilDestroyed(this)
    ).subscribe((res: number) => {
      this.navs[0].count = res;
    })*/

    // Pour mettre à jour le nombre de demandes soumises
    this.demandService.demandSubmittedCountSubject.pipe(
      untilDestroyed(this)
    ).subscribe((res: number) => {
      this.navs[0].count = res;
    })

    // Pour mettre à jour le nombre de demandes acceptées
    this.demandService.demandAcceptedCountSubject.pipe(
      untilDestroyed(this)
    ).subscribe((res: number) => {
      this.navs[1].count = res;
    })

    // Pour mettre à jour le nombre de demandes rejetées
    this.demandService.demandProcessedCountSubject.pipe(
      untilDestroyed(this)
    ).subscribe((res: number) => {
      this.navs[2].count = res;
    })

    // Pour mettre à jour le nombre de demandes traitées
    this.demandService.demandRejectedCountSubject.pipe(
      untilDestroyed(this)
    ).subscribe((res: number) => {
      this.navs[3].count = res;
    })
  }

  listenerRouteChange() {
    this.routerSubscription = this.router.events
    .pipe(
      filter((event: Event) => event instanceof NavigationEnd),
      untilDestroyed(this)
    )
    .subscribe((event: any) => {
      console.log('Navigation vers:', event);
      this.onRouteChange(event.urlAfterRedirects);
    });
  }


  onClickNav(nav: string): void {
    this.activeLink = nav;
    this.router.navigate(["importations/aeaa/demand", nav]);
    localStorage.setItem('currentTabsAAEAAdmin', nav);
    this.getBadgeCount();
  }

  onRouteChange(url: string) {
    // console.log('onRouteChange', url);
    this.urlRoute = url;
    localStorage.setItem('currentRoute', url);
  }

  ngOnDestroy(): void {
    this.routerSubscription.unsubscribe();
  }


}
