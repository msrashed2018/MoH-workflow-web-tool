import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EyeRevealSettingService } from '../../../services/administration/eye-reveal-setting.service';
import { EyeRevealSetting } from '../../../model/eye-reveal-setting.model';
import { ConfirmationModalService } from '../confirmation-modal/confirmation-modal.service';


@Component({
  selector: 'app-list-eye-reveal-setting',
  templateUrl: './list-eye-reveal-setting.component.html',
  styleUrls: ['./list-eye-reveal-setting.component.scss']
})
export class ListEyeRevealSettingComponent implements OnInit {
  settings: EyeRevealSetting[]
  message: string

  constructor(
    private eyeRevealSettingService:EyeRevealSettingService,
    private router : Router, private confirmationModalService: ConfirmationModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.eyeRevealSettingService.retrieveAllEyeRevealSettings().subscribe(
      response => {
        this.settings = response;
      },
      error =>{
        console.log("oops", error);
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف الضبط ')
    .then((confirmed) => {
      console.log('User confirmed:', confirmed)
      if(confirmed){
        this.eyeRevealSettingService.deleteEyeRevealSetting(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['administration/eye-reveal-settings',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/eye-reveal-setting-data'])
  }
}
