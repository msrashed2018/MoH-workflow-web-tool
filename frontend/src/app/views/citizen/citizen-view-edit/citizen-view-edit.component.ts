import { Component, OnInit, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormControl, FormBuilder, FormsModule } from '@angular/forms';
import { Citizen } from '../../../model/citizen.model';
import { CitizenService } from '../../../services/citizenService';
import { Router, ActivatedRoute } from '@angular/router';
import { OccupationService } from '../../../services/administration/occupation.service';
import { GovernateService } from '../../../services/administration/governate.service';
import { Occupation } from '../../../model/occupation.model';
import { Governate } from '../../../model/governate.model';
import { City } from '../../../model/city.model';
import { Gender } from '../../../model/gender.model';
import { GenderService } from '../../../services/administration/gender.service';
import { DatePipe } from '@angular/common';
import { RequestService } from '../../../services/request.service';
import { Request } from '../../../model/request.model';
import { RequestType } from '../../../model/request-type.model';
import { RequestTypeService } from '../../../services/administration/request-type.service';
import { CustomService } from '../../../services/administration/custom.service';
import { TrafficManagementService } from '../../../services/administration/traffic-management.service';
import { Custom } from '../../../model/custom.model';
import { TrafficManagement } from '../../../model/traffic-management.model';
import { TokenStorageService } from '../../../services/authentication/jwt/token-storage.service';

@Component({
  selector: 'app-citizen-view-edit',
  templateUrl: './citizen-view-edit.component.html',
  styleUrls: ['./citizen-view-edit.component.scss']
})
export class CitizenViewEditComponent implements OnInit {
  citizen : Citizen= new Citizen;
  
  isCollapsed: boolean = false;
  isCitizenRequestsCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  iconCitizenRequestsCollapse: string = 'icon-arrow-up';
  errorMessage: boolean = false;
  message: string = "";
  disabled : boolean = false;
  citizenId : number;
  componentMode;
  public occupations: Occupation[] = [];
  public requests: Request[] = [];
  public requestTypes: RequestType[] = [];
  public governates : Governate[] = [];
  public customs : Custom[] = [];
  public trafficManagements : TrafficManagement[] = [];
  public cities : City[] = [];
  // public genders : Gender[] = [];
  public selectedOccupationId : number
  selectedRequestTypeId : number = 0;
  public selectedGovernateId : number
  public selectedTrafficManagementId : number
  public selectedCustomId : number
  public selectedCityId : number
  public selectedGenderId : number
  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder,  private requestTypeService: RequestTypeService, private requestService: RequestService, private authenticationService: TokenStorageService, private datepipe: DatePipe, private genderService: GenderService, private governateService: GovernateService, private occupationService: OccupationService, private citizenService: CitizenService, private router: Router, private trafficManagementService: TrafficManagementService, private customService: CustomService) { }

  ngOnInit() {
    // this.fillCities();
    this.fillGovernates();
    // this.fillGenders();
    this.fillOccupations();
    this.fillRequestTypes();

    this.route.params.forEach((urlParams) => {
      this.citizenId= urlParams['id'];
      this.componentMode=urlParams['componentMode'];
      this.displayCitizenDetails();
      if(this.componentMode == "editMode"){
          this.disabled = false;
          this.fillTrafficManagements();
          this.fillCustoms();
      }else{
        this.disabled = true;
      }
    });
    this.fillCitizenRequests();
  }

  displayCitizenDetails(){
    this.citizenService.retrieveCitizen(this.citizenId).subscribe(
      response => {
        this.citizen = response as Citizen;
        
        if(this.citizen.governate != null){
          this.selectedGovernateId = this.citizen.governate.id;
          this.fillCities(this.selectedGovernateId);
        }
        if(this.citizen.city != null){
          this.selectedCityId = this.citizen.city.id;
        }
        if(this.citizen.occupation != null){
          this.selectedOccupationId = this.citizen.occupation.id;
        }
        // if(this.citizen.gender != null){
        //   this.selectedGenderId = this.citizen.gender.id;
        // }
        
      }
    )
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

  citizenRequestsCollapsed(event: any): void {
    // console.log(event);
  }

  citizenRequestsExpanded(event: any): void {
    // console.log(event);
  }
  toggleCitizenRequestsCollapse(): void {
    this.isCitizenRequestsCollapsed = !this.isCitizenRequestsCollapsed;
    this.iconCitizenRequestsCollapse = this.isCitizenRequestsCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  onGovernateChanged(value){
    console.log("value = "+ value)
    // let id = this.governates.find(g => g.name === value).id;
    this.fillCities(value);
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
  
    this.citizen.modifiedBy = this.authenticationService.getUsername();
    this.citizen.modifiedDate =this.datepipe.transform(new Date(), 'yyyy-MM-dd');

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

    this.citizenService.updateCitizen(this.citizen.id,this.citizen).subscribe(
      result => {
        this.router.navigateByUrl("/citizen/search");
        this.errorMessage = false;

      },
      error => {
        this.errorMessage = true;
        this.message = error.error.message
      }
    );
  }
  close(){
    this.router.navigateByUrl("/citizen/search");
  }
  fillOccupations(){
    this.occupationService.retrieveAllOccupations(0,100).subscribe(
      result => {
        this.occupations = result['content'];
      },
      error => {
        console.log('oops', error);
      });
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

  fillCitizenRequests(){
    this.requestService.retrieveCitizenRequests(this.citizenId).subscribe(
      result=>{
         this.requests = result;
      },
      error=>{
        console.log('oops', error);
        this.errorMessage = true;
        this.message = error.error.message;
      }
    )
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
  createNewRequest(){
    let request = new Request();
    request.requestDate = this.datepipe.transform(new Date(), 'yyyy-MM-dd');
    request.createdBy = this.authenticationService.getUsername();

    let requestType = new RequestType;
    requestType.id = this.selectedRequestTypeId;
    request.requestType = requestType;

    if(this.selectedCustomId !=0){
      let custom = new Custom();
      custom.id = this.selectedCustomId;
      request.custom = custom;
    }
    if(this.selectedTrafficManagementId !=0){
      let trafficManagement = new TrafficManagement();
      trafficManagement.id = this.selectedTrafficManagementId;
      request.trafficManagement = trafficManagement;
    }

    // for(var x =0 ; x< this.requestTypes.length ; x++){
    //   if(this.selectedRequestTypeId == this.requestTypes[x].id){
    //     if(this.requestTypes[x].name == 'كشف عادي' || this.requestTypes[x].name == 'كشف مستعجل'){
    //       request.state = 'PENDING_PAYMENT';
    //     }else{
    //       request.state = 'PENDING_CONTINUE_REGISTERING';
    //     }
    //   }
    // }


    // if(requestType.name == 'كشف عادي' || requestType.name == 'كشف مستعجل'){
    //   request.state = 'NEW';
    // }else{
    //   request.state = 'PAYMENT_DONE';
    // }
    
    this.requestService.createRequest(this.citizenId,request).subscribe(
      result=>{
        this.fillCitizenRequests();
      },
      error=>{
        console.log('oops', error);
        this.errorMessage = true;
        this.message = error.error.message;
      }
    )
  }

  onDeleteRequest(requestId){
    this.requestService.deleteRequest(this.citizenId,requestId).subscribe(
      result=>{
        this.fillCitizenRequests();
      },
      error=>{
        console.log('oops', error);
        this.errorMessage = true;
        this.message = error.error.message;
      }
    )
  }

  onEditRequest(requestId){
    this.router.navigate(["/request/",requestId,{componentMode: "editMode", citizenId:this.citizenId}]);
  }
}