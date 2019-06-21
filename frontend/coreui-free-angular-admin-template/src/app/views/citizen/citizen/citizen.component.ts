import { Component, OnInit, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormControl, FormBuilder, FormsModule } from '@angular/forms';
import { Citizen } from '../../../model/citizen.model';
import { CitizenService } from '../../../services/citizenService';
import { Router } from '@angular/router';
import { OccupationService } from '../../../services/administration/occupation.service';
import { GovernateService } from '../../../services/administration/governate.service';
import { Occupation } from '../../../model/occupation.model';
import { Governate } from '../../../model/governate.model';
import { City } from '../../../model/city.model';
import { Gender } from '../../../model/gender.model';
import { GenderService } from '../../../services/administration/gender.service';
import { DatePipe } from '@angular/common';
import { BasicAuthenticationService } from '../../../services/authentication/basic-authentication.service';
import { RequestService } from '../../../services/request.service';
import { Request } from '../../../model/request.model';
import { RequestTypeService } from '../../../services/administration/request-type.service';
import { RequestType } from '../../../model/request-type.model';
import { Custom } from '../../../model/custom.model';
import { TrafficManagement } from '../../../model/traffic-management.model';
import { TrafficManagementService } from '../../../services/administration/traffic-management.service';
import { CustomService } from '../../../services/administration/custom.service';

@Component({
  selector: 'app-citizen',
  templateUrl: './citizen.component.html',
  styleUrls: ['./citizen.component.scss']
})
export class CitizenComponent implements OnInit {
  citizen : Citizen= new Citizen;
  successMessage: boolean = false;
  errorMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  message: string = "";

  
  
  public occupations: Occupation[] = [];
  public requestTypes: RequestType[] = [];
  public governates : Governate[] = [];
  public cities : City[] = [];
  // public genders : Gender[] = [];
  public customs : Custom[] = [];
  public trafficManagements : TrafficManagement[] = [];
  public selectedOccupationId : number
  public selectedRequestTypeId : number
  public selectedGovernateId : number
  public selectedCityId : number
  public selectedGenderId : number
  public selectedTrafficManagementId : number
  public selectedCustomId : number
  public requestPrice : number = 0;
  constructor(private requestTypeService: RequestTypeService, private requestService: RequestService, private formBuilder: FormBuilder, private authenticationService: BasicAuthenticationService, private datepipe: DatePipe, private genderService: GenderService, private governateService: GovernateService, private occupationService: OccupationService, private citizenService: CitizenService, private trafficManagementService: TrafficManagementService, private customService: CustomService, private router: Router ) { }

  ngOnInit() {
    // this.fillCities();
    this.fillGovernates();
    // this.fillGenders();
    this.fillOccupations();
    this.fillRequestTypes();
    this.fillTrafficManagements();
    this.fillCustoms();

    
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
    // let id = this.governates.find(g => g.name === value).id;
    this.fillCities(value);
  }
  onRequestTypeChanged(value){
    for( var x= 0 ; x< this.requestTypes.length ; x++ ){
      if(this.requestTypes[x].id == value){
        this.requestPrice = this.requestTypes[x].price;
      }
    }
  }
  onNationalIdChange(value){
    if(value.length == 14){

      //getting governate from national id
      let governateCode = value[7]+value[8];
      for (var x = 0; x<this.governates.length; x++) {
        if(this.governates[x].code == governateCode){
          this.selectedGovernateId = this.governates[x].id;
          this.fillCities(this.selectedGovernateId);
        }
      }
      //getting birthdate from national id
      let date;
      if(value[0] == "2"){
        date = "19" + value[1]+ value[2]+ "-"+ value[3] + value[4] + "-" + value[5]+value[6];
      }else if (value[0] == "3"){
        let date = "20" + value[1]+ value[2]+ "-"+ value[3] + value[4] + "-" + value[5]+value[6];
      }
      this.citizen.birthDate = this.datepipe.transform(new Date(date), 'yyyy-MM-dd');
    
      //getting gender from national id
      if ( value[12] % 2 != 0) {
        this.citizen.gender = 'ذكر'
        // this.selectedGenderId = 2;
      }else{
        this.citizen.gender = 'أنثي'
        // this.selectedGenderId = 1;
      } 
    }
  }
  onSave(){
  
    this.citizen.createdBy = this.authenticationService.getAuthenticatedUser();
    this.citizen.createdDate =this.datepipe.transform(new Date(), 'yyyy-MM-dd');

    let governate = new Governate
    governate.id = this.selectedGovernateId;
    this.citizen.governate = governate;

    let city = new City;
    city.id = this.selectedCityId;
    this.citizen.city = city;

    let occupation = new Occupation;
    occupation.id = this.selectedOccupationId;
    this.citizen.occupation = occupation;


    // let gender = new Gender;
    // gender.id = this.selectedGenderId;
    // this.citizen.gender = gender;


    this.citizenService.createCitizen(this.citizen).subscribe(
      result => {
        this.errorMessage = false;
        
        this.citizen = result;
        this.createRequest();
        // this.router.navigateByUrl("/citizen/search");
      },
      error => {
        console.log('oops', error);
        this.errorMessage = true;
        this.message = error.error.message;
       }
    );
  }
  createRequest(){
    let request = new Request();
    request.requestDate = this.citizen.createdDate;
    request.createdBy = this.citizen.createdBy;

    let requestType = new RequestType;
    requestType.id = this.selectedRequestTypeId;
    request.requestType = requestType;

    for(var x =0 ; x< this.requestTypes.length ; x++){
      if(this.selectedRequestTypeId == this.requestTypes[x].id){
        if(this.requestTypes[x].name == 'كشف عادي' || this.requestTypes[x].name == 'كشف مستعجل'){
          request.state = 'PENDING_PAYMENT';
        }else{
          request.state = 'PENDING_CONTINUE_REGISTERING';
        }
      }
    }

    if( this.selectedCustomId > 0 ){
      let custom = new Custom;
      custom.id = this.selectedCustomId;
      request.custom = custom;
    }

    if( this.selectedTrafficManagementId > 0 ){
      let trafficManagement = new TrafficManagement;
      trafficManagement.id = this.selectedTrafficManagementId;
      request.trafficManagement = trafficManagement;
    }


    this.requestService.createRequest(this.citizen.id,request).subscribe(
      result=>{
         this.router.navigateByUrl("/citizen/search");
      },
      error=>{
        console.log('oops', error);
        this.errorMessage = true;
        this.message = error.error.message;
      }
    )
  }

  fillOccupations(){
    this.occupationService.retrieveAllOccupations(0,200).subscribe(
      result => {
        this.occupations = result['content'];
      },
      error => {
        console.log('oops', error);
      });
  }
  close(){
    this.router.navigateByUrl("/citizens/search");
  }
  // fillGenders(){
  //   this.genderService.retrieveAllGenders().subscribe(
  //     result => {
  //       this.genders = result;
  //     },
  //     error => {
  //       console.log('oops', error);
  //     });
  // }

  fillCities(governateId){
    this.governateService.retrieveGovernateCities(governateId).subscribe(
      result => {
        this.cities = result;
      },
      error => {
        console.log('oops', error);
      });
  }

  fillGovernates(){
    this.governateService.retrieveAllGovernates(0,100).subscribe(
      result => {
        this.governates = result['content'];
      },
      error => {
        console.log('oops', error);
      });
  }
  fillRequestTypes(){
    this.requestTypeService.retrieveAllRequestTypes(0,100).subscribe(
      result => {
        this.requestTypes = result['content'];
      },
      error => {
        console.log('oops', error);
      });
  }
  fillCustoms(){
    this.customService.retrieveAllCustoms(0,100).subscribe(
      result => {
        this.customs = result['content'];
      },
      error => {
        console.log('oops', error);
      });
  }
  fillTrafficManagements(){
    this.trafficManagementService.retrieveAllTrafficManagement(0,100).subscribe(
      result => {
        this.trafficManagements = result['content'];
      },
      error => {
        console.log('oops', error);
      });
  }

}
