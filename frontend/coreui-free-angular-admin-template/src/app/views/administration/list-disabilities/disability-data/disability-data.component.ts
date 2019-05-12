import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { DisabilityService } from '../../../../services/administration/disability.service';
import { Router } from '@angular/router';
import { Disability } from '../../../../model/disability.model';
import { Equipment } from '../../../../model/equipment.model';
import { EquipmentService } from '../../../../services/administration/equipment.service';

@Component({
  selector: 'app-disability-data',
  templateUrl: './disability-data.component.html',
  styleUrls: ['./disability-data.component.scss']
})
export class DisabilityDataComponent implements OnInit {
  requestModel : Disability= new Disability;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  public equipments : Equipment[];
  public selectedEquipmentId : number
  public accepted : boolean = false;
  constructor(private formBuilder: FormBuilder,private equipmentService: EquipmentService, private disabilityService: DisabilityService, private router: Router ) { }

  ngOnInit() {
    this.fillEquipments();
  }
  collapsed(event: any): void {
  }

  expanded(event: any): void {
  }
  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  onSave(){
    let equipment = new Equipment;
    equipment.id = this.selectedEquipmentId;
    this.requestModel.equipment = equipment;

    if(this.accepted){
      this.requestModel.accepted= '1';
    }else{
        this.requestModel.accepted= '0';
    }

    this.disabilityService.createDisability(this.requestModel).subscribe(
      result => {
        this.router.navigateByUrl("/administration/disabilities");
      },
      error => {
        console.log('oops', error);
        this.successMessage = false;
      }
    );
  }
  close(){
    this.router.navigateByUrl("/administration/disabilities");
  }
  fillEquipments(){
    this.equipmentService.retrieveAllEquipments().subscribe(
      result => {
        this.equipments = result;
      },
      error => {
        console.log('oops', error);
    });
  }
  onAcceptedChecked( event) {
    this.accepted = event.target.checked;
  }
}
