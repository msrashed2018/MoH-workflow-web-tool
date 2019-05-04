import { Component, OnInit, NgModule } from '@angular/core';
import { Citizen } from '../../../model/citizen.model';
import { CitizenService } from '../../../services/citizenService';
import { DatePipe, CommonModule } from '@angular/common';
import { AlertModule, AlertConfig } from 'ngx-bootstrap/alert';



@Component({
  selector: 'app-list-citizens',
  templateUrl: './list-citizens.component.html',
  styleUrls: ['./list-citizens.component.scss']
})
export class ListCitizensComponent implements OnInit {
  searchByID: string = '';
  private citizens: Citizen[];
  private noDataFound: boolean = false;
  private errorMessage: boolean = false;
  constructor(private citizenService: CitizenService, private datepipe: DatePipe) { }

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
