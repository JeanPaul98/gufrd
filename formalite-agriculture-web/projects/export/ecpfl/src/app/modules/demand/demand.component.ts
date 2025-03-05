import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Event, NavigationEnd, Router} from "@angular/router";
import {filter, Observable, Subscription} from "rxjs";
import {DemandService} from "./services/demand.service";

@Component({
  selector: 'app-demand',
  templateUrl: './demand.component.html',
  styleUrl: './demand.component.scss'
})
export class DemandComponent implements OnInit, OnDestroy {
  activeRoute = inject(ActivatedRoute);
  // router = inject(Router);
  activeLink: string = 'not-submitted';
  navs: { link: string, title: string }[] = [
    {link: 'not-submitted', title: 'Non soumises'},
    {link: 'submitted', title: 'Soumises'},
    {link: 'accepted', title: 'Acceptées'},
    {link: 'processed', title: 'Traitées'},
    {link: 'rejected', title: 'Rejetées'},
  ];
  // NAVS_ENUM = {
  //   ALL: 'Not-submitted',
  //   SEND: 'Submitted',
  //   PROCESSED: 'Accepted',
  //   FAILED: 'Processed',
  //   REJECTED: 'Rejected',
  // };
  // trueActiveRoute = this.activeRoute.snapshot.url.map(url => url.path);
  // activeLink$: Observable<string> = this.demandService.currentRoute$;
  urlRoute: string = localStorage.getItem('currentRoute') ?? "";

  private routerSubscription: Subscription = new Subscription();

  constructor(private router: Router, private demandService: DemandService,) {
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.listenerRouteChange()
  }

  listenerRouteChange() {
    this.routerSubscription = this.router.events
      .pipe(
        filter((event: Event) => event instanceof NavigationEnd)
      )
      .subscribe((event: any) => {
        console.log('Navigation vers:', event);
        this.onRouteChange(event.urlAfterRedirects);
      });
  }


  onClickNav(nav: string): void {
    this.activeLink = nav;
    this.router.navigate(["demand", nav]);
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

