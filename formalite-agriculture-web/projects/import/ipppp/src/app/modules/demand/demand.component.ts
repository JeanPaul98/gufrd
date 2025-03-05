import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Event, NavigationEnd, Router} from "@angular/router";
import {filter, Observable, Subscription} from "rxjs";
import {DemandService} from "./services/demand.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";

@UntilDestroy()
@Component({
  selector: 'app-ipppp-demand',
  templateUrl: './demand.component.html',
  styleUrl: './demand.component.scss'
})
export class DemandComponent implements OnInit, OnDestroy {
  activeRoute = inject(ActivatedRoute);
  activeLink: string = localStorage.getItem('currentTabsIPPPP') ?? "not-submitted";
  navs: { link: string, title: string, count: number }[] = [
    {link: 'not-submitted', title: 'Non soumises', count: 0},
    {link: 'submitted', title: 'Soumises', count: 0},
    {link: 'accepted', title: 'Acceptées', count: 0},
    {link: 'processed', title: 'Traitées', count: 0},
    {link: 'rejected', title: 'Rejetées', count: 0},
  ];

  activeLink$: Observable<string> = this.service.currentRoute$;
  isHideDemandBar$ = this.service.isHideDemandBar$;
  urlRoute: string = localStorage.getItem('currentRoute') ?? "";
  private routerSubscription: Subscription = new Subscription();

  constructor(private router: Router, private service: DemandService) {
    this.service.isHideDemandBar$ = this.service.isHideDemandBarSubject.asObservable();
  }

  ngOnInit(): void {
    this.listenerRouteChange();
    this.getBadge();
  }

  listenerRouteChange() {
    this.routerSubscription = this.router.events
      .pipe(filter((event: Event) => event instanceof NavigationEnd), untilDestroyed(this))
      .subscribe((event: any) => {
        this.onRouteChange(event.urlAfterRedirects);
      });
  }

  onClickNav(nav: string): void {
    this.activeLink = nav;
    this.router.navigate(["demand", nav]);
    localStorage.setItem('currentTabsIPPPP', nav);
  }

  onRouteChange(url: string) {
    this.urlRoute = url;
    localStorage.setItem('currentRoute', url);
  }

  ngOnDestroy(): void {
    this.routerSubscription.unsubscribe();
    localStorage.removeItem('currentTabsIPPPP');
  }

  private getBadge() {
    this.service.getDemandBadgeCount().subscribe((res) => {
      if (res) {
        this.navs[0].count = res.nonSoumise;
        this.navs[1].count = res.soumise;
        this.navs[2].count = res.accepter;
        this.navs[3].count = res.traiter;
        this.navs[4].count = res.rejeter;
      }
    });
  }
}

