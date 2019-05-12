import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CityService } from '../../../services/administration/city.service';
import { City } from '../../../model/city.model';
import { ConfirmationModalService } from '../confirmation-modal/confirmation-modal.service';


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
    private router : Router, 
    private confirmationModalService: ConfirmationModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.cityService.retrieveAllCities().subscribe(
      response => {
        this.cities = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المدينة ')
    .then((confirmed) => {
      console.log('User confirmed:', confirmed)
      if(confirmed){
        this.cityService.deleteCity(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['administration/cities',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/city-data'])
  }
}
