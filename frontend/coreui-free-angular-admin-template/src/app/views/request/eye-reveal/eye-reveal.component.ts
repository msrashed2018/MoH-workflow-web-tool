import { Component, OnInit, NgModule } from '@angular/core';
import { Request } from '../../../model/request.model';
import { DatePipe, CommonModule } from '@angular/common';
import { AlertModule, AlertConfig } from 'ngx-bootstrap/alert';
import * as moment from  'moment';
import { Router } from '@angular/router';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';
import { RequestService } from '../../../services/request.service';
import { EyeReveal } from '../../../model/eye-reveal.model';

@Component({
  selector: 'app-eye-reveal',
  templateUrl: './eye-reveal.component.html',
  styleUrls: ['./eye-reveal.component.scss']
})
export class EyeRevealComponent implements OnInit {
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
    this.requestService.retrieveByRequestState('PAYMENT_DONE')
      .subscribe(
        result => {
          if (typeof result !== 'undefined' && result !== null && result.length !=0) {
            console.log(result);
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