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

  constructor(private route:ActivatedRoute, private formBuilder: FormBuilder, private committeeService:CommitteeService, private disabilityService:DisabilityService, private equipmentService: EquipmentService, private eyeMeasureService: EyeMeasureService, private eyeRevealSettingService: EyeRevealSettingService, private customService: CustomService, private requestService: RequestService, private requestTypeService: RequestTypeService, private requestStatusService: RequestStatusService, private trafficManagementService: TrafficManagementService) { }

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


  print(): void {
    let printContents, popupWin, name = "" , address  =  ""
    , nationalId = 0, occupation = "", birthDate = "", mobileNumber = ""
    , governate = "", custom = "", bonesCommittee=" لم يتم تحديد اللجنة" ;

    if(this.citizen.name != null){
      name = this.citizen.name;
    }
    if(this.citizen.address != null){
      address = this.citizen.address
    }
    if(this.citizen.nationalId != null){
      nationalId = this.citizen.nationalId;
    }
    if(this.citizen.occupation != null){
      occupation = this.citizen.occupation.name
    }
    if(this.citizen.birthDate != null){
      birthDate = this.citizen.birthDate
    }
    if(this.citizen.mobileNumber != null){
      mobileNumber = this.citizen.mobileNumber
    }

    if(this.citizen.governate != null){
      governate = this.citizen.governate.name
    }

    if(this.request.custom != null){
      custom = this.request.custom.name;
    }

    if(this.request.bonesCommittee != null){
      bonesCommittee = this.request.bonesCommittee.date;
    }



    printContents =`
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ar" dir="rtl" lang="ar">

<head>
    <title>الكشف الطبي</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <style type="text/css">
    p {margin: 0; padding: 0;font-size:11px;}
        table {
            width: 100%;
        }
        
        /* table,
        th,
        td {
            border: 1px solid black; border-collapse: collapse;
        } */
        
        th {
            text-align: center;
        }
        
        td {
            padding: 0px;
        }
    </style>

</head>

<body  >

    <div class="container">
        <table>
            <tr>
                <td>
                    <p style="text-align: center"><b> وزارة الصحة والسكان</b></p>
                    <p style="text-align: center"><b>  الإدارة العامة للمجالس الطبية</b></p>
                    <p style="text-align: center"><b>  المتخصصة</b></p>
                    <p style="text-align: center"><b> السيارات المجهزة</b></p>
                </td>
                <td><img width="80" height="80" src="../../../../../assets/img/brand/logo_2.png" alt="background image"/></td>
                <td><h2>نتيجة الكشف  الطبي</h2></td>
                <td><img width="80" height="100" src="../../../img/brand/logo_2.png" alt="صورة شخصية"/></td>
            </tr>
        </table>
        <p>  •   على قرار وزير المالية رقم 861 لسنة 2005 </p>
        <p>  •   على كتاب رئيس مصلحة الجمارك رقم 1151 والمؤرخ في 9/11/2005</p>
        <p>  •  لجنة السيارات المجهزة والمشكلة بقرار وزير الصحة رقم 431 لسنة 1978</p>

        <p style="text-align: center"><b>البيانات الشخصية لطالب السيارة المجهزة</b></p>
        <table style=" border-collapse:separate; border-spacing: 0 1px">
            <tr>
                <td  colspan="2">
                        <p>الرقم القومي : ${nationalId}</p>
                </td>
                <td colspan="2">
                        <p>تاريخ الميلاد : ${birthDate}</p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                        <p>الاسم : ${name}</p>
                </td>
                <td colspan="2">
                        <p>الوظيفة : ${occupation} </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                        <p>العنوان : ${address}</p>
                </td>
                <td><p>المحافظة: ${governate}</p></td>
                <td><p>التليفون : ${mobileNumber} </p></td>
            </tr>
            <tr>
                <td><p>مسلسل الإيداع :</p></td>
                <td><p>تاريخ الإيداع:</p></td>
                <td><p>رقم :</p></td>
                <td><p>الجمرك : ${custom}</p></td>
            </tr>
            <tr>
                    <td colspan="2">
                            <p>تاريخ اللجنة : ${bonesCommittee}</p>
                    </td>
                    <td colspan="2">
                            <p> المراجع :</p>
                    </td>
            </tr>

        </table>

    <table style="border: 1px solid black; border-collapse: collapse;">
        <col width="60%">
        <col width="40%">
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <th> <p>توصيف الإعاقة</p></th>
            <th><p>الحالة تستدعى سيارة مجهزة بـ</p></th>
        </tr>
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <td style="border: 1px solid black; border-collapse: collapse; text-align: right; padding: 10px;">
               <p>(&#160;&#160;&#160;) شلل بالطرفين السفليين مؤثر على القوة العضلية وحركة المفاصل</p>
                <p>(&#160;&#160;&#160;) بتر بالطرفين السفليين</p>
                <p>(&#160;&#160;&#160;) تيبس كامل بمفصل الركبة  اليسرى + اليمنى  يبعد الطرفين السفليين عن الدواسات</p>
                <p>(&#160;&#160;&#160;) سقوط بالقدمين</p>
                <p>(&#160;&#160;&#160;) خذل بالطرفين السفليين</p>
                <p>(&#160;&#160;&#160;) تشوه بالقدمين مؤثر</p>
            </td>
            <td style="border: 1px solid black; border-collapse: collapse; text-align: center; padding: 10px;"><p>دواسة البنزين والفرامل تدار باليد وفاصل الحركة يدار باليد أو اوتوماتيك </p></td>
        </tr>
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <td style="border: 1px solid black; border-collapse: collapse; text-align: right; padding: 10px;">
                <p>(&#160;&#160;&#160;) شلل بالطرف السفلي الأيمن مؤثر علي القوة العضلية وحركة المفاصل</p>
                <p>(&#160;&#160;&#160;) بتر بالطرف السفلي الأيمن مؤثر</p>
                <p>(&#160;&#160;&#160;) ضعف بعضلات الطرف السفلي الأيمن مؤثر علي القوة العضلية وحركة المفاصل</p>
                <p>(&#160;&#160;&#160;) سقوط بالقدم اليمني مؤثر</p>
                <p>(&#160;&#160;&#160;) بتر بالقدم اليمني مؤثر</p>
                <p>(&#160;&#160;&#160;) تشوه بالقدم اليمني مؤثر</p>
            </td>
            <td style="border: 1px solid black; border-collapse: collapse; text-align: center;"><p>دواسة البنزين والفرامل تدار باليد وفاصل الحركة يدار باليد أو اوتوماتيك</p></td>
        </tr >
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <td style="border: 1px solid black; border-collapse: collapse; text-align: right; padding: 10px;">
                <p>(&#160;&#160;&#160;) شلل بالطرف السفلي الأيسر مؤثر علي القوة العضلية وحركة المفاصل</p>
                <p>(&#160;&#160;&#160;) بتر بالطرف السفلي الأيسر مؤثر</p>
                <p>(&#160;&#160;&#160;) ضعف بعضلات الطرف السفلي الأيسر مؤثر علي القوة العضلية وحركة المفاصل</p>
                <p>(&#160;&#160;&#160;) سقوط بالقدم اليسرى مؤثر</p>
                <p>(&#160;&#160;&#160;) بتر بالقدم اليسرى مؤثر</p>
                <p>(&#160;&#160;&#160;) تشوه بالقدم اليسرى مؤثر</p>
            </td>
            <td style="border: 1px solid black; border-collapse: collapse; text-align: center;"><p>دواسة فاصل الحركة تدار باليد أو اوتوماتيك  </p></td>
        </tr>
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <td style=" border: 1px solid black; border-collapse: collapse;text-align: right; padding: 10px;">
                <p>(&#160;&#160;&#160;) بتر بالطرف العلوي الأيمن ويرتدي طرف صناعي وظيفي يعمل</p>
                <p>(&#160;&#160;&#160;) شلل اربسي بالطرف العلوي الأيمن وحركة المفاصل في وضع وظيفي والقبضة مقبوله</p>
            </td>
            <td style="text-align: center;"><p> مفاتيح التشغيل والاناره بالجهة اليسرى بالإضافة إلي عجله قيادة مائية مزودة ببكره ومنيم بالكف الصناعي + ناقل سرعة اوتوماتيك </p></td>

        </tr>
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <td style="border: 1px solid black; border-collapse: collapse; text-align: right; padding: 10px;">
                <p>(&#160;&#160;&#160;) بتر بالطرف العلوي الأيسر ويرتدي طرف صناعي وظيفي يعمل</p>
                <p>(&#160;&#160;&#160;) شلل اربسي بالطرف العلوي الأيسر وحركة المفاصل في وضع وظيفي والقبضة مقبوله</p>
            </td>
            <td style="border: 1px solid black; border-collapse: collapse; text-align: center;"><p>  مفاتيح التشغيل والاناره بالجهة اليمني بالإضافة إلي عجله قيادة مائية مزودة ببكره ومنيم بالكف الصناعي + ناقل سرعة اوتوماتيك </p></td>
        </tr>
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <td style="border: 1px solid black; border-collapse: collapse; text-align: right; padding: 10px;">
                <p>(&#160;&#160;&#160;) أخرى</p>
            </td>
            <td style="text-align: center;"><p> التجهيز </p></td>
        </tr>
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <td style="border: 1px solid black; border-collapse: collapse; text-align: right; padding: 10px;" colspan="2">
                <p>(&#160;&#160;&#160;) الحالة لا ينطبق عليها شروط الحصول على سيارة مجهزة </p>
                <p>(&#160;&#160;&#160;)&#160;&#160;&#160; • &#160;&#160; (خزل  / ضعف  / شلل أطفال)  غير مؤثر بالطرف السفلى &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;(لا تستدعى)</p>
                <p>(&#160;&#160;&#160;)&#160;&#160;&#160; • &#160;&#160;  مفصل صناعي بمفصل  ............................ غير مؤثر&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;(لا تستدعى)</p>
                <p>(&#160;&#160;&#160;)&#160;&#160;&#160; • &#160;&#160;  ضعف بالأطراف الأربعة&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;(لا تمكنه)</p>
                <p>(&#160;&#160;&#160;)&#160;&#160;&#160; • &#160;&#160;  شلل دماغي&#160;&#160; • &#160;&#160;	شلل رباعي&#160;&#160; • &#160;&#160;	شلل نصفى طولي (أيمن / أيسر)	&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;(لا تمكنه)</p>
                <p>(&#160;&#160;&#160;)&#160;&#160;&#160; • &#160;&#160;  أخرى ..........................................................................................................&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;(  لا تمكنه/ لا تستدعى)</p>
            </td> 
        </tr>
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <td style="border: 1px solid black; border-collapse: collapse; text-align: right; padding: 10px;" colspan="2">
                <p>(&#160;&#160;&#160;) يعاد مناظرته بعد استقرار الحالة &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;(&#160;&#160;&#160;) يعاد مناظرته بعد إحضار طرف وظيفي يعمل</p>
                <p>(&#160;&#160;&#160;) يعاد مناظرته بعد التدريب على الجهاز &#160;&#160;&#160;&#160;&#160;(&#160;&#160;&#160;) يعاد مناظرته بعد إحضار رسم عضلات على ............................</p>
            </td>
        </tr>
        <tr style="border: 1px solid black; border-collapse: collapse;">
            <td style="border: 1px solid black; border-collapse: collapse; text-align: right; padding: 10px;" colspan="2">
                <p><b>السادة أعضاء اللجنة : -</b></p>
                <p>مقرر اللجنة د / ............................................. &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; أ د / ..............................................</p>
                <p>أ د / .................................&#160;&#160;&#160;&#160;&#160;&#160; مهندس / ............................................................  &#160;&#160;&#160; ضابط /  .........................................</p>
                <br><p>....................................</p>
                <p>&#160;&#160;&#160;&#160;&#160;&#160;مراجعة </p>
                <p style="text-align: left"><b>مدير عام&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</b></p>
                <p style="text-align: left"><b>الإدارة العامة للمجالس الطبية المتخصصة</b></p>
            </td>
        </tr>
    </table>
</div>
</body>

</html>
    `


    popupWin = window.open('', '_blank', 'top=0,left=0,height=100%,width=auto');
    // // window.print()
    popupWin.document.open();
    popupWin.document.write(printContents);
    popupWin.document.close();
    popupWin.print();
  }
}
