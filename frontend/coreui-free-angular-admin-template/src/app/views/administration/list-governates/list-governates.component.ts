import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GovernateService } from '../../../services/administration/governate.service';
import { Governate } from '../../../model/governate.model';
import { ConfirmationModalService } from '../confirmation-modal/confirmation-modal.service';
// import { BrowserModule } from '@angular/platform-browser';


@Component({
  // selector: 'app-list-governates',
  templateUrl: './list-governates.component.html',
  styleUrls: ['./list-governates.component.scss']
})
export class ListGovernatesComponent implements OnInit {
  governates: Governate[]
  message: string

  constructor(
    private governateService:GovernateService,
    private router : Router,
    private confirmationModalService: ConfirmationModalService
  ) { 

  }

  ngOnInit() {
    this.refreshGovernates();
  }

  refreshGovernates(){
    this.governateService.retrieveAllGovernates().subscribe(
      response => {
        this.governates = response;
      }
    )
  }
  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المدينة ')
    .then((confirmed) => {
      if(confirmed){
        this.governateService.deleteGovernate(id).subscribe (
          response => {
            this.message = ` تم حذف المحافظه بنجاح `
            this.refreshGovernates();
          },
          error => {
            this.message = 'لا يمكن حذف المحافظة'
          }
        )
      }
    })
  }


  onEdit(id) {
    this.router.navigate(['administration/governates',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/governate-data'])
  }
}
