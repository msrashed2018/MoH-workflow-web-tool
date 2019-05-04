import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListCitizensComponent } from './list-citizens/list-citizens.component';
import { CitizenComponent } from './citizen/citizen.component';
import { CitizenRoutingModule } from './citizen-routing.module';
import { AlertModule } from 'ngx-bootstrap/alert';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CollapseModule } from 'ngx-bootstrap/collapse';


@NgModule({
  declarations: [ListCitizensComponent, CitizenComponent],
  imports: [
    CommonModule,
    AlertModule,
    CitizenRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    CollapseModule.forRoot()
  ]
})
export class CitizenModule { }
