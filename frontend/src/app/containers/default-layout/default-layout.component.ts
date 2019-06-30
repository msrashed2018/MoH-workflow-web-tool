import { Component, OnDestroy, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import {  adminNavItems, cashierNavItems, citizenNavItems, continueRegisteringNavItems, eyeNavItems, bonesNavItems, medicalRegisteringNavItems, reviewNavItems, approveNavItems } from '../../_nav';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../services/authentication/jwt/token-storage.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnDestroy {
  public navItems;
  public sidebarMinimized = true;
  private changes: MutationObserver;
  public element: HTMLElement;
  constructor(private token: TokenStorageService,  private router: Router, @Inject(DOCUMENT) _document?: any ) {

    //you can define navitems depend login user roles and authorities 
    // if(true){

    //   this.navItems = adminNavItems;
    // }

    if(token.getAuthorities().includes("ROLE_ADMIN")){
      this.navItems = adminNavItems;
    }else if(token.getAuthorities().includes("ROLE_CITIZEN")){
      this.navItems = citizenNavItems;
    }else if(token.getAuthorities().includes("ROLE_CASHIER")){
      this.navItems = cashierNavItems;
    }else if(token.getAuthorities().includes("ROLE_CONTINUE_REGISTERING")){
      this.navItems = continueRegisteringNavItems;
    }else if(token.getAuthorities().includes("ROLE_EYE")){
      this.navItems = eyeNavItems;
    }else if(token.getAuthorities().includes("ROLE_BONES")){
      this.navItems = bonesNavItems;
    }else if(token.getAuthorities().includes("ROLE_MEDICAL_REGISTERING")){
      this.navItems = medicalRegisteringNavItems;
    }else if(token.getAuthorities().includes("ROLE_REQUESTS_REVIEWER")){
      this.navItems = reviewNavItems;
    }else if(token.getAuthorities().includes("ROLE_REQUESTS_APPROVER")){
      this.navItems = approveNavItems;
    }else{
      console.log("user has no authorities for any app pages")
    }
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
