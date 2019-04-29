import { Citizen } from './citizen.model';
import { Component, OnInit, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-citizens',
  templateUrl: './citizens.component.html',
  styleUrls: ['./citizens.component.scss']
})

// @NgModule({
//   imports:[CommonModule],
// })

export class CitizensComponent implements OnInit {
  searchByID: string = '';

  constructor() { }

  private citizens: Citizen[];


  ngOnInit() {
    this.citizens = [];
     /*  this.citizens = [{"nationalId": "12345678912345",
      "name": "salah Abdel Hai",
      "birthDate": "10/10/1992",
      "address": "60 st. medan Gohyna, october",
      "mobileNumber": "201092335926",
      "createdBy": "Ahmed Ali",
      "createdDate": "19/07/2019",
      "modifiedBy": "Mohamed Alaa",
      "modifiedDate": "20/07/2019"},

      {"nationalId": "12345678912345",
      "name": "salah Abdel Hai",
      "birthDate": "10/10/1992",
      "address": "60 st. medan Gohyna, october",
      "mobileNumber": "201092335926",
      "createdBy": "Ahmed Ali",
      "createdDate": "19/07/2019",
      "modifiedBy": "Mohamed Alaa",
      "modifiedDate": "20/07/2019"},
      
      {"nationalId": "12345678912345",
      "name": "salah Abdel Hai",
      "birthDate": "10/10/1992",
      "address": "60 st. medan Gohyna, october",
      "mobileNumber": "201092335926",
      "createdBy": "Ahmed Ali",
      "createdDate": "19/07/2019",
      "modifiedBy": "Mohamed Alaa",
      "modifiedDate": "20/07/2019"}]; */
  }


  searchchanged(event : Event) {
    console.log('search by : ' + event);
  
  //  this.citizens = [{"nationalId": "12345678912345",
  //  "name": "salah Abdel Hai",
  //  "birthDate": "10/10/1992",
  //  "address": "60 st. medan Gohyna, october",
  //  "mobileNumber": "201092335926",
  //  "createdBy": "Ahmed Ali",
  //  "createdDate": "19/07/2019",
  //  "modifiedBy": "Mohamed Alaa",
  //  "modifiedDate": "20/07/2019"},
  //  ]
}
}