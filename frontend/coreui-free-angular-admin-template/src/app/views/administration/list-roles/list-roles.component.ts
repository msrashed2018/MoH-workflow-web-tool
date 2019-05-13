import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RoleService } from '../../../services/administration/role.service';
import { Role } from '../../../model/role.model';
import { FormBuilder } from '@angular/forms';
import { ConfirmModalService } from '../../confirm-modal/confirm-modal.service';

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
    private router : Router, private confirmationModalService: ConfirmModalService
  ) { 

  }

  ngOnInit() {
    this.refreshData();
  }
  refreshData(){
    this.roleService.retrieveAllRoles().subscribe(
      response => {
        this.roles = response;
      }
    )
  }

  onDelete(id) {
    this.confirmationModalService.confirm('برجاء التاكيد', 'هل انت متاكد من حذف السيارة؟ ')
    .then((confirmed) => {
      console.log('User confirmed:', confirmed)
      if(confirmed){
        this.roleService.deleteRole(id).subscribe (
          response => {
            this.refreshData();
          }
        )
      }
    })
    
  
    
  }

  onEdit(id) {
    this.router.navigate(['administration/roles',id,{componentMode: "editMode"}])
  }

  onAdd() {
    this.router.navigate(['administration/role-data'])
  }
}

