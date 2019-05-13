import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../../../services/administration/user.service';
import { User } from '../../../model/user.model';
import { FormBuilder } from '@angular/forms';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';



@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.scss']
})
export class ListUsersComponent implements OnInit {
  users: User[]
  message: string

  constructor(
    private userService:UserService,
    private router : Router, private confirmationModalService: ConfirmModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.userService.retrieveAllUsers().subscribe(
      response => {
        this.users = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف المستخدم؟ ')
    .then((confirmed) => {
      if(confirmed){
        this.userService.deleteUser(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
    
  
    
  }

  onEdit(id) {
    this.router.navigate(['administration/users',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/user-data'])
  }
}
