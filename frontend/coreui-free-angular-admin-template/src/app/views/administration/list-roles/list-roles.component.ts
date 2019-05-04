import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RoleService } from '../../../services/administration/role.service';
import { Role } from '../../../model/role.model';


@Component({
  selector: 'app-list-roles',
  templateUrl: './list-roles.component.html',
  styleUrls: ['./list-roles.component.scss']
})
export class ListRolesComponent implements OnInit {
  roles: Role[]
  message: string

  constructor(
    private roleService:RoleService,
    private router : Router
  ) { 

  }

  ngOnInit() {
    this.refreshRoles();
  }
  refreshRoles(){
    this.roleService.retrieveAllRoles().subscribe(
      response => {
        console.log(response);
        this.roles = response;
      }
    )
  }

  deleteRole(name,id) {
    console.log(`delete role ${id}` )
    this.roleService.deleteRole(id).subscribe (
      response => {
        console.log(response);
        this.message = ` تم حذف المحافظه بنجاح `
        // this.message =  `Delete of Role ${name} Successful!`;
        this.refreshRoles();
      }
    )
  }

  updateRole(id) {
    console.log(`update ${id}`)
    this.router.navigate(['roles',id])
  }

  addRole() {
    this.router.navigate(['roles',-1])
  }
}
