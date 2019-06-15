import { Component, OnInit, NgModule } from '@angular/core';
import { Request } from '../../../model/request.model';
import { DatePipe, CommonModule } from '@angular/common';
import { AlertModule, AlertConfig } from 'ngx-bootstrap/alert';
import * as moment from  'moment';
import { Router } from '@angular/router';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';
import { RequestService } from '../../../services/request.service';
import { BonesReveal } from '../../../model/bones-reveal.model';
@Component({
  selector: 'app-bones-reveal',
  templateUrl: './bones-reveal.component.html',
  styleUrls: ['./bones-reveal.component.scss']
})
export class BonesRevealComponent implements OnInit {
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
    this.requestService.retreiveRequestsForBonesReveal()
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

  onAttend(id) {
    this.confirmationModalService.confirm('من فضلك أدخل بصمة المواطن او اضغط علي ok', 'هل انت متاكد من  تسجيل حضور المواطن ')
    .then((confirmed) => {
      if(confirmed){
        let bonesReveal = new BonesReveal();
        bonesReveal.revealDone = '1';
        this.requestService.saveRequestBonesReveal(id, bonesReveal).subscribe(
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
