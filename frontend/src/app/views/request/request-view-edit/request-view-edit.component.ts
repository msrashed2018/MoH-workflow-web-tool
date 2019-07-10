import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { HttpResponse, HttpEventType } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { RequestTypeService } from '../../../services/administration/request-type.service';
import { RequestStatusService } from '../../../services/administration/request-status.service';
import { TrafficManagementService } from '../../../services/administration/traffic-management.service';
import { CustomService } from '../../../services/administration/custom.service';
import { EyeRevealSettingService } from '../../../services/administration/eye-reveal-setting.service';
import { EyeMeasureService } from '../../../services/administration/eye-measure.service';
import { EquipmentService } from '../../../services/administration/equipment.service';
import { DisabilityService } from '../../../services/administration/disability.service';
import { CommitteeService } from '../../../services/administration/committee.service';
import { Request } from '../../../model/request.model';
import { API_URL } from '../../../app.constants';
import { RequestPayment } from '../../../model/request-payment.model';
import { EyeReveal } from '../../../model/eye-reveal.model';
import { BonesReveal } from '../../../model/bones-reveal.model';
import { ActivatedRoute } from '@angular/router';
import { RequestStatus } from '../../../model/request-status.model';
import { DocumentCategory } from '../../../model/document-category.enum';
import { DocumentTypeService } from '../../../services/administration/document-type.service';
import { RequestDocument } from '../../../model/request-document.model';
import { DocumentType } from '../../../model/document-type.model';
@Component({
  selector: 'app-request-view-edit',
  templateUrl: './request-view-edit.component.html',
  styleUrls: ['./request-view-edit.component.scss']
})
export class RequestViewEditComponent implements OnInit {
  

  constructor(private documentTypeService: DocumentTypeService, private route:ActivatedRoute, private formBuilder: FormBuilder, private committeeService:CommitteeService, private disabilityService:DisabilityService, private equipmentService: EquipmentService, private eyeMeasureService: EyeMeasureService, private eyeRevealSettingService: EyeRevealSettingService, private customService: CustomService, private requestService: RequestService, private requestTypeService: RequestTypeService, private requestStatusService: RequestStatusService, private trafficManagementService: TrafficManagementService) { }


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
  //---------------------------------------------------------------------------------------------------------x
//models fields--------------------------------------------------------------------------------------------
citizenId : number = 0;
requestId : number = 0;
componentMode: string
request : Request = new Request();
selectedRequestStatusId : number = 0;
citizen = {};
payment : RequestPayment = new RequestPayment();
paymentDoneCheck : boolean = false;
requestStatuses :RequestStatus[] = [];
documents = [{}];

eyeReveal  = new EyeReveal();
distinguishCheck : boolean = false;
glassesCheck: boolean = false;
squintCheck: boolean = false;

bonesReveal = new BonesReveal();
errorMessage : boolean = false;
requestDataErrorMessage : string = '';
requestDataSuccessMessage : string = '';

paymentErrorMessage : string = '';
paymentSuccessMessage : string = '';
bonesRevealErrorMessage : string = '';
bonesRevealSuccessMessage : string = '';
eyeRevealErrorMessage : string = '';
eyeRevealSuccessMessage : string = '';
successMessage : boolean = false;
message: string = "";
//---------------------------------------------------------------------------------------------------------

//file upload fields---------------------------------------------------------------------------------------
showFile = false
documentTypes: DocumentType[];
requestDocuments: RequestDocument[];
fileUploadErrorMessage : string;
uploading = false;
@Input() fileUpload: string;
selectedFiles: FileList
currentFileUpload: File
progress: { percentage: number } = { percentage: 0 }
selectedDocumentTypeId: number = 0;
//---------------------------------------------------------------------------------------------------------

  ngOnInit() {
    this.route.params.forEach((urlParams) => {
      this.requestId= urlParams['id'];
      this.componentMode=urlParams['componentMode'];
      // this.requestId= 1;
      this.displayRequestDataDetails();
    });
  }
  displayRequestDataDetails(){
    this.requestService.retrieveRequest(this.requestId).subscribe(
    result => {
      this.request = result as Request;
      this.citizen = this.request.citizen;
      
      if(this.request.requestStatus != null){
        this.selectedRequestStatusId = this.request.requestStatus.id;
      }
      
      this.fillRequestStatuses();
    },
    error => {
      this.errorMessage = true;
      this.message = error.error.message;
      console.log('oops', error);
    });

  }
  fillRequestStatuses(){
    this.requestStatusService.retrieveAllRequestStatus(0,100).subscribe(
      result => {
        this.requestStatuses = result['content'];
      },
      error => {
        console.log('oops', error);
      });
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
  paymentDataCollapsed(event: any): void {
    
  }
  paymentDataExpanded(event: any): void {
    this.requestService.retreiveRequestPayment(this.requestId).subscribe(
      result =>{
        this.payment = result as RequestPayment;
        if(this.payment.paymentDone == '1'){
          this.paymentDoneCheck = true;
        }else{
          this.paymentDoneCheck = false;
        }

      },
      error=>{
        this.paymentSuccessMessage = "";
        this.paymentErrorMessage = error.error.message;
      }

    )
  }
  togglePaymentDataCollapse(): void {
    this.isPaymentDataCollapsed = !this.isPaymentDataCollapsed;
    this.iconPaymentDataCollapse = this.isPaymentDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }

  eyeRevealDataCollapsed(event: any): void {
  }
  eyeRevealDataExpanded(event: any): void {
    this.requestService.retreiveRequestEyeReveal(this.requestId).subscribe(
      result =>{
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
      },
      error=>{
        this.eyeRevealSuccessMessage = "";
        this.eyeRevealErrorMessage = error.error.message;
      }
    )
  }
  toggleEyeRevealDataCollapse(): void {
    this.isEyeRevealDataCollapsed = !this.isEyeRevealDataCollapsed;
    this.iconEyeRevealDataCollapse = this.isEyeRevealDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }

  bonesRevealDataCollapsed(event: any): void {
  }
  bonesRevealDataExpanded(event: any): void {
    this.requestService.retreiveRequestBonesReveal(this.requestId).subscribe(
      result =>{
        this.bonesReveal = result as BonesReveal;
        // console.log(JSON.stringify(this.bonesReveal))
      },
      error=>{
        this.bonesRevealSuccessMessage = "";
        this.bonesRevealErrorMessage = error.error.message;
      }
    )


  }
  toggleBonesRevealDataCollapse(): void {
    this.isBonesRevealDataCollapsed = !this.isBonesRevealDataCollapsed;
    this.iconBonesRevealDataCollapse = this.isBonesRevealDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }

  fileUploadDataCollapsed(event: any): void {
  }
  fileUploadDataExpanded(event: any): void {
    this.fillDocumentTypes();
    this.showFiles(true);
  }
  toggleFileUploadDataCollapse(): void {
    this.isFileUploadDataCollapsed = !this.isFileUploadDataCollapsed;
    this.iconFileUploadDataCollapse = this.isFileUploadDataCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }

  onSaveRequestData(){
    if(this.selectedRequestStatusId !=0){
      this.request.requestStatus =  this.requestStatuses.find((r)=> r.id==this.selectedRequestStatusId)
    }
    
    // console.log(JSON.stringify(this.request))
    this.requestService.updateRequestStatus(this.request.citizen.id,this.request.id, this.request.requestStatus).subscribe(
      result => {
        this.requestDataErrorMessage = "";
        this.requestDataSuccessMessage = "تم حفظ الطلب بنجاح"
      },
      error => {
        console.log('oops', error);
        this.requestDataSuccessMessage ="";
        this.requestDataErrorMessage = error.error.message;
       }
    )
  }

 // file upload methods--------------------------------------------------------------------------------------
 showFiles(enable: boolean) {
  this.showFile = enable
  // this.getDocumentsTypes();
  if (enable) {

    this.requestService.getRequestDocumentsByCategory(this.request.id, DocumentCategory.ALL).subscribe(
      result => {
        this.requestDocuments = result as RequestDocument[];
        // this.requestDocuments .forEach(element => {
        //   this.fileUploads.set(element,`${API_URL}/requests/${this.request.id}/documents/${element}`);
        //   // console.log("key: "+ element + "   value :"+`${API_URL}/requests/${this.request.id}/documents/${element}`);
        // });
      },
      error => {
        console.log('oops', error.error)
      }
    )
  }
}

fillDocumentTypes() {
  this.documentTypeService.retrieveDocumentTypesByCategory(DocumentCategory.ALL).subscribe(
    result => {
      this.documentTypes = result as DocumentType[];
    },
    error => {
      console.log('oops', error.error)
    }
  )
}
selectFile(event) {
  this.selectedFiles = event.target.files;
  this.uploading = false;
}
getFile(requestDocumentName) {
  this.requestService.getRequestDocument(this.request.id, requestDocumentName);
}
deleteFile(requestDocumentName) {
  this.requestService.deleteRequestDocument(this.request.id, requestDocumentName).subscribe(
    result => {
      this.fileUploadErrorMessage = "";
      this.showFiles(true);
    },
    error => {
      this.fileUploadErrorMessage = error.error.message;
    }


  );
}
upload() {

  if (this.selectedDocumentTypeId != 0) {
    this.uploading = true;
    this.requestService.uploadRequestDocument(this.requestId, this.selectedDocumentTypeId, this.selectedFiles).subscribe(
      result => {
        this.fileUploadErrorMessage = "";
        if (result.type === HttpEventType.UploadProgress) {
          this.progress.percentage = Math.round(100 * result.loaded / result.total);

        } else if (result instanceof HttpResponse) {
          console.log('File is completely uploaded!');

          this.showFiles(true);
        }

      },
      error => {
        this.fileUploadErrorMessage = error.error;
        console.log('oops', error.error.message)
        console.log('oops', error.error)
      }

    )
    this.selectedFiles = undefined
  } else {
    this.fileUploadErrorMessage = "اختر نوع الملف اولا";
  }

}
  
}
