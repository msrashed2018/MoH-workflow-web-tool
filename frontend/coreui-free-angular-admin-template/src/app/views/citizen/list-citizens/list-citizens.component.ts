import { Component, OnInit, NgModule } from '@angular/core';
import { Citizen } from '../../../model/citizen.model';
import { CitizenService } from '../../../services/citizenService';
import { DatePipe, CommonModule } from '@angular/common';
import { AlertModule, AlertConfig } from 'ngx-bootstrap/alert';
import * as moment from  'moment';
import { Router } from '@angular/router';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';


@Component({
  selector: 'app-list-citizens',
  templateUrl: './list-citizens.component.html',
  styleUrls: ['./list-citizens.component.scss']
})
export class ListCitizensComponent implements OnInit {
  searchByID: string = '';
  private citizens: Citizen[];
  private noDataFound: boolean = false;
  private errorMessage: boolean = false;
  constructor( private confirmationModalService: ConfirmModalService, private citizenService: CitizenService, private router : Router, private datepipe: DatePipe) { }

  ngOnInit() {
    this.citizens = [];
    this.retriveAllCitizens();
  }
  searchchanged(event: Event) {
    this.citizens = [];
    this.errorMessage = false;
    this.noDataFound = false;

    this.citizenService.findCitizen(this.searchByID)
      .subscribe(
        result => {
          if (typeof result !== 'undefined' && result !== null) {
            this.noDataFound = false;
            this.citizens = result;
          }else{
            this.noDataFound = true;
          }
        },
        error => {
          console.log('oops', error);
          this.errorMessage = true;

        }
      );
  }
  calculateAge(dateString)
  {
      let birthDate : Date = new Date(dateString);
      return moment().diff(birthDate, 'years');
  }
  retriveAllCitizens(){
    this.citizens = [];
    this.errorMessage = false;
    this.noDataFound = false;
    let date=new Date();
    let latest_date =this.datepipe.transform(date, 'yyyy-MM-dd');
    this.citizenService.retriveAll(latest_date)
      .subscribe(
        result => {
          if (typeof result !== 'undefined' && result !== null) {
            console.log(result)
            this.noDataFound = false;
            this.citizens= result;
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

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المواطن؟ ')
    .then((confirmed) => {
      if(confirmed){
        this.citizenService.deleteCitizen(id).subscribe (
          response => {
            this.retriveAllCitizens();
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['citizen/view-edit',id,{componentMode: "editMode"}])
  }
  onAdd() {
    this.router.navigate(['citizen/new-citizen'])
  }
}
