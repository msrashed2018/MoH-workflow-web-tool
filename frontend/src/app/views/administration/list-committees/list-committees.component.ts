import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Committee } from '../../../model/committee.model';
import { CommitteeService } from '../../../services/administration/committee.service';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';
import { COMMITTEES_PAGE_SIZE } from '../../../app.constants';

@Component({
  selector: 'app-list-committees',
  templateUrl: './list-committees.component.html',
  styleUrls: ['./list-committees.component.scss']
})
export class ListCommitteesComponent implements OnInit {
  committees: Committee[]
  message: string

  constructor(
    private committeeService:CommitteeService,
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
    this.items = i*COMMITTEES_PAGE_SIZE;
    this.refreshData();
  }
  nextPage(event: any): void {
    event.preventDefault();
    if((this.page+1) < this.pages.length){
      this.page = this.page+1
      this.items = (this.page)*COMMITTEES_PAGE_SIZE;
      this.refreshData();
    }
  }
  prevPage(event: any): void {
    event.preventDefault();

    if((this.page-1) >= 0){
      this.page =this.page -1;
      this.items = (this.page)*COMMITTEES_PAGE_SIZE;
      this.refreshData();
    }
  }
  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.committeeService.retrieveAllCommittees(this.page,COMMITTEES_PAGE_SIZE).subscribe(
      response => {
        this.committees = response['content'];
        this.pages = new Array(response['totalPages']);
      },
      error =>{
        console.log('oops',error);
        this.message = error.error.message;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف اللجنة؟ ')
    .then((confirmed) => {
      if(confirmed){
        this.committeeService.deleteCommittee(id).subscribe (
          response => {
            this.refreshData();
          },
          error =>{
            console.log('oops',error)
            this.message = error.error.message  
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['administration/committees',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/committee-data'])
  }
}
