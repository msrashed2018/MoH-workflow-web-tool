import { Component, OnInit, NgModule } from '@angular/core';
import { Request } from '../../../../model/request.model';
import { DatePipe, CommonModule } from '@angular/common';
import { AlertModule, AlertConfig } from 'ngx-bootstrap/alert';
import * as moment from  'moment';
import { Router } from '@angular/router';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { RequestService } from '../../../../services/request.service';
import { PAGINATION_PAGE_SIZE } from '../../../../app.constants';

@Component({
  selector: 'app-eye-reveal-registering-list',
  templateUrl: './eye-reveal-registering-list.component.html',
  styleUrls: ['./eye-reveal-registering-list.component.scss']
})
export class EyeRevealRegisteringListComponent implements OnInit {
  private requests: Request[];
  private noDataFound: boolean = false;
  private errorMessage: boolean = false;
  searchByID: string = '';
  constructor( private confirmationModalService: ConfirmModalService, private requestService: RequestService, private router : Router, private datepipe: DatePipe) { }
  page: number = 0;
  pages: Array<number>;
  items: number = 0;
  setPage(i,event: any): void {
    // this.currentPage = event.page;
    event.preventDefault();
    this.page = i ;
    this.items = i*PAGINATION_PAGE_SIZE;
    this.retriveAllRequests();
  }
  nextPage(event: any): void {
    event.preventDefault();
    if((this.page+1) < this.pages.length){
      this.page = this.page+1
      this.items = (this.page)*PAGINATION_PAGE_SIZE;
      this.retriveAllRequests();
    }
  }
  prevPage(event: any): void {
    event.preventDefault();

    if((this.page-1) >= 0){
      this.page =this.page -1;
      this.items = (this.page)*PAGINATION_PAGE_SIZE;
      this.retriveAllRequests();
    }
  }
  ngOnInit() {
    this.requests = [];
    this.retriveAllRequests();
  }
  searchByNationalId(event: Event) {
    // this.citizens = [];
    this.errorMessage = false;
    this.noDataFound = false;
    this.requestService.searchByNationalId("CONTINUE_REGISTERING_DONE","NA","PENDING_REGISTERING",this.searchByID)
    .subscribe(
      result => {
        if (typeof result !== 'undefined' && result !== null && result.length !=0) {
          this.noDataFound = false;
          this.requests= result;
        }else{
          this.noDataFound = true;
        }
      },
      error => {
        console.log('oops: ', error);
        this.errorMessage = true;
      }
    );
   
  }
  retriveAllRequests(){
    this.requests = [];
    this.errorMessage = false;
    this.noDataFound = false;
    let date=new Date();
    // let today =this.datepipe.transform(date, 'yyyy-MM-dd');
    this.requestService.retrieveByRequestStates("CONTINUE_REGISTERING_DONE","NA","PENDING_REGISTERING",this.page,PAGINATION_PAGE_SIZE)
      .subscribe(
        result => {
          if (typeof result !== 'undefined' && result !== null && result.length !=0) {
            this.noDataFound = false;
            this.requests= result['content'];
            this.pages = new Array(result['totalPages']);
          }else{
            console.log("no data found ")
            this.noDataFound = true;
          }
        },
        error => {
          console.log('oops: ', error);
          this.errorMessage = true;

        }
      );
  }

  onContinue(id) {
    this.router.navigate(['request/eye-reveal-registering-data',{requestId:id}])
  }

}
