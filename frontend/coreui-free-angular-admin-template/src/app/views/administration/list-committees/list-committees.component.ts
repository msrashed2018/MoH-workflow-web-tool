import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Committee } from '../../../model/committee.model';
import { CommitteeService } from '../../../services/administration/committee.service';




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
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshCommittees();
  }
  refreshCommittees(){
    this.committeeService.retrieveAllCommittees().subscribe(
      response => {
        console.log(response);
        this.committees = response;
      }
    )
  }

  deleteCommittee(name,id) {
    console.log(`delete committee ${id}` )
    this.committeeService.deleteCommittee(id).subscribe (
      response => {
        console.log(response);
        // this.message =  `Delete of Committee ${name} Successful!`;
        this.refreshCommittees();
      }
    )
  }

  updateCommittee(id) {
    console.log(`update ${id}`)
    this.router.navigate(['committees',id])
  }

  addCommittee() {
    this.router.navigate(['committees',-1])
  }
}
