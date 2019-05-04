import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Custom } from '../../../model/custom.model';
import { CustomService } from '../../../services/administration/custom.service';



@Component({
  selector: 'app-list-customs',
  templateUrl: './list-customs.component.html',
  styleUrls: ['./list-customs.component.scss']
})
export class ListCustomsComponent implements OnInit {
  customs: Custom[]
  message: string

  constructor(
    private cityService:CustomService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshCustoms();
  }
  refreshCustoms(){
    this.cityService.retrieveAllCustoms().subscribe(
      response => {
        console.log(response);
        this.customs = response;
      }
    )
  }

  deleteCustom(name,id) {
    console.log(`delete city ${id}` )
    this.cityService.deleteCustom(id).subscribe (
      response => {
        console.log(response);
        // this.message =  `Delete of Custom ${name} Successful!`;
        this.refreshCustoms();
      }
    )
  }

  updateCustom(id) {
    console.log(`update ${id}`)
    this.router.navigate(['customs',id])
  }

  addCustom() {
    this.router.navigate(['customs',-1])
  }
}
