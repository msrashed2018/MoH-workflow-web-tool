import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RequestTypeService } from '../../../../services/administration/request-type.service';

@Component({
  selector: 'app-request-type-view-edit',
  templateUrl: './request-type-view-edit.component.html',
  styleUrls: ['./request-type-view-edit.component.scss']
})
export class RequestTypeViewEditComponent implements OnInit {

  constructor(      private requestTypeService:RequestTypeService,
    private router : Router,private route:ActivatedRoute
  ) { }
  requestId;
  componentMode;
  requestModel={};
  ngOnInit() {
    this.route.params.forEach((urlParams) => {
      this.requestId= urlParams['id'];
      this.componentMode=urlParams['componentMode'];
      this.displayTypeDetails();
      console.log(this.requestId,this.componentMode)
    });
  }
  displayTypeDetails(){
    this.requestTypeService.retrieveRequestType(this.requestId).subscribe(
      response => {
        console.log(response);
        this.requestModel = response as any;
      }
    )
  }



}
