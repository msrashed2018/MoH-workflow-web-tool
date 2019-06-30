import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DocumentType } from '../../../model/document-type.model';


@Component({
  selector: 'app-list-requiredDocuments',
  templateUrl: './list-required-documents.component.html',
  styleUrls: ['./list-required-documents.component.scss']
})
export class ListRequiredDocumentsComponent implements OnInit {
  // requiredDocuments: DocumentType[]
  // message: string

  constructor(
    // private requiredDocumentService:RequiredDocumentService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshRequiredDocuments();
  }
  refreshRequiredDocuments(){
    // this.requiredDocumentService.retrieveAllRequiredDocuments().subscribe(
    //   response => {
    //     console.log(response);
    //     this.requiredDocuments = response;
    //   }
    // )
  }

  deleteRequiredDocument(name,id) {
    // console.log(`delete requiredDocument ${id}` )
    // this.requiredDocumentService.deleteRequiredDocument(id).subscribe (
    //   response => {
    //     console.log(response);
    //     this.message = ` تم حذف المحافظه بنجاح `
    //     // this.message =  `Delete of RequiredDocument ${name} Successful!`;
    //     this.refreshRequiredDocuments();
    //   }
    // )
  }

  updateRequiredDocument(id) {
    console.log(`update ${id}`)
    this.router.navigate(['requiredDocuments',id])
  }

  addRequiredDocument() {
    this.router.navigate(['requiredDocuments',-1])
  }
}
