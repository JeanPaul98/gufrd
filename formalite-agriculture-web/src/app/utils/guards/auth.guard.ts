import {inject} from "@angular/core";
import {CanActivateFn, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {RolesEnum} from "../../enums/roles.enum";
import {DefaultLayoutService} from "../../layout/default-layout/default-layout.service";

export const authGuard: CanActivateFn = () => {
  const keycloakService = inject(KeycloakService);
  const router = inject(Router);
  const defaultLayoutService = inject(DefaultLayoutService);
  const userProfile =JSON.parse( localStorage.getItem('user-profile') as string) ?? null;

  // Vérifie si l'utilisateur est connecté
  if (userProfile) {
    // Récupère les rôles de l'utilisateur
    const userRoles = keycloakService.getUserRoles();

    // Variable pour éviter les redirections multiples
    let isRedirecting = false;

    // Redirection basée sur le rôle de l'utilisateur
    if (userRoles?.includes(RolesEnum.INSPECTEUR_VETERINAIRE) && !isRedirecting) {
      isRedirecting = true;
      alert('INSPECTEUR_VETERINAIRE');
      defaultLayoutService.activatedNavItem(defaultLayoutService.findNavItemByCode('adt'));
      router.navigate(['/importations/adt']);
      return false; // Ne permet pas l'accès après redirection
    }
    else if (userRoles?.includes(RolesEnum.INSPECTEUR_PHYTOSANITAIRE) && !isRedirecting) {
      isRedirecting = true;
      alert('INSPECTEUR_PHYTOSANITAIRE');
      defaultLayoutService.activatedNavItem(defaultLayoutService.findNavItemByCode('ipn'));
      router.navigate(['/importations/ipn']);
      return false; // Ne permet pas l'accès après redirection
    }
    else if (userRoles?.includes(RolesEnum.MANAGER)) {
      defaultLayoutService.activatedNavItem(defaultLayoutService.findNavItemByCode('ipn'));
      return true; // Accès autorisé pour le MANAGER
    }
  }

  keycloakService.login()
  return false; // Si l'utilisateur n'est pas connecté ou aucun rôle ne correspond
};
