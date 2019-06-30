import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuditService } from '../../../services/administration/audit.service';
import { Audit } from '../../../model/audit.model';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';
import { PAGINATION_PAGE_SIZE } from '../../../app.constants';

@Component({
  selector: 'app-audit-list',
  templateUrl: './audit-list.component.html',
  styleUrls: ['./audit-list.component.scss']
})
export class AuditListComponent implements OnInit {
  audits: Audit[]
  message: string

  constructor(
    private auditService:AuditService,
    private router : Router, 
    private confirmationModalService: ConfirmModalService
  ) { 

  }
  page: number = 0;
  pages: Array<number>;
  items: number = 0;
  setPage(i,event: any): void {
    // this.currentPage = event.page;
    event.preventDefault();
    this.page = i ;
    this.items = i*PAGINATION_PAGE_SIZE;
    this.refreshData();
  }
  nextPage(event: any): void {
    event.preventDefault();
    if((this.page+1) < this.pages.length){
      this.page = this.page+1
      this.items = (this.page)*PAGINATION_PAGE_SIZE;
      this.refreshData();
    }
  }
  prevPage(event: any): void {
    event.preventDefault();

    if((this.page-1) >= 0){
      this.page =this.page -1;
      this.items = (this.page)*PAGINATION_PAGE_SIZE;
      this.refreshData();
    }
  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.auditService.retrieveAllAudits(this.page,PAGINATION_PAGE_SIZE).subscribe(
      response => {
        this.audits = response['content'];
        this.pages = new Array(response['totalPages']);
      },
      error =>{
        console.log('oops',error)
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف هذا الحدث ')
    .then((confirmed) => {
      if(confirmed){
        this.auditService.deleteAudit(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['administration/audits',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/audit-data'])
  }
}

