import { Component, OnInit, NgModule } from '@angular/core';
import { Request } from '../../../../model/request.model';
import { DatePipe, CommonModule } from '@angular/common';
import { AlertModule, AlertConfig } from 'ngx-bootstrap/alert';
import * as moment from  'moment';
import { Router } from '@angular/router';
import { ConfirmModalService } from '../../../confirm-modal/confirm-modal.service';
import { RequestService } from '../../../../services/request.service';
@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.scss']
})
export class PaymentListComponent implements OnInit {
  private requests: Request[];
  private noDataFound: boolean = false;
  private errorMessage: boolean = false;
  searchByID: string = '';
  constructor( private confirmationModalService: ConfirmModalService, private requestService: RequestService, private router : Router, private datepipe: DatePipe) { }

  ngOnInit() {
    this.requests = [];
    this.retriveAllRequests();
  }
  searchByNationalId(event: Event) {
    // this.citizens = [];
    this.errorMessage = false;
    this.noDataFound = false;
    this.requestService.retrieveRequestsByNationalId(this.searchByID)
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
    this.requestService.retrieveByRequestState('NEW')
      .subscribe(
        result => {
          if (typeof result !== 'undefined' && result !== null && result.length !=0) {
            this.noDataFound = false;
            this.requests= result;
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

  // onDelete(citizenId, requestId) {
  //   this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف الطلب؟ ')
  //   .then((confirmed) => {
  //     if(confirmed){
  //       this.requestService.deleteRequest(citizenId, requestId).subscribe (
  //         response => {
  //           this.retriveAllRequests();
  //         }
  //       )
  //     }
  //   })
  // }

  onPay(id) {
    this.router.navigate(['request/payment-data',{requestId:id}])
  }
  onAdd() {
    // this.router.navigate(['request/new-request'])
  }
}
