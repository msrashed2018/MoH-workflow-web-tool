import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Custom } from '../../../model/custom.model';
import { CustomService } from '../../../services/administration/custom.service';
import { ConfirmationModalService } from '../confirmation-modal/confirmation-modal.service';



@Component({
  selector: 'app-list-customs',
  templateUrl: './list-customs.component.html',
  styleUrls: ['./list-customs.component.scss']
})
export class ListCustomsComponent implements OnInit {
  customs: Custom[]
  message: string

  constructor(
    private customService:CustomService,
    private router : Router, private confirmationModalService: ConfirmationModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.customService.retrieveAllCustoms().subscribe(
      response => {
        this.customs = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف الجمرك؟ ')
    .then((confirmed) => {
      if(confirmed){
        this.customService.deleteCustom(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
    
  
    
  }

  onEdit(id) {
    this.router.navigate(['administration/customs',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/custom-data'])
  }
}
