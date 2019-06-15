import { Component, OnInit, Input } from '@angular/core';
import { RequestService } from '../../../../services/request.service';
import { HttpResponse, HttpEventType } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { RequestTypeService } from '../../../../services/administration/request-type.service';
import { RequestStatusService } from '../../../../services/administration/request-status.service';
import { TrafficManagementService } from '../../../../services/administration/traffic-management.service';
import { CustomService } from '../../../../services/administration/custom.service';
import { EyeRevealSettingService } from '../../../../services/administration/eye-reveal-setting.service';
import { EyeMeasureService } from '../../../../services/administration/eye-measure.service';
import { EquipmentService } from '../../../../services/administration/equipment.service';
import { DisabilityService } from '../../../../services/administration/disability.service';
import { CommitteeService } from '../../../../services/administration/committee.service';
import { Committee } from '../../../../model/committee.model';
import { BasicAuthenticationService } from '../../../../services/authentication/basic-authentication.service';
import { Request } from '../../../../model/request.model';
import { API_URL } from '../../../../app.constants';
import { ActivatedRoute } from '@angular/router';
import { Citizen } from '../../../../model/citizen.model';

@Component({
  selector: 'app-continue-registering-data',
  templateUrl: './continue-registering-data.component.html',
  styleUrls: ['./continue-registering-data.component.scss']
})
export class ContinueRegisteringDataComponent implements OnInit {
  isRequestDataCollapsed: boolean = false;
  isMedicalRevealDataCollapsed: boolean = true;
  isFileUploadDataCollapsed: boolean = true;
  iconRequestDataCollapse: string = 'icon-arrow-up';
  iconMedicalRevealDataCollapse: string = 'icon-arrow-down';
  iconFileUploadDataCollapse: string = 'icon-arrow-down';


  //---------------------------------------------------------------------------------------------------------
  
  //models fields--------------------------------------------------------------------------------------------
  citizenId : number = 0;
  requestId : number = 0;
  request : Request = new Request();
  citizen = new Citizen();
  requestType = {};
  public documents = [{}];
  eyeCommittee = new Committee();
  bonesCommittee = new Committee();
  committees : Committee[];
  eyeCommittees : Committee[];
  bonesCommittees : Committee[];
  selectedEyeCommitteeId: number = 0;
  selectedBonesCommitteeId: number = 0;

  errorMessage : boolean = false;
  requestDataErrorMessage : string = '';
  requestDataSuccessMessage : string = '';
  medicalRevealErrorMessage : string = '';
  medicalRevealSuccessMessage : string = '';
  successMessage : boolean = false;
  message: string = "";
  //file upload fields---------------------------------------------------------------------------------------
  showFile = false
  fileUploadErrorMessage : string;
  fileUploads: Map<string, string> = new Map<string, string>();
  requestDocuments: string[];
  uploading = false;
  @Input() fileUpload: string;
  selectedFiles: FileList
  currentFileUpload: File
  progress: { percentage: number } = { percentage: 0 }
  //---------------------------------------------------------------------------------------------------------

  constructor(private route:ActivatedRoute, private basicAuthenticationService: BasicAuthenticationService, private formBuilder: FormBuilder, private committeeService:CommitteeService, private disabilityService:DisabilityService, private equipmentService: EquipmentService, private eyeMeasureService: EyeMeasureService, private eyeRevealSettingService: EyeRevealSettingService, private customService: CustomService, private requestService: RequestService, private requestTypeService: RequestTypeService, private requestStatusService: RequestStatusService, private trafficManagementService: TrafficManagementService) { }

  ngOnInit() {
    this.route.params.forEach((urlParams) => {
      this.requestId= urlParams['requestId'];
      // this.requestId= 1;
      this.refreshData();
    });
  }
  refreshData(){
    this.requestService.retrieveRequest(this.requestId).subscribe(
    result => {
      this.request = result as Request;
      this.citizen = this.request.citizen;
      this.requestType = this.request.requestType;
      
      if(this.request.eyeCommittee != null){
        this.selectedEyeCommitteeId = this.request.eyeCommittee.id;
      }
      if(this.request.bonesCommittee != null){
        this.selectedBonesCommitteeId = this.request.bonesCommittee.id;
      }
     
    },
    error => {
      this.errorMessage = true;
      this.message = error.error.message;
      console.log('oops', error);
    });

  }

  onSaveMedicalReveal(){

  
    // console.log(JSON.stringify(this.request))

    if(this.selectedEyeCommitteeId !=0 ){
      let committee: Committee = new Committee;
      committee.id= this.selectedEyeCommitteeId;
      this.request.eyeCommittee = committee;
    }

    if(this.selectedBonesCommitteeId !=0 ){
      let committee: Committee = new Committee;
      committee.id= this.selectedBonesCommitteeId;
      this.request.bonesCommittee = committee;
    }
    this.requestService.updateRequest(this.citizen.id,this.request.id, this.request).subscribe(
      result => {
        this.medicalRevealErrorMessage = "";
        this.medicalRevealSuccessMessage = "تم حفظ بيانات الكشف الطبي بنجاح"
      },
      error => {
        console.log('oops', error);
        this.medicalRevealSuccessMessage ="";
        this.medicalRevealErrorMessage = error.error.message;
       }
    )
  }

  fillCommittees(){
    this.committeeService.retrieveCommitteesByType('رمد').subscribe(
      result => {
        this.eyeCommittees = result;
      },
      error => {
        console.log('oops', error);
    });
    this.committeeService.retrieveCommitteesByType('عظام').subscribe(
      result => {
        this.bonesCommittees = result;
      },
      error => {
        console.log('oops', error);
    });
  }
// file upload methods--------------------------------------------------------------------------------------
  showFiles(enable: boolean) {
    this.showFile = enable
    if (enable) {
      this.requestService.getFiles(this.request.id, 'PERSONAL').subscribe(
        result =>{
          this.requestDocuments = result as any;
          this.requestDocuments .forEach(element => {
            this.fileUploads.set(element,`${API_URL}/requests/${this.request.id}/documents/${element}`);
            // console.log("key: "+ element + "   value :"+`${API_URL}/requests/${this.request.id}/documents/${element}`);
          });
        },
        error =>{
          console.log('oops', error.error)
        }
      )
    }
  }
  selectFile(event) {
    this.selectedFiles = event.target.files;
    this.uploading = false;
  }
  getFile(fileName){
    this.requestService.getFile(this.request.id,fileName);
  }
   upload() {
     this.uploading = true;
     this.requestService.pushFileToStorage(this.requestId,'PERSONAL',this.selectedFiles).subscribe(
       result => {
         // console.log(result);
         if (result.type === HttpEventType.UploadProgress) {
           this.progress.percentage = Math.round(100 * result.loaded / result.total);
           
         } else if (result instanceof HttpResponse) {
           console.log('File is completely uploaded!');
           this.showFiles(true);
         }
         
       },
       error =>{
         this.fileUploadErrorMessage = error.error;
         console.log('oops', error.error.message)
         console.log('oops', error.error)
       }
     
     )
     this.selectedFiles = undefined
   }
  //--------------------------------------------------------------------------------------



  medicalRevealDataCollapsed(event: any): void {
  }
  medicalRevealDataExpanded(event: any): void {
   this.fillCommittees();
  }
  toggleMedicalRevealDataCollapse(): void {
    this.isMedicalRevealDataCollapsed = !this.isMedicalRevealDataCollapsed;
    this.iconMedicalRevealDataCollapse = this.isMedicalRevealDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  requestDataCollapsed(event: any): void {
  }
  requestDataExpanded(event: any): void {
  }
  toggleRequestDataCollapse(): void {
    this.isRequestDataCollapsed = !this.isRequestDataCollapsed;
    this.iconRequestDataCollapse = this.isRequestDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }


  fileUploadDataCollapsed(event: any): void {
  }
  fileUploadDataExpanded(event: any): void {
    this.showFiles(true);
  }
  toggleFileUploadDataCollapse(): void {
    this.isFileUploadDataCollapsed = !this.isFileUploadDataCollapsed;
    this.iconFileUploadDataCollapse = this.isFileUploadDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }

  onEyeCommitteeChanged(committeeId){
    
    for(var x=0; x< this.eyeCommittees.length;x++){
      if(this.eyeCommittees[x].id == committeeId){
        this.eyeCommittee = this.eyeCommittees[x];
      }
    }
  }
  onBonesCommitteeChanged(committeeId){
    for(var x=0; x< this.bonesCommittees.length;x++){
      if(this.bonesCommittees[x].id == committeeId){
        this.bonesCommittee = this.bonesCommittees[x];
      }
    }
  }
}
