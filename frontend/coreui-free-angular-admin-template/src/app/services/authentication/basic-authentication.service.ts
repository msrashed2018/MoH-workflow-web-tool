import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
import { Citizen } from '../../model/citizen.model';
import { UserAuthorities } from '../../model/user-authorities.model';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  constructor(private http: HttpClient) { }

  executeAuthenticationService(username, password) {
    let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);

    let headers = new HttpHeaders({
        Authorization: basicAuthHeaderString
      })


    return this.http.get<UserAuthorities>(
      'http://localhost:8080/ministry-of-health/api/authenticate',
      {headers}).pipe(
        map(
          data => {
            sessionStorage.setItem(AUTHENTICATED_USER, username);
            sessionStorage.setItem(TOKEN, basicAuthHeaderString);
            return data;
          }
        )
      );

      
  }

  findByID(){
    return this.http.get<Citizen []>
      ('http://localhost:8080/ministry-of-health/api/citizens/search/findAllByDate?date=2018-07-19').pipe( map(
        data => {console.log(data) 
        return data;
      }));
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem(AUTHENTICATED_USER)
  }

  getAuthenticatedToken() {
    if(this.getAuthenticatedUser())
      return sessionStorage.getItem(TOKEN)
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

  logout(){
    sessionStorage.removeItem(TOKEN)
    sessionStorage.removeItem(AUTHENTICATED_USER)
    console.log("user is logged out;");
  }

}

export class AuthenticationBean{
  constructor(public message:string) { }
}
