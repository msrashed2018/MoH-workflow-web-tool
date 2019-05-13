import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RequestTypeService } from '../../../../services/administration/request-type.service';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';

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
    private router : Router,
    private confirmationModalService: ConfirmModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }

  refreshData(){
    this.requestTypeService.retrieveAllRequestTypes().subscribe(
      response => {
        this.requestTypes = response as any;
      }
    )
  }

  onEdit(id){
    this.router.navigate(['administration/types', id ,{componentMode: "editMode"}])
  }
  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف حالة النوع ')
    .then((confirmed) => {
      if(confirmed){
      this.requestTypeService.deleteRequestType(id).subscribe (
        response => {
          this.refreshData();
        }
      )
    }
    })
  }

}
