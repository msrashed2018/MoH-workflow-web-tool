import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RequestTypeService } from '../../../../services/administration/request-type.service';

@Component({
  selector: 'app-request-types-list',
  templateUrl: './request-types-list.component.html',
  styleUrls: ['./request-types-list.component.scss']
})
export class RequestTypesListComponent implements OnInit {

  requestTypes=[];
  message: string

  constructor(
    private requestTypeService:RequestTypeService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.getAllRequests();
  }

  getAllRequests(){
    this.requestTypeService.retrieveAllRequestTypes().subscribe(
      response => {
        console.log(response);
        this.requestTypes = response as any;
      }
    )
  }

  deleteRequestType(name,id) {
    // console.log(`delete requestType ${id}` )
    // this.requestTypeService.deleteRequestType(id).subscribe (
    //   response => {
    //     console.log(response);
    //     this.message = ` تم حذف المحافظه بنجاح `
    //     // this.message =  `Delete of RequestType ${name} Successful!`;
    //     this.refreshRequestTypes();
    //   }
    // )
  }

  updateRequestType(id) {
    console.log(`update ${id}`)
    this.router.navigate(['request-types',id])
  }

  addRequestType() {
    this.router.navigate(['request-types',-1])
  }

}
