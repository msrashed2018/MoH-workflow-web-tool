import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DisabilityService } from '../../../services/administration/disability.service';
import { Disability } from '../../../model/disability.model';


@Component({
  selector: 'app-list-disabilities',
  templateUrl: './list-disabilities.component.html',
  styleUrls: ['./list-disabilities.component.scss']
})
export class ListDisabilitiesComponent implements OnInit {
  disabilities: Disability[]
  message: string

  constructor(
    private disabilityService:DisabilityService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshDisabilities();
  }
  refreshDisabilities(){
    this.disabilityService.retrieveAllDisabilities().subscribe(
      response => {
        console.log(response);
        this.disabilities = response;
      }
    )
  }

  deleteDisability(name,id) {
    console.log(`delete disability ${id}` )
    this.disabilityService.deleteDisability(id).subscribe (
      response => {
        console.log(response);
        // this.message =  `Delete of Disability ${name} Successful!`;
        this.refreshDisabilities();
      }
    )
  }

  updateDisability(id) {
    console.log(`update ${id}`)
    this.router.navigate(['disabilities',id])
  }

  addDisability() {
    this.router.navigate(['disabilities',-1])
  }
}
