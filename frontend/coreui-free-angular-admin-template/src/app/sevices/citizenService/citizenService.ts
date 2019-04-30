import { Citizen } from './../../citizens/citizen.model';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
import { DatePipe } from '@angular/common';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'

@Injectable({
    providedIn: 'root'
  })
  export class CitizenService {
  
  constructor(private http: HttpClient, public datepipe: DatePipe) { }

  findCitizen(nationalId : string) {
    let url = 'http://localhost:8080/ministry-of-health/api/citizens';
    if(nationalId && nationalId!==null && nationalId!="")
      url+= '/search/findByNationalId?id=';
    
    return this.http.get<Citizen[]>
      (url+nationalId).pipe( map(
        data => {
            console.log("service return : "+data) 
        return data;
      }));
  }


  retriveAll(latest_date :string) {
      
    return this.http.get<Citizen[]>
      ('http://localhost:8080/ministry-of-health/api/citizens/search/findAllByDate?date='+latest_date).pipe( map(
        data => {
            console.log("service return : "+data) 
        return data;
      }));
  }

  createCitizen(citizen : Citizen){
    return this.http.post<Citizen[]>
    ('http://localhost:8080/ministry-of-health/api/citizens',citizen).pipe( map(
      data => {
          console.log("service return : "+data) 
      return data;
    }));
  }

  retriveAllOccupations() {
      
    return this.http.get<Object>
      ('http://localhost:8080/ministry-of-health/api/occupations').pipe( map(
        data => {
            console.log("service return : "+data) 
        return data;
      }));
  }

  retriveAllCities() {
      
    return this.http.get<Object>
      ('http://localhost:8080/ministry-of-health/api/cities').pipe( map(
        data => {
            console.log("service return : "+data) 
        return data;
      }));
  }
  retriveAllGovernates() {
      
    return this.http.get<Object>
      ('http://localhost:8080/ministry-of-health/api/governates').pipe( map(
        data => {
            console.log("service return : "+data) 
        return data;
      }));
  }

}

