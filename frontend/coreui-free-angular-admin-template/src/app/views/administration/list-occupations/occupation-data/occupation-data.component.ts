import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { OccupationService } from '../../../../services/administration/occupation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-occupation-data',
  templateUrl: './occupation-data.component.html',
  styleUrls: ['./occupation-data.component.scss']
})
export class OccupationDataComponent implements OnInit {
  requestModel={};
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  constructor(private formBuilder: FormBuilder, private occupationService: OccupationService, private router: Router ) { }


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
  
    this.occupationService.createOccupation(this.requestModel).subscribe(
      result => {
        this.router.navigateByUrl("/administration/occupations");
      },
      error => {
        console.log('oops', error);
        this.successMessage = false;
      }
    );
  }
  close(){
    this.router.navigateByUrl("/administration/occupations");
  }
}

