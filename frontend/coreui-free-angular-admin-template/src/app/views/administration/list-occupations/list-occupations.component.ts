import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OccupationService } from '../../../services/administration/occupation.service';
import { Occupation } from '../../../model/occupation.model';


@Component({
  selector: 'app-list-occupations',
  templateUrl: './list-occupations.component.html',
  styleUrls: ['./list-occupations.component.scss']
})
export class ListOccupationsComponent implements OnInit {
  occupations: Occupation[]
  message: string

  constructor(
    private occupationService:OccupationService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshOccupations();
  }
  refreshOccupations(){
    this.occupationService.retrieveAllOccupations().subscribe(
      response => {
        console.log(response);
        this.occupations = response;
      }
    )
  }

  deleteOccupation(name,id) {
    console.log(`delete occupation ${id}` )
    this.occupationService.deleteOccupation(id).subscribe (
      response => {
        console.log(response);
        // this.message =  `Delete of Occupation ${name} Successful!`;
        this.refreshOccupations();
      }
    )
  }

  updateOccupation(id) {
    console.log(`update ${id}`)
    this.router.navigate(['occupations',id])
  }

  addOccupation() {
    this.router.navigate(['occupations',-1])
  }
}
