import { AlertModule } from 'ngx-bootstrap/alert';
import { CommonModule } from '@angular/common';
import { CitizensComponent } from './../../citizens/citizens.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common'
const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    data: {
      title: 'الصفحه الرئيسيه'
    }
  }
];

@NgModule({
  declarations: [CitizensComponent],
  imports: [RouterModule.forChild(routes), CommonModule ,FormsModule , AlertModule,],
  exports: [RouterModule, CitizensComponent],
  providers: [DatePipe]
})
export class DashboardRoutingModule {}
