import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DocumentType } from '../../../model/document-type.model';
import { DocumentTypeService } from '../../../services/administration/document-type.service';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';
import { PAGINATION_PAGE_SIZE } from '../../../app.constants';
@Component({
  selector: 'app-list-document-types',
  templateUrl: './list-document-types.component.html',
  styleUrls: ['./list-document-types.component.scss']
})
export class ListDocumentTypesComponent implements OnInit {
  documentTypes: DocumentType[]
  message: string

  constructor(
    private documentTypeService:DocumentTypeService,
    private router : Router, private confirmationModalService: ConfirmModalService
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
    this.documentTypeService.retrieveAllDocumentTypes(this.page,PAGINATION_PAGE_SIZE).subscribe(
      response => {
        this.documentTypes = response['content'];
        this.pages = new Array(response['totalPages']);
      },
      error =>{
        console.log('oops',error)
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف نوع الملف ')
    .then((confirmed) => {
      if(confirmed){
        this.documentTypeService.deleteDocumentType(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
    
  
    
  }

  onEdit(id) {
    this.router.navigate(['administration/document-types',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/document-type-data'])
  }
}