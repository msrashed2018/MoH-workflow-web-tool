import { Component, OnDestroy, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { navItems, adminNavItems, cashierNavItems } from '../../_nav';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../services/authentication/jwt/token-storage.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnDestroy {
  public navItems = navItems;
  public sidebarMinimized = true;
  private changes: MutationObserver;
  public element: HTMLElement;
  constructor(private token: TokenStorageService,  private router: Router, @Inject(DOCUMENT) _document?: any ) {

    //you can define navitems depend login user roles and authorities 
    // if(true){

    //   this.navItems = adminNavItems;
    // }


    // if(token.getAuthorities().includes("ROLE_CASHIER")){
    //   this.navItems = cashierNavItems;
    // }


    this.changes = new MutationObserver((mutations) => {
      this.sidebarMinimized = _document.body.classList.contains('sidebar-minimized');
    });
    this.element = _document.body;
    this.changes.observe(<Element>this.element, {
      attributes: true,
      attributeFilter: ['class']
    });
  }

  logout(){
    this.token.signOut();
    window.location.reload();
    // this.authService.logout();
    // this.router.navigateByUrl("/login");
  }

  ngOnDestroy(): void {
    this.changes.disconnect();
  }

  getUsername(){
    return this.token.getUsername();
    // return this.authService.getAuthenticatedUser();
  }
}
