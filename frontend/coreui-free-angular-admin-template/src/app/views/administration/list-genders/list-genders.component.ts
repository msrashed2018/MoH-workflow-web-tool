import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GenderService } from '../../../services/administration/gender.service';
import { Gender } from '../../../model/gender.model';


@Component({
  selector: 'app-list-genders',
  templateUrl: './list-genders.component.html',
  styleUrls: ['./list-genders.component.scss']
})
export class ListGendersComponent implements OnInit {
  genders: Gender[]
  message: string

  constructor(
    private genderService:GenderService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshGenders();
  }
  refreshGenders(){
    this.genderService.retrieveAllGenders().subscribe(
      response => {
        console.log(response);
        this.genders = response;
      }
    )
  }

  deleteGender(name,id) {
    console.log(`delete gender ${id}` )
    this.genderService.deleteGender(id).subscribe (
      response => {
        console.log(response);
        // this.message =  `Delete of Gender ${name} Successful!`;
        this.refreshGenders();
      }
    )
  }

  updateGender(id) {
    console.log(`update ${id}`)
    this.router.navigate(['genders',id])
  }

  addGender() {
    this.router.navigate(['genders',-1])
  }
}
