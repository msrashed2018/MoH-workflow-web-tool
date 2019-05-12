import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EyeMeasureService } from '../../../services/administration/eye-measure.service';
import { EyeMeasure } from '../../../model/eye-measure.model';
import { ConfirmationModalService } from '../confirmation-modal/confirmation-modal.service';


@Component({
  selector: 'app-list-eye-measures',
  templateUrl: './list-eye-measures.component.html',
  styleUrls: ['./list-eye-measures.component.scss']
})
export class ListEyeMeasureComponent implements OnInit {
  measures: EyeMeasure[]
  message: string

  constructor(
    private eyeMeasureService:EyeMeasureService,
    private router : Router, private confirmationModalService: ConfirmationModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.eyeMeasureService.retrieveAllEyeMeasure().subscribe(
      response => {
        this.measures = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف القياس ')
    .then((confirmed) => {
      console.log('User confirmed:', confirmed)
      if(confirmed){
        this.eyeMeasureService.deleteEyeMeasure(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['administration/eye-measures',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/eye-measure-data'])
  }
}
