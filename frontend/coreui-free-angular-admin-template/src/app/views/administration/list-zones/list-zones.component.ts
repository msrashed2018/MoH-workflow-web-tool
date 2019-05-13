import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ZoneService } from '../../../services/administration/zone.service';
import { Zone } from '../../../model/zone.model';
import { FormBuilder } from '@angular/forms';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';


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
    private router : Router, private confirmationModalService: ConfirmModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.zoneService.retrieveAllZones().subscribe(
      response => {
        this.zones = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المقر ')
    .then((confirmed) => {
      if(confirmed){
        this.zoneService.deleteZone(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
    
  
    
  }

  onEdit(id) {
    this.router.navigate(['administration/zones',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/zone-data'])
  }
}
