import { Component, OnInit, NgModule } from '@angular/core';
import { Request } from '../../../model/request.model';
import { DatePipe, CommonModule } from '@angular/common';
import { AlertModule, AlertConfig } from 'ngx-bootstrap/alert';
import * as moment from  'moment';
import { Router } from '@angular/router';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';
import { RequestService } from '../../../services/request.service';
import { EyeReveal } from '../../../model/eye-reveal.model';
import { PAGINATION_PAGE_SIZE } from '../../../app.constants';

@Component({
  selector: 'app-eye-reveal',
  templateUrl: './eye-reveal.component.html',
  styleUrls: ['./eye-reveal.component.scss']
})
export class EyeRevealComponent implements OnInit {
  private requests: Request[];
  private noDataFound: boolean = false;
  private errorMessage: boolean = false;
  searchKey: string = '';
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
  searchByKey(event: Event) {
    this.requests = [];
    this.page=0;
    // this.citizens = [];
    this.errorMessage = false;
    this.noDataFound = false;
    this.requestService.searchByStatesAndSearchKey("CONTINUE_REGISTERING_DONE","NA","PENDING_REVEAL",this.searchKey,this.page,PAGINATION_PAGE_SIZE)
    .subscribe(
      result => {
        if (typeof result !== 'undefined' && result !== null && result.length !=0) {
          this.noDataFound = false;
          this.requests= result['content'];
          this.pages = new Array(result['totalPages']);
        }else{
          
this.pages = new Array(0);
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
    // let date=new Date();
    // let today =this.datepipe.transform(date, 'yyyy-MM-dd');
    this.requestService.retrieveByRequestStates("CONTINUE_REGISTERING_DONE","NA","PENDING_REVEAL",this.page,PAGINATION_PAGE_SIZE)
      .subscribe(
        result => {
          if (typeof result !== 'undefined' && result !== null && result.length !=0) {
            this.noDataFound = false;
            this.requests= result['content'];
            this.pages = new Array(result['totalPages']);
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

  onAttend(id) {
    this.confirmationModalService.confirm('من فضلك أدخل بصمة المواطن او اضغط علي ok', 'هل انت متاكد من  تسجيل حضور المواطن ')
    .then((confirmed) => {
      if(confirmed){
        let eyeReveal = new EyeReveal();
        eyeReveal.revealDone = '1';
        this.requestService.saveRequestEyeReveal(id, eyeReveal).subscribe(
          result => {
            this.retriveAllRequests();
            this.errorMessage = false;
          },
          error => {
            console.log('oops', error);
            this.errorMessage = true;
          }
        )
      }
    })

  }
 
}