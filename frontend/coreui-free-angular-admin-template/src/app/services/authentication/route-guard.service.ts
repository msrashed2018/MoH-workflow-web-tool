import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { BasicAuthenticationService } from './basic-authentication.service';

@Injectable(
  // providedIn: 'root'
)
export class RouteGuardService implements CanActivate {

  constructor(
    private BasicAuthenticationService: BasicAuthenticationService,
    private router: Router) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if (this.BasicAuthenticationService.isUserLoggedIn()) {
      return true;
    } else {
      console.log(" user is not loged");

      this.router.navigate(['login']);

      return false;
    }
  }
}
