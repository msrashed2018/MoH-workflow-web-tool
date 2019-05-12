import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DisabilityService } from '../../../services/administration/disability.service';
import { Disability } from '../../../model/disability.model';
import { FormBuilder } from '@angular/forms';
import { ConfirmationModalService } from '../confirmation-modal/confirmation-modal.service';

@Component({
  selector: 'app-list-disabilities',
  templateUrl: './list-disabilities.component.html',
  styleUrls: ['./list-disabilities.component.scss']
})
export class ListDisabilitiesComponent implements OnInit {
  disabilities: Disability[]
  message: string

  constructor(
    private disabilityService:DisabilityService,
    private router : Router, private confirmationModalService: ConfirmationModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.disabilityService.retrieveAllDisabilities().subscribe(
      response => {
        this.disabilities = response as Disability[];
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف السيارة؟ ')
    .then((confirmed) => {
      console.log('User confirmed:', confirmed)
      if(confirmed){
        this.disabilityService.deleteDisability(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['administration/disabilities',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/disability-data'])
  }
}
