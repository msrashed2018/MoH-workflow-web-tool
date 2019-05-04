import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ZoneService } from '../../../services/administration/zone.service';
import { Zone } from '../../../model/zone.model';


@Component({
  selector: 'app-list-zones',
  templateUrl: './list-zones.component.html',
  styleUrls: ['./list-zones.component.scss']
})
export class ListZonesComponent implements OnInit {
  zones: Zone[]
  message: string

  constructor(
    private zoneService:ZoneService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshZones();
  }
  refreshZones(){
    this.zoneService.retrieveAllZones().subscribe(
      response => {
        console.log(response);
        this.zones = response;
      }
    )
  }

  deleteZone(name,id) {
    console.log(`delete zone ${id}` )
    this.zoneService.deleteZone(id).subscribe (
      response => {
        console.log(response);
        this.message = ` تم حذف المحافظه بنجاح `
        // this.message =  `Delete of Zone ${name} Successful!`;
        this.refreshZones();
      }
    )
  }

  updateZone(id) {
    console.log(`update ${id}`)
    this.router.navigate(['zones',id])
  }

  addZone() {
    this.router.navigate(['zones',-1])
  }
}
