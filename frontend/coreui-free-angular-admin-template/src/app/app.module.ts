import { HttpIntercepterBasicAuthService } from './sevices/authinticationService/http/http-intercepter-basic-auth.service';
import { FormsModule } from '@angular/forms';
import { HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouteGuardService } from './sevices/authinticationService/route-guard.service';
import { AlertModule } from 'ngx-bootstrap/alert';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LocationStrategy, HashLocationStrategy, CommonModule, DatePipe } from '@angular/common';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

import { AppComponent } from './app.component';

// Import containers
import { DefaultLayoutComponent } from './containers';


import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';

const APP_CONTAINERS = [
  DefaultLayoutComponent
];

import {
  AppAsideModule,
  AppBreadcrumbModule,
  AppHeaderModule,
  AppFooterModule,
  AppSidebarModule,
} from '@coreui/angular';

// Import routing module
import { AppRoutingModule } from './app.routing';

// Import 3rd party components
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { CitizensComponent } from './citizens/citizens.component';
// import { CitizensComponent } from './citizens/citizens.component';

@NgModule({
  imports: [
    BrowserModule,
    // CommonModule,
    AppRoutingModule,
    AppAsideModule,
    AppBreadcrumbModule.forRoot(),
    AppFooterModule,
    AppHeaderModule,
    AppSidebarModule,
    PerfectScrollbarModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    ChartsModule,
    HttpModule,
    HttpClientModule,
    FormsModule,    
    AlertModule.forRoot(),  

  ],
  declarations: [
    AppComponent,
    ...APP_CONTAINERS,
    P404Component,
    P500Component,
    LoginComponent,
    // CitizensComponent,
    RegisterComponent,
    // CitizensComponent

  ],
  providers: [{
    provide: LocationStrategy,
    useClass: HashLocationStrategy,
    
  },
  RouteGuardService,
  {provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterBasicAuthService, multi: true },
  DatePipe],
    bootstrap: [ AppComponent ],
    // exports: [CitizensComponent]
})
export class AppModule { }
