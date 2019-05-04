import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RequestType } from '../../../model/request-type.model';


@Component({
  selector: 'app-list-request-types',
  templateUrl: './list-request-types.component.html',
  styleUrls: ['./list-request-types.component.scss']
})
export class ListRequestTypesComponent implements OnInit {
  requestTypes: RequestType[]
  message: string

  constructor(
    // private requestTypeService:RequestTypeService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshRequestTypes();
  }
  refreshRequestTypes(){
    // this.requestTypeService.retrieveAllRequestTypes().subscribe(
    //   response => {
    //     console.log(response);
    //     this.requestTypes = response;
    //   }
    // )
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
