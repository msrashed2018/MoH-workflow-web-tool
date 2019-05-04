import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Component, NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { SelectorMatcher } from '@angular/compiler';
import { BasicAuthenticationService } from '../../services/authentication/basic-authentication.service';
import { Citizen } from '../../model/citizen.model';
import { UserAuthorities } from '../../model/user-authorities.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})

export class LoginComponent {
  constructor(private authService: BasicAuthenticationService,
    private router: Router, ) { }
  username: string = 'salah';
  password: string = '123456789';
  message: string = "";
  userAuthorities : UserAuthorities;
  citizen: Citizen[];
  invalidAuthinticationMessage: boolean = false;
  login() {
    console.log("calling auth service with : " + this.username + ", and password: " + this.password);
    this.authService.executeAuthenticationService(this.username, this.password)
      .subscribe(result => {
        this.userAuthorities= result;
        console.log(this.userAuthorities.message)
        console.log(this.userAuthorities.authorities)
        this.router.navigateByUrl("");
        // console.log(result.message)
        // if (result.message === "You are authenticated") {
        //   this.router.navigateByUrl("");
        // }
        // else
        //   console.log("you are not authinticated");
        // this.invalidAuthinticationMessage = true;

      },
        error => {
          console.log('oops', error);
          this.invalidAuthinticationMessage = true;

        }
      );



  }

}
