import { BrowserModule } from '@angular/platform-browser';
import { Component, NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';


@Component({
  templateUrl: 'forms.component.html',
  
})

export class FormsComponent {

  constructor() { }
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';

  collapsed(event: any): void {
    // console.log(event);
  }

  expanded(event: any): void {
    // console.log(event);
  }

  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }

  addCitizen(event : Event){
    console.log("ading citizen: " + event);
    this.successMessage = true;
  }
}
