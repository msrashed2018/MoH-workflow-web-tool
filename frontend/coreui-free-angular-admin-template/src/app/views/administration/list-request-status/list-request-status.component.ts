import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RequestStatus } from '../../../model/request-status.model';
import { RequestStatusService } from '../../../services/administration/request-status.service';



@Component({
  selector: 'app-list-request-status',
  templateUrl: './list-request-status.component.html',
  styleUrls: ['./list-request-status.component.scss']
})
export class ListRequestStatusComponent implements OnInit {
  requestStatus: RequestStatus[]
  message: string

  constructor(
    private requestStatusService:RequestStatusService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshRequestStatus();
  }
  refreshRequestStatus(){
    this.requestStatusService.retrieveAllRequestStatus().subscribe(
      response => {
        console.log(response);
        this.requestStatus = response;
      }
    )
  }

  deleteRequestStatus(name,id) {
    console.log(`delete requestStatus ${id}` )
    this.requestStatusService.deleteRequestStatus(id).subscribe (
      response => {
        console.log(response);
        // this.message =  `Delete of RequestStatus ${name} Successful!`;
        this.refreshRequestStatus();
      }
    )
  }

  updateRequestStatus(id) {
    console.log(`update ${id}`)
    this.router.navigate(['request-status',id])
  }

  addRequestStatus() {
    this.router.navigate(['request-status',-1])
  }
}
