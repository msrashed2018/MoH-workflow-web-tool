import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
import { DatePipe } from '@angular/common';
import { Citizen } from '../model/citizen.model';
import { API_URL } from '../app.constants';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'

@Injectable({
    providedIn: 'root'
  })
  export class CitizenService {
  
  constructor(private http: HttpClient, public datepipe: DatePipe) { }
  deleteCitizen(id){
    return this.http.delete(`${API_URL}/citizens/${id}`);
  }
  findCitizen(nationalId : string) {
    let url = `${API_URL}`+'/citizens';
    if(nationalId && nationalId!==null && nationalId!="")
      url+= '/search/findByNationalId?id=';
    
    return this.http.get<Citizen[]>
      (url+nationalId).pipe( map(
        data => {
        return data;
      }));
  }

  retrieveCitizen(id){
    return this.http.get<Citizen>(`${API_URL}/citizens/${id}`);
  }
  retriveAll(latest_date :string) {
      
    return this.http.get<Citizen[]>
      (`${API_URL}`+'/citizens/search/findAllByDate?date='+latest_date).pipe( map(
        data => {
        return data;
      }));
  }

  createCitizen(citizen : Citizen){
    return this.http.post<Citizen>
    (`${API_URL}`+'/citizens',citizen).pipe( map(
      data => {
      return data;
    }));
  }

  retriveAllOccupations() {
      
    return this.http.get<Object>
      (`${API_URL}`+'/occupations').pipe( map(
        data => {
        return data;
      }));
  }

  retriveAllCities() {
      
    return this.http.get<Object>
      (`${API_URL}`+'/cities').pipe( map(
        data => {
        return data;
      }));
  }
  retriveAllGovernates() {
      
    return this.http.get<Object>
      (`${API_URL}`+'/governates').pipe( map(
        data => {
        return data;
      }));
  }

}

