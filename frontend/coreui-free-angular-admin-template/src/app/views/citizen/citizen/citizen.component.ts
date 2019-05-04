import { Component, OnInit, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormControl, FormBuilder, FormsModule } from '@angular/forms';
import { Citizen } from '../../../model/citizen.model';
import { CitizenService } from '../../../services/citizenService';
import { Router } from '@angular/router';
import { OccupationService } from '../../../services/administration/occupation.service';
import { GovernateService } from '../../../services/administration/governate.service';

@Component({
  selector: 'app-citizen',
  templateUrl: './citizen.component.html',
  styleUrls: ['./citizen.component.scss']
})
export class CitizenComponent implements OnInit {
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  name = new FormControl('');
  nationalId = new FormControl('');
  // public birthDate : string;
  // public createdDate: string;
  // public modifiedDate: String;
  // public modifiedBy : string;
  // public createdBy : string;
  public address = new FormControl('');
  // public mobileNumber: string;
  public gender = new FormControl('');
  public city = new FormControl('');
  public governate= new FormControl('');
  public occupation= new FormControl('');
  public citizen : Citizen = new Citizen();
  public occupations: any;
  public governates : any;
  public cities : any;
  constructor(private formBuilder: FormBuilder, private governateService: GovernateService, private occupationService: OccupationService, private citizenService: CitizenService, private router: Router ) { }

  ngOnInit() {
    this.occupations = [];
    this.cities = [];
    this.governates = [];
    // this.fillCities();
    this.fillGovernates();
    this.fillOccupations();
  }
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
  onGovernateChanged(value){
    let id = this.governates.find(g => g.name === value).id;
    this.fillCities(id);
  }
  onNationalIdChange(value){
    console.log("National ID = "+value);
    value
  }
  addCitizen(){
  
    this.citizen.city = this.city.value;
    this.citizen.address = this.address.value;
    this.citizen.birthDate = "1991-06-20";
    this.citizen.createdBy = "salah";
    this.citizen.createdDate = "2019-04-30";
    this.citizen.gender = this.gender.value;
    this.citizen.governate = this.governate.value;
    this.citizen.mobileNumber = "201092335926";
    this.citizen.name = this.name.value;
    this.citizen.nationalId = this.nationalId.value;
    this.citizen.occupation = this.occupation.value;

    this.citizenService.createCitizen(this.citizen).subscribe(
      result => {
        console.log(" create ");
        // this.successMessage = true;
        this.router.navigateByUrl("");


      },
      error => {
        console.log('oops', error);
        this.successMessage = false;
      }
    );
  }

  fillOccupations(){
    this.occupationService.retrieveAllOccupations().subscribe(
      result => {
        console.log(" occupations list ");
        this.occupations = result;
      },
      error => {
        console.log('oops', error);
      });
  }

  fillCities(governateId){
    this.governateService.retrieveGovernateCities(governateId).subscribe(
      result => {
        console.log(" cities list ");
        this.cities = result;
      },
      error => {
        console.log('oops', error);
      });
  }

  fillGovernates(){
    this.governateService.retrieveAllGovernates().subscribe(
      result => {
        console.log(" governates list ");
        this.governates = result;
      },
      error => {
        console.log('oops', error);
      });
  }
}
