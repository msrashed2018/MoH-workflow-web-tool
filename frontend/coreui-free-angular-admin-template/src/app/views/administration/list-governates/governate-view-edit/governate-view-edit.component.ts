import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { GovernateService } from '../../../../services/administration/governate.service';
import { Zone } from '../../../../model/zone.model';
import { ZoneService } from '../../../../services/administration/zone.service';
import { Governate } from '../../../../model/governate.model';

@Component({
  selector: 'app-governate-view-edit',
  templateUrl: './governate-view-edit.component.html',
  styleUrls: ['./governate-view-edit.component.scss']
})
export class GovernateViewEditComponent implements OnInit {
  governate : Governate= new Governate;
  requestGovernateId;
  componentMode;
  disabled : boolean = false;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  public zones : Zone[];
  public selectedZoneId : number
  constructor(private formBuilder: FormBuilder,private governateService: GovernateService, private zoneService: ZoneService, private router: Router, private route:ActivatedRoute ) { }

  ngOnInit() {
    this.fillZones();
    this.route.params.forEach((urlParams) => {
      this.requestGovernateId= urlParams['id'];
      this.componentMode=urlParams['componentMode'];
      this.displayGovernateDetails();
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

  displayGovernateDetails(){
    
    this.governateService.retrieveGovernate(this.requestGovernateId).subscribe(
      response => {
        this.governate = response as Governate;
        if(this.governate.zone != null){
          this.selectedZoneId = this.governate.zone.id;
        }
      }
    )
  }
  onSave(){
    let zone = new Zone;
    zone.id = this.selectedZoneId;
    this.governate.zone = zone;

    this.governateService.createGovernate(this.governate).subscribe(
      result => {
        this.router.navigateByUrl("/administration/governates");
      },
      error => {
        console.log('oops', error);
        this.successMessage = false;
      }
    );
  }
  close(){
    this.router.navigateByUrl("/administration/governates");
  }
  fillZones(){
    this.zoneService.retrieveAllZones().subscribe(
      result => {
        this.zones = result;
      },
      error => {
        console.log('oops', error);
    });
  }

}
