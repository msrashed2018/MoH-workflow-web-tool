import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CustomService } from '../../../../services/administration/custom.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-custom-data',
  templateUrl: './custom-data.component.html',
  styleUrls: ['./custom-data.component.scss']
})
export class CustomDataComponent implements OnInit {
  requestModel={};
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  
  constructor(private formBuilder: FormBuilder, private customService: CustomService, private router: Router ) { }


  ngOnInit() {
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