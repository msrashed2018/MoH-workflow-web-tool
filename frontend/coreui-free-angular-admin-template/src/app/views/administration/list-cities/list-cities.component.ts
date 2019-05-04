import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CityService } from '../../../services/administration/city.service';
import { City } from '../../../model/city.model';



@Component({
  selector: 'app-list-cities',
  templateUrl: './list-cities.component.html',
  styleUrls: ['./list-cities.component.scss']
})
export class ListCitiesComponent implements OnInit {
  cities: City[]
  message: string

  constructor(
    private cityService:CityService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshCities();
  }
  refreshCities(){
    this.cityService.retrieveAllCities().subscribe(
      response => {
        console.log(response);
        this.cities = response;
      }
    )
  }

  deleteCity(name,id) {
    console.log(`delete city ${id}` )
    this.cityService.deleteCity(id).subscribe (
      response => {
        console.log(response);
        // this.message =  `Delete of City ${name} Successful!`;
        this.refreshCities();
      }
    )
  }

  updateCity(id) {
    console.log(`update ${id}`)
    this.router.navigate(['cities',id])
  }

  addCity() {
    this.router.navigate(['cities',-1])
  }
}
