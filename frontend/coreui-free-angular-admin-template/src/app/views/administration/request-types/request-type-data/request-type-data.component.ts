import { Component, OnInit } from '@angular/core';
import { RequestTypeService } from '../../../../services/administration/request-type.service';
import { NgModel } from '@angular/forms';
import { Router } from '@angular/router';
import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import { ConfirmationModalComponent } from '../../../confirmation-modal/confirmation-modal.component';

@Component({
  selector: 'app-request-type-data',
  templateUrl: './request-type-data.component.html',
  styleUrls: ['./request-type-data.component.scss']
})
export class RequestTypeDataComponent implements OnInit {

  constructor(private router:Router,  private modalService: NgbModal,  private requestTypeService:RequestTypeService,
  ) { }
  componentViewMode='addMode';
  requestModel={};
  ngOnInit() {
  }
  open() {
    const modalRef = this.modalService.open(ConfirmationModalComponent);
    modalRef.componentInstance.name = 'World';
  }
  onSave(){
     if(this.componentViewMode=='addMode')
     {
          this.createRequestType();
     }
  }

  onCancel(){
    this.router.navigate(["/administration/request-types"])
  }

  createRequestType(){
    this.requestTypeService.createRequestType(this.requestModel).subscribe(
      response => {
        console.log(response);
        this.router.navigate(["/administration/request-types"])
      }
    )
  }
  




}
