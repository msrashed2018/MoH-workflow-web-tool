import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { OccupationService } from '../../../services/administration/occupation.service';
import { Occupation } from '../../../model/occupation.model';
import { FormBuilder } from '@angular/forms';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';


@Component({
  selector: 'app-list-occupations',
  templateUrl: './list-occupations.component.html',
  styleUrls: ['./list-occupations.component.scss']
})
export class ListOccupationsComponent implements OnInit {
  occupations: Occupation[]
  message: string

  constructor(
    private occupationService:OccupationService,
    private router : Router, private confirmationModalService: ConfirmModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.occupationService.retrieveAllOccupations().subscribe(
      response => {
        this.occupations = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المقر ')
    .then((confirmed) => {
      if(confirmed){
        this.occupationService.deleteOccupation(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
    
  
    
  }

  onEdit(id) {
    this.router.navigate(['administration/occupations',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/occupation-data'])
  }
}
