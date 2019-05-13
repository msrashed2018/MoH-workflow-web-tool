import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EquipmentService } from '../../../services/administration/equipment.service';
import { Equipment } from '../../../model/equipment.model';
import { FormBuilder } from '@angular/forms';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';

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
    private router : Router, private confirmationModalService: ConfirmModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.equipmentService.retrieveAllEquipments().subscribe(
      response => {
        this.equipments = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف السيارة؟ ')
    .then((confirmed) => {
      console.log('User confirmed:', confirmed)
      if(confirmed){
        this.equipmentService.deleteEquipment(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
    
  
    
  }

  onEdit(id) {
    this.router.navigate(['administration/equipments',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/equipment-data'])
  }
}
