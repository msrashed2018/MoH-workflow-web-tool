import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RequestStatus } from '../../../model/request-status.model';
import { RequestStatusService } from '../../../services/administration/request-status.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmationModalService } from '../confirmation-modal/confirmation-modal.service';



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
    private router : Router, private confirmationModalService: ConfirmationModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.requestStatusService.retrieveAllRequestStatus().subscribe(
      response => {
        this.requestStatus = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف حالة الطلب؟ ')
    .then((confirmed) => {
      if(confirmed){
      this.requestStatusService.deleteRequestStatus(id).subscribe (
        response => {
          this.refreshData();
        }
      )
    }
    })
  }
 
  public openConfirmationDialog() {
    }
  onEdit(id) {
    this.router.navigate(['administration/request-status',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/request-status-data'])
  }
}
