import {ViewportScroller} from "@angular/common";
import {Injectable} from '@angular/core';
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import _ from "lodash";
import {BehaviorSubject} from "rxjs";
import {customNavItems, INavDataModel} from './_nav';

@UntilDestroy()
@Injectable({
  providedIn: 'root'
})
export class DefaultLayoutService {
  public navItems = customNavItems;

  public activeNavItemSubject: BehaviorSubject<INavDataModel> = new BehaviorSubject<INavDataModel>(JSON.parse(localStorage.getItem('activeNavItemAGRI') || '{}'));
  public activeNavItemData!: INavDataModel;

  public sidebarVisibleSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(true);

  constructor(private viewportScroller: ViewportScroller) {
    // this.activeNavItemSubject = new BehaviorSubject<INavDataModel>(navItems[0]);
    this.activeNavItem
      .pipe(untilDestroyed(this))
      .subscribe(navItem => {
      this.activeNavItemData = navItem;
    });

  }

  checkIfNavLinkHaveChildren(navItem: INavDataModel){
    return _.has(navItem, 'children');
  }

  checkIfNavLinkIsTitle(navItem: INavDataModel){
    return _.has(navItem, 'title');
  }

  activatedNavItem(navItem: INavDataModel){
    this.activeNavItemSubject.next(navItem);
    localStorage.setItem('activeNavItemAGRI', JSON.stringify(navItem));
  }

  get activeNavItem(){
    return this.activeNavItemSubject.asObservable();
  }

  checkIfNavItemIsPresentByCode(code: string) {

  }

  findNavItemByCode = (code: string): INavDataModel  => {
    // Fonction récursive pour parcourir les sous-éléments
    const searchInChildren = (children: INavDataModel[]): INavDataModel | undefined => {
      for (const item of children) {
        if (item.code === code) {
          return item;
        }
        // Si l'élément a des sous-éléments, on continue de chercher
        if (item.children) {
          const found = searchInChildren(item.children);
          if (found) {
            return found;
          }
        }
      }
      return this.navItems[0];
    };

    // Parcourir le tableau principal `customNavItems`
    for (const navItem of customNavItems) {
      if (navItem.code === code) {
        return navItem;
      }
      // Si l'élément a des enfants, rechercher aussi dans les sous-éléments
      if (navItem.children) {
        const found = searchInChildren(navItem.children);
        if (found) {
          return found;
        }
      }
    }

    return this.navItems[0]; // Si aucun élément avec le code donné n'est trouvé
  };

  checkIfNavItemIsActive(navItem: INavDataModel){
    return _.isEqual(navItem, this.activeNavItemData);
  }

  scrollToElement() {
    console.log(this.activeNavItemData.name);
    this.viewportScroller.scrollToAnchor(this.activeNavItemData.name as string);
  }

  hideSidebar(){
    this.sidebarVisibleSubject.next(false);
  }

  openSidebar(){
    this.sidebarVisibleSubject.next(true);
  }

  toggleSidebar(){
    this.sidebarVisibleSubject.next(!this.sidebarVisibleSubject.value);
  }
}
