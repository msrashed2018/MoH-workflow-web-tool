import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GovernateService } from '../../../services/administration/governate.service';
import { Governate } from '../../../model/governate.model';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';
// import { BrowserModule } from '@angular/platform-browser';


@Component({
  // selector: 'app-list-governates',
  templateUrl: './list-governates.component.html',
  styleUrls: ['./list-governates.component.scss']
})
export class ListGovernatesComponent implements OnInit {
  governates:  Governate[]
  pages: number = 5;
  message: string
  currentPage: number = 5;

  constructor(
    private governateService:GovernateService,
    private router : Router,
    private confirmationModalService: ConfirmModalService
  ) { 

  }

  ngOnInit() {
    this.pages=5;
    this.refreshGovernates();
  }

  refreshGovernates(){
    this.governateService.retrieveAllGovernates().subscribe(
      response => {
        this.governates = response['content'];
        // this.pages = response['totalPages'];
      }
    )
  }
  pageChanged(event: any): void {
    this.currentPage = event.page;
    console.log('Page changed to: ' + event.page);
    console.log('Number items per page: ' + event.itemsPerPage);
  }
  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المحافظة ')
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
