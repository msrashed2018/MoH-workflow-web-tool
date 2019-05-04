import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GovernateService } from '../../../services/administration/governate.service';
import { Governate } from '../../../model/governate.model';
// import { BrowserModule } from '@angular/platform-browser';


@Component({
  // selector: 'app-list-governates',
  templateUrl: './list-governates.component.html',
  styleUrls: ['./list-governates.component.scss']
})
export class ListGovernatesComponent implements OnInit {
  governates: Governate[]
  message: string

  constructor(
    private governateService:GovernateService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshGovernates();
  }
  log(){
    console.log("hereeeeeeeeeee");
  }
  refreshGovernates(){
    this.governateService.retrieveAllGovernates().subscribe(
      response => {
        console.log(response);
        this.governates = response;
      }
    )
  }

  deleteGovernate(id) {
    console.log(`delete governate ${id}` )
    this.governateService.deleteGovernate(id).subscribe (
      response => {
        console.log(response);
        this.message = ` تم حذف المحافظه بنجاح `
        // this.message =  `Delete of Governate ${name} Successful!`;
        this.refreshGovernates();
      }
    )
  }

  updateGovernate(id) {
    console.log(`update ${id}`)
    // this.router.navigate(['governates',id])
  }

  addGovernate() {
    // this.router.navigate(['governates',-1])
  }
}
