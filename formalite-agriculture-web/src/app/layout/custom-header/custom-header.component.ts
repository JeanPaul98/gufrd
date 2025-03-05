import {CommonModule} from "@angular/common";
import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {ButtonModule, DropdownModule} from '@coreui/angular';
import {NiceToastComponent} from "@docs-components/nice-toast/nice-toast.component";
import {Notification, Novu} from "@novu/js";
import {KeycloakService} from "keycloak-angular";
import {toast} from "ngx-sonner";
import {fromPromise} from "rxjs/internal/observable/innerFrom";
import {tap} from "rxjs/operators";
import {TippyTooltipDirective} from "../../../directives/tippy-tooltip/tippy-tooltip.directive";
import {environment} from "../../../environments/environment";
import {DefaultLayoutService} from "../default-layout/default-layout.service";

@Component({
  selector: 'app-custom-header',
  standalone: true,
  imports: [ButtonModule, TippyTooltipDirective, DropdownModule, CommonModule],
  templateUrl: './custom-header.component.html',
  styleUrl: './custom-header.component.scss'
})
export class CustomHeaderComponent implements OnInit {

 notifCounter: WritableSignal<number> = signal<number>(0);
 notifications: WritableSignal<any> = signal<Notification[]>([]);
  private novu!: Novu;


  constructor(public defaultLayoutService: DefaultLayoutService, private service: KeycloakService) {}



  logout() {
    localStorage.clear()
    this.service.logout();
  }

  async ngOnInit() {
    this.novu = new Novu({
      subscriberId: environment.novu.subscriberId,
      applicationIdentifier: environment.novu.applicationIdentifier,
      backendUrl: environment.novu.backendUrl,
      socketUrl: environment.novu.socketUrl

    });
    const response = await this.novu.notifications.list({
      limit: 30,

    });


    const notifications = response?.data?.notifications
    console.log("notifications", notifications)
    this.notifications.set(notifications)
    this.notifCounter.set(notifications!.length)

    this.novu.on("notifications.notification_received", (data) => {
      console.log("new notification =>", data);
      this.notifCounter.set(this.notifCounter() + 1)
      this.notifications.set([...this.notifications(), data])
      toast.custom(NiceToastComponent, {
        position: 'top-right',
        componentProps:{
          texto: 'Nouvelle Demande ajoutée!',
          state: 'success'
        }
      });
    });

    this.novu.on("notifications.unread_count_changed", (data) => {
      console.log("new unread notifications count =>", data);
      // this.notifCounter.set(data.result)
    });
  }

   readNotification(notification: Notification) {
    console.log("readNotification", notification);
    fromPromise(this.novu.notifications.read({notificationId: notification.id}))
      .pipe(tap(() => this.notifications.set(this.notifications().filter((n: Notification) => n.id !== notification.id))))
      .subscribe(() => {
      toast.custom(NiceToastComponent, {
        position: 'top-right',
        componentProps:{
          texto: 'Notification lue!',
          state: 'success'
        }
      });
    });
   };
}
