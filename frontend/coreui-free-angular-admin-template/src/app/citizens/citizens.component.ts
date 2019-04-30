import { AlertModule, AlertConfig } from 'ngx-bootstrap/alert';
import { Citizen } from './citizen.model';
import { Component, OnInit, NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { CitizenService } from './../sevices/citizenService/citizenService';

@Component({
  selector: 'app-citizens',
  templateUrl: './citizens.component.html',
  styleUrls: ['./citizens.component.scss']
})

export class CitizensComponent implements OnInit {
  searchByID: string = '';

  constructor(private citizenService: CitizenService, private datepipe: DatePipe) { }

  private citizens: Citizen[];
  private noDataFound: boolean = false;
  private errorMessage: boolean = false;

  ngOnInit() {
    this.citizens = [];
    this.retriveAllCitizens();
  }



  searchchanged(event: Event) {
    this.citizens = [];
    console.log('search by : ' + this.searchByID);
    this.errorMessage = false;
    this.noDataFound = false;

    this.citizenService.findCitizen(this.searchByID)
      .subscribe(
        result => {
          console.log(" find by id");
          if (typeof result !== 'undefined' && result !== null) {
            this.noDataFound = false;
            this.citizens = result;
          }else{
            this.noDataFound = true;
          }
          console.log("returned citizen : " + this.citizens)
        },
        error => {
          console.log('oops', error);
          this.errorMessage = true;

        }
      );

  }

  retriveAllCitizens(){
    this.citizens = [];
    console.log('retrive all');
    this.errorMessage = false;
    this.noDataFound = false;
    let date=new Date();
    let latest_date =this.datepipe.transform(date, 'yyyy-MM-dd');
    console.log("today:" + latest_date)
    this.citizenService.retriveAll(latest_date)
      .subscribe(
        result => {
          console.log(" retrive all");
          if (typeof result !== 'undefined' && result !== null) {
            this.noDataFound = false;
            this.citizens= result;
          }else{
            this.noDataFound = true;
          }
          console.log("returned citizen : " + this.citizens)
        },
        error => {
          console.log('oops: ', error);
          this.errorMessage = true;

        }
      );
  }
}