import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommitteeMember } from '../../../model/committee-member.model';
import { CommitteeMemberService } from '../../../services/administration/committee-member.service';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';



@Component({
  templateUrl: './list-committee-members.component.html',
  styleUrls: ['./list-committee-members.component.scss']
})
export class ListCommitteeMembersComponent implements OnInit {
  committeeMembers: CommitteeMember[]
  message: string

  constructor(
    private committeeMemberService:CommitteeMemberService,
    private router : Router, private confirmationModalService: ConfirmModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.committeeMemberService.retrieveAllCommitteeMembers().subscribe(
      response => {
        this.committeeMembers = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف العضو؟ ')
    .then((confirmed) => {
      if(confirmed){
        this.committeeMemberService.deleteCommitteeMember(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
  }

  onEdit(id) {
    this.router.navigate(['administration/committee-members',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/committee-member-data'])
  }
}
