import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RequestComponent } from './request/request.component';
import { ListRequestsComponent } from './list-requests/list-requests.component';
const routes: Routes = [
  {
    path: 'search',
    component: ListRequestsComponent,
    data: {
      title: 'requests'
    }
  },
  {
    path: ':id',
    component: RequestComponent,
    data: {
      title: 'Request'
    }
  }
  
];
@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RequestRoutingModule { }
