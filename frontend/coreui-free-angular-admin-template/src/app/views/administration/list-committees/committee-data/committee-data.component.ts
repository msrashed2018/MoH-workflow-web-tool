import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder } from '@angular/forms';
import { CommitteeService } from '../../../../services/administration/committee.service';
import { Router } from '@angular/router';
import { Zone } from '../../../../model/zone.model';
import { ZoneService } from '../../../../services/administration/zone.service';
import { Committee } from '../../../../model/committee.model';
import { CommitteeMemberService } from '../../../../services/administration/committee-member.service';
import { CommitteeMember } from '../../../../model/committee-member.model';

@Component({
  selector: 'app-committee-data',
  templateUrl: './committee-data.component.html',
  styleUrls: ['./committee-data.component.scss']
})
export class CommitteeDataComponent implements OnInit {
  requestModel : Committee= new Committee;
  successMessage: boolean = false;
  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  public zones : Zone[];
  public members : CommitteeMember[]
  public selectedZoneId : number
  public selectedMember1Id : number
  public selectedMember2Id : number
  public selectedMember3Id : number
  public selectedMember4Id : number
  public selectedMember5Id : number
  public selectedMember6Id : number
  
  constructor(private formBuilder: FormBuilder,private committeeMemberService: CommitteeMemberService ,private zoneService: ZoneService, private committeeService: CommitteeService, private router: Router ) { }

  ngOnInit() {
    this.zones = [];
    this.members = [];
    this.fillZones();
    this.fillCommitteeMembers();
    this.selectedMember6Id = 0;
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
    let zone = new Zone;
    zone.id = this.selectedZoneId;
    this.requestModel.zone = zone;

    let committeeMember = new CommitteeMember;
    committeeMember.id = this.selectedMember1Id;
    this.requestModel.memberOne = committeeMember;

    committeeMember.id = this.selectedMember2Id;
    this.requestModel.memberTwo = committeeMember;


    committeeMember.id = this.selectedMember3Id;
    this.requestModel.memberThree = committeeMember;


    committeeMember.id = this.selectedMember4Id;
    this.requestModel.memberFour = committeeMember;


    committeeMember.id = this.selectedMember5Id;
    this.requestModel.memberFive = committeeMember;


    if(this.selectedMember6Id != 0){
      committeeMember.id = this.selectedMember6Id;
      this.requestModel.memberSix = committeeMember;
    }

    this.committeeService.createCommittee(this.requestModel).subscribe(
      result => {
        this.router.navigateByUrl("/administration/committees");
      },
      error => {
        console.log('oops', error);
        this.successMessage = false;
      }
    );
  }
  close(){
    this.router.navigateByUrl("/administration/committees");
  }

  fillZones(){
    this.zoneService.retrieveAllZones().subscribe(
      result => {
        this.zones = result;
      },
      error => {
        console.log('oops', error);
    });
  }
  fillCommitteeMembers(){
    this.committeeMemberService.retrieveAllCommitteeMembers().subscribe(
      result => {
        this.members = result;
      },
      error => {
        console.log('oops', error);
    });
  }
}