import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EyeMeasureService } from '../../../services/administration/eye-measure.service';
import { EyeMeasure } from '../../../model/eye-measure.model';


@Component({
  selector: 'app-list-eye-measures',
  templateUrl: './list-eye-measures.component.html',
  styleUrls: ['./list-eye-measures.component.scss']
})
export class ListEyeMeasureComponent implements OnInit {
  eyeMeasures: EyeMeasure[]
  message: string

  constructor(
    private eyeMeasureService:EyeMeasureService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshEyeMeasure();
  }
  refreshEyeMeasure(){
    this.eyeMeasureService.retrieveAllEyeMeasure().subscribe(
      response => {
        console.log(response);
        this.eyeMeasures = response;
      }
    )
  }

  deleteEyeMeasure(name,id) {
    console.log(`delete eyeMeasure ${id}` )
    this.eyeMeasureService.deleteEyeMeasure(id).subscribe (
      response => {
        console.log(response);
        // this.message =  `Delete of EyeMeasure ${name} Successful!`;
        this.refreshEyeMeasure();
      }
    )
  }

  updateEyeMeasure(id) {
    console.log(`update ${id}`)
    this.router.navigate(['eye-measures',id])
  }

  addEyeMeasure() {
    this.router.navigate(['eye-measures',-1])
  }
}
