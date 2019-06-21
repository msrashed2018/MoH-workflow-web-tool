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
import { BonesReveal } from '../../../../model/bones-reveal.model';
import { EyeReveal } from '../../../../model/eye-reveal.model';
import { Disability } from '../../../../model/disability.model';
import { Equipment } from '../../../../model/equipment.model';
import { EyeMeasure } from '../../../../model/eye-measure.model';
@Component({
  selector: 'app-reveal-data',
  templateUrl: './reveal-data.component.html',
  styleUrls: ['./reveal-data.component.scss']
})
export class RevealDataComponent implements OnInit {
  //cards collapse fields------------------------------------------------------------------------------------
  isRequestDataCollapsed: boolean = false;
  isPaymentDataCollapsed: boolean = true;
  isEyeRevealDataCollapsed: boolean = true;
  isBonesRevealDataCollapsed: boolean = true;
  isFileUploadDataCollapsed: boolean = true;
  iconRequestDataCollapse: string = 'icon-arrow-up';
  iconPaymentDataCollapse: string = 'icon-arrow-down';
  iconEyeRevealDataCollapse: string = 'icon-arrow-down';
  iconBonesRevealDataCollapse: string = 'icon-arrow-down';
  iconFileUploadDataCollapse: string = 'icon-arrow-down';
  //---------------------------------------------------------------------------------------------------------
  
  //models fields--------------------------------------------------------------------------------------------
  citizenId : number = 0;
  requestId : number = 0;
  componentMode: string
  request : Request = new Request();
  requestType = {};
  equipment : string ="";
  eyeCommittee = {};
  bonesCommittee = {};
  selectedRequestTypeId : number = 0;
  selectedCustomId : number = 0;
  selectedTrafficManagementId: number = 0;
  selectedRequestStatusId : number = 0;
  citizen = {};

  
  disabilities : Disability[]= [];
  // equipments = [{}];
  public documents = [{}];
  customs = [{}];


  measures = [{}];
  eyeReveal  = new EyeReveal();
  distinguishCheck : boolean = false;
  glassesCheck: boolean = false;
  squintCheck: boolean = false;
  // selectedEyeCommitteeId: number = 0;
  selectedLeftEyeMeasureId: number = 0;
  selectedRightEyeMeasureId: number = 0;

  bonesReveal = new BonesReveal();
  // selectedBonesCommitteeId: number = 0;
  selectedDisabilityTypeName : string = "";
  // selectedEquipmentTypeId: number = 0;


  errorMessage : boolean = false;
  requestDataErrorMessage : string = '';
  requestDataSuccessMessage : string = '';
  
  bonesRevealErrorMessage : string = '';
  bonesRevealSuccessMessage : string = '';
  eyeRevealErrorMessage : string = '';
  eyeRevealSuccessMessage : string = '';
  successMessage : boolean = false;
  message: string = "";
  //---------------------------------------------------------------------------------------------------------
  
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
      this.eyeCommittee = this.request.eyeCommittee;
      this.bonesCommittee = this.request.bonesCommittee;
    },
    error => {
      this.errorMessage = true;
      this.message = error.error.message;
      console.log('oops', error);
    });

  }
  
  onSaveEyeReveal(){
  
if(this.eyeReveal.id !=0){

    if(this.distinguishCheck){
      this.eyeReveal.distinguishColor = '1';
    }else{
      this.eyeReveal.distinguishColor= '0';
    }
    if(this.glassesCheck){
      this.eyeReveal.useGlasses = '1';
    }else{
      this.eyeReveal.useGlasses= '0';
    }

    if(this.squintCheck){
      this.eyeReveal.squint = '1';
    }else{
      this.eyeReveal.squint= '0';
    }
    // if(this.selectedEyeCommitteeId !=0){
    //   let committee: Committee = new Committee;
    //   committee.id= this.selectedEyeCommitteeId;
    //   // this.eyeReveal.committee = committee;
    // }
    if(this.selectedLeftEyeMeasureId != 0){
      let eyeMeasure: EyeMeasure = new EyeMeasure;
      eyeMeasure.id= this.selectedLeftEyeMeasureId;
      
      this.eyeReveal.leftEye = eyeMeasure;
    }
    if(this.selectedRightEyeMeasureId !=0){
      let eyeMeasure: EyeMeasure = new EyeMeasure;
      eyeMeasure.id= this.selectedRightEyeMeasureId;
      this.eyeReveal.rightEye = eyeMeasure;
    }
    this.requestService.updateRequestEyeReveal(this.requestId, this.eyeReveal.id, this.eyeReveal).subscribe(
      result => {
        this.eyeReveal = result as EyeReveal;
        this.eyeRevealErrorMessage = '';
        this.eyeRevealSuccessMessage = "تم حفظ بيانات كشف الرمد بنجاح"
      },
      error => {
        console.log('oops', error);
        this.eyeRevealErrorMessage = error.error.message;
        this.eyeRevealSuccessMessage = '';
      }
    )
  }else{
    this.eyeRevealErrorMessage = 'عفوا لم يتم كشف رمد لهذا المواطن';
    this.eyeRevealSuccessMessage = '';
  }
  }

  onSaveBonesReveal(){
    if(this.bonesReveal.id != 0){
        // if(this.selectedBonesCommitteeId !=0){
        //   let committee: Committee = new Committee;
        //   committee.id= this.selectedBonesCommitteeId;
        //   // this.bonesReveal.committee = committee;
        // }

        this.bonesReveal.disability = this.disabilities.find((d)=> d.name==this.selectedDisabilityTypeName);
        if(this.bonesReveal.disability == null){
          this.bonesRevealErrorMessage = ".نوع الاعاقة غير صحيح .. من فضلك اختر النوع من القائمة";
        }else{
          this.bonesRevealErrorMessage = "";
          this.requestService.updateRequestBonesReveal(this.request.id, this.bonesReveal.id, this.bonesReveal).subscribe(
            result => {
              this.bonesReveal = result as BonesReveal;
              this.bonesRevealSuccessMessage =  "تم حفظ بيانات كشف العظام بنجاح";
              this.bonesRevealErrorMessage = "";
            },
            error => {
              console.log('oops', error);
              this.bonesRevealSuccessMessage = "";
              this.bonesRevealErrorMessage = error.error.message;
            }
          )
        }


        
            
    }else{
      this.bonesRevealSuccessMessage = "";
      this.bonesRevealErrorMessage = "عفوا لم يتم كشف عظام لهذا المواطن";
    }
  }
  onSaveRequestData(){
}


  onClose(){
  }

  onDelete(){
  }
  onDistinguishChecked(event){
    this.distinguishCheck = event.target.checked;
  }
  onSquintChecked(event){
    this.squintCheck = event.target.checked;
  }
  onGlassesChecked(event){
    this.glassesCheck = event.target.checked;
  }



  //cards collapse methods-----------------------------------------------------------------------------------
  requestDataCollapsed(event: any): void {
  }
  requestDataExpanded(event: any): void {
  }
  toggleRequestDataCollapse(): void {
    this.isRequestDataCollapsed = !this.isRequestDataCollapsed;
    this.iconRequestDataCollapse = this.isRequestDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  

  eyeRevealDataCollapsed(event: any): void {
  }
  eyeRevealDataExpanded(event: any): void {
    this.requestService.retreiveRequestEyeReveal(this.requestId).subscribe(
        result =>{
          if(result != null){
            this.eyeReveal = result as EyeReveal;
            console.log(JSON.stringify(this.eyeReveal))
            
            if(this.eyeReveal.distinguishColor == '1'){
              this.distinguishCheck =true;
            }else{
              this.distinguishCheck =false;
            }
            if( this.eyeReveal.useGlasses == '1'){
              this.glassesCheck = true;
            }else{
              this.glassesCheck = false;
            }
            if( this.eyeReveal.squint == "1"){
              this.squintCheck = true;
            }else{
              this.squintCheck = false;
            }
        
            // if(this.eyeReveal.committee != null){
            //   this.selectedEyeCommitteeId = this.eyeReveal.committee.id
            // }
        
            if(this.eyeReveal.leftEye != null){
              this.selectedLeftEyeMeasureId = this.eyeReveal.leftEye.id
            }
            if(this.eyeReveal.rightEye != null){
              this.selectedRightEyeMeasureId = this.eyeReveal.rightEye.id
            }
          }else{
            this.eyeRevealSuccessMessage = "";
            this.eyeRevealErrorMessage = "عفوا لم يتم كشف رمد لهذا المواطن";
          }
      },
      error=>{
        this.eyeRevealSuccessMessage = "";
        this.eyeRevealErrorMessage = error.error.message;
      }
    )

    this.fillMeasures();

  }
  
  toggleEyeRevealDataCollapse(): void {
    this.isEyeRevealDataCollapsed = !this.isEyeRevealDataCollapsed;
    this.iconEyeRevealDataCollapse = this.isEyeRevealDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }

  bonesRevealDataCollapsed(event: any): void {
  }
  bonesRevealDataExpanded(event: any): void {
    // this.fillEquipments();
    this.fillDisabilities();

    this.requestService.retreiveRequestBonesReveal(this.requestId).subscribe(
      result =>{
          if(result != null){
              this.bonesReveal = result as BonesReveal;
              // console.log(JSON.stringify(this.bonesReveal))
              if(this.bonesReveal.disability != null){
                this.selectedDisabilityTypeName = this.bonesReveal.disability.name
              }

              // if(this.bonesReveal.equipment != null){
              //   this.selectedEquipmentTypeId = this.bonesReveal.equipment.id
              // }
          }else{
            this.bonesRevealSuccessMessage = "";
            this.bonesRevealErrorMessage = "عفوا لم يتم كشف عظام لهذا المواطن";
          }
      },
      error=>{
        this.bonesRevealSuccessMessage = "";
        this.bonesRevealErrorMessage = error.error.message;
      }
    )
  }
  onDisabilityChanged(value){
    this.bonesReveal.disability = this.disabilities.find((d)=> d.name==value)
    if( this.bonesReveal.disability == null){
      this.bonesRevealErrorMessage = ".نوع الاعاقة غير صحيح .. من فضلك اختر النوع من القائمة";
    }else{
      this.bonesRevealErrorMessage = "";
      this.equipment = this.bonesReveal.disability.equipment.name;
      if(this.bonesReveal.disability.accepted == '0'){
        this.bonesReveal.result = 'مرفوض'
      }else if(this.bonesReveal.disability.accepted == '1'){
        this.bonesReveal.result = 'مقبول'
      }else if(this.bonesReveal.disability.accepted == '2'){
        this.bonesReveal.result = 'اعادة مناظرة'
      }
    }
  }
  toggleBonesRevealDataCollapse(): void {
    this.isBonesRevealDataCollapsed = !this.isBonesRevealDataCollapsed;
    this.iconBonesRevealDataCollapse = this.isBonesRevealDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
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

// createNewRequest(){

//   this.requestService.createRequest(this.request).subscribe(
//     result => {
//       this.request = result as Request;
//       this.request.id= 1;
//     },
//     error => {
//       console.log('oops', error);
//     });
// }
  //---------------------------------------------------------------------------------------------------------

  fillMeasures(){
    this.eyeMeasureService.retrieveAllEyeMeasure(0,100).subscribe(
      result => {
        this.measures = result['content'];
      },
      error => {
        console.log('oops', error);
    });
  }
  // fillEquipments(){
  //   this.equipmentService.retrieveAllEquipments().subscribe(
  //     result => {
  //       this.equipments = result;
  //     },
  //     error => {
  //       console.log('oops', error);
  //   });
  // }
  fillDisabilities(){
    this.disabilityService.retrieveAllDisabilities(0,100).subscribe(
      result => {
        this.disabilities = result['content'];
      },
      error => {
        console.log('oops', error);
    });
  }


  // file upload methods--------------------------------------------------------------------------------------
  showFiles(enable: boolean) {
    this.showFile = enable
    if (enable) {
      this.requestService.getFiles(this.request.id,'MEDICAL').subscribe(
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
    this.requestService.pushFileToStorage(this.requestId,'MEDICAL',this.selectedFiles).subscribe(
      result => {
        // console.log(result);
        if (result.type === HttpEventType.UploadProgress) {
          this.progress.percentage = Math.round(100 * result.loaded / result.total);
          
        } else if (result instanceof HttpResponse) {
          this.fileUploadErrorMessage = "";
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

}
//----------------------------------------------------------------------------------------------------------


