import { Citizen } from './../../citizens/citizen.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BasicAuthenticationService, AuthenticationBean } from './../../sevices/authinticationService/basic-authentication.service';
import { Component, NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { SelectorMatcher } from '@angular/compiler';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})

export class LoginComponent {
  constructor(private authService: BasicAuthenticationService,
    private router: Router, ) { }
  username: string;
  password: string;
  message: string = "";
  citizen: Citizen[];
  invalidAuthinticationMessage: boolean = false;
  login() {
    console.log("calling auth service with : " + this.username + ", and password: " + this.password);
    this.authService.executeAuthenticationService(this.username, this.password)
      .subscribe(result => {

        console.log(result.message)
        if (result.message === "You are authenticated") {
          this.router.navigateByUrl("");
        }
        else
          console.log("you are not authinticated");
        this.invalidAuthinticationMessage = true;

      },
        error => {
          console.log('oops', error);
          this.invalidAuthinticationMessage = true;

        }
      );



  }

}
