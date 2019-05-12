import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CustomService } from '../../../../services/administration/custom.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-custom-view-edit',
  templateUrl: './custom-view-edit.component.html',
  styleUrls: ['./custom-view-edit.component.scss']
})
export class CustomViewEditComponent implements OnInit {
  requestModel={};
  requestStatusId;
  componentMode;
  disabled : boolean = false;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  constructor(private formBuilder: FormBuilder, private customService: CustomService, private router: Router,private route:ActivatedRoute ) { }

  ngOnInit() {
    this.route.params.forEach((urlParams) => {
      this.requestStatusId= urlParams['id'];
      this.componentMode=urlParams['componentMode'];
      this.displayCustomDetails();

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
  displayCustomDetails(){
    this.customService.retrieveCustom(this.requestStatusId).subscribe(
      response => {
        this.requestModel = response as any;
      }
    )
  }
  onSave(){
  
    this.customService.createCustom(this.requestModel).subscribe(
      result => {
        this.router.navigateByUrl("/administration/customs");
      },
      error => {
        console.log('oops', error);
        this.successMessage = false;
      }
    );
  }
  close(){
    this.router.navigateByUrl("/administration/customs");
  }
}
