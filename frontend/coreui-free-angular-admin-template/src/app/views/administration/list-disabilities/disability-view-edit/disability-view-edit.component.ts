import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { DisabilityService } from '../../../../services/administration/disability.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Disability } from '../../../../model/disability.model';
import { EquipmentService } from '../../../../services/administration/equipment.service';
import { Equipment } from '../../../../model/equipment.model';

@Component({
  selector: 'app-disability-view-edit',
  templateUrl: './disability-view-edit.component.html',
  styleUrls: ['./disability-view-edit.component.scss']
})
export class DisabilityViewEditComponent implements OnInit {
  requestModel : Disability = new Disability;
  requestEquipmentId;
  componentMode;
  disabled : boolean = false;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  public equipments : Equipment[];
  public selectedEquipmentId : number
  public accepted : boolean = false;
  constructor(private formBuilder: FormBuilder,private equipmentService: EquipmentService, private disabilityService: DisabilityService, private router: Router,private route:ActivatedRoute ) { }

  ngOnInit() {

    this.fillEquipments();
    this.route.params.forEach((urlParams) => {
      this.requestEquipmentId= urlParams['id'];
      this.componentMode=urlParams['componentMode'];
      this.displayDisabilityDetails();
      if(this.componentMode == "editMode"){
          this.disabled = false;
      }else{
        this.disabled = true;
      }
    });
  }
  collapsed(event: any): void {
  }

  expanded(event: any): void {
  }
  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
  displayDisabilityDetails(){
    
    this.disabilityService.retrieveDisability(this.requestEquipmentId).subscribe(
      response => {
        this.requestModel = response as Disability;
        if(this.requestModel.equipment != null){
          this.selectedEquipmentId = this.requestModel.equipment.id;
        }

        if(this.requestModel.accepted == "1"){
          this.accepted = true;
        }else{
          this.accepted = false;
        }
      }
    )
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
  onAcceptedChecked( event) {
    this.accepted = event.target.checked;
  }
  close(){
    this.router.navigateByUrl("/administration/disabilities");
  }
}
