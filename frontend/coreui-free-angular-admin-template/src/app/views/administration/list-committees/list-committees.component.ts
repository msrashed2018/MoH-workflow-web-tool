import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Committee } from '../../../model/committee.model';
import { CommitteeService } from '../../../services/administration/committee.service';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';

@Component({
  selector: 'app-list-committees',
  templateUrl: './list-committees.component.html',
  styleUrls: ['./list-committees.component.scss']
})
export class ListCommitteesComponent implements OnInit {
  committees: Committee[]
  message: string

  constructor(
    private committeeService:CommitteeService,
    private router : Router, private confirmationModalService: ConfirmModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.committeeService.retrieveAllCommittees().subscribe(
      response => {
        this.committees = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف اللجنة؟ ')
    .then((confirmed) => {
      if(confirmed){
        this.committeeService.deleteCommittee(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['administration/committees',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/committee-data'])
  }
}
