import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EquipmentService } from '../../../services/administration/equipment.service';
import { Equipment } from '../../../model/equipment.model';




@Component({
  selector: 'app-list-equipments',
  templateUrl: './list-equipments.component.html',
  styleUrls: ['./list-equipments.component.scss']
})
export class ListEquipmentsComponent implements OnInit {
  equipments: Equipment[]
  message: string

  constructor(
    private equipmentService:EquipmentService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshEquipments();
  }
  refreshEquipments(){
    this.equipmentService.retrieveAllEquipments().subscribe(
      response => {
        console.log(response);
        this.equipments = response;
      }
    )
  }

  deleteEquipment(name,id) {
    console.log(`delete equipment ${id}` )
    this.equipmentService.deleteEquipment(id).subscribe (
      response => {
        console.log(response);
        // this.message =  `Delete of Equipment ${name} Successful!`;
        this.refreshEquipments();
      }
    )
  }

  updateEquipment(id) {
    console.log(`update ${id}`)
    this.router.navigate(['equipments',id])
  }

  addEquipment() {
    this.router.navigate(['equipments',-1])
  }
}
