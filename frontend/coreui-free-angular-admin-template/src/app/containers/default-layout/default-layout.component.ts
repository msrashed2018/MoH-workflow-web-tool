import { Component, OnDestroy, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { navItems, adminNavItems } from '../../_nav';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../../services/authentication/basic-authentication.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnDestroy {
  public navItems = navItems;
  public sidebarMinimized = true;
  private changes: MutationObserver;
  public element: HTMLElement;
  constructor(private authService: BasicAuthenticationService, private router: Router, @Inject(DOCUMENT) _document?: any ) {

    //you can define navitems depend login user roles and authorities 
    // if(true){

    //   this.navItems = adminNavItems;
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
    this.authService.logout();
    this.router.navigateByUrl("/login");
  }

  ngOnDestroy(): void {
    this.changes.disconnect();
  }

  getUsername(){
    return this.authService.getAuthenticatedUser();
  }
}
