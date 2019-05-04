import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../../services/administration/user.service';
import { User } from '../../../model/user.model';


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
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshUsers();
  }
  refreshUsers(){
    this.userService.retrieveAllUsers().subscribe(
      response => {
        console.log(response);
        this.users = response;
      }
    )
  }

  deleteUser(name,id) {
    console.log(`delete user ${id}` )
    this.userService.deleteUser(id).subscribe (
      response => {
        console.log(response);
        this.message = ` تم حذف المحافظه بنجاح `
        // this.message =  `Delete of User ${name} Successful!`;
        this.refreshUsers();
      }
    )
  }

  updateUser(id) {
    console.log(`update ${id}`)
    this.router.navigate(['users',id])
  }

  addUser() {
    this.router.navigate(['users',-1])
  }
}
