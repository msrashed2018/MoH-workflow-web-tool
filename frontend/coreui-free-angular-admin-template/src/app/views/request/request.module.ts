import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListRequestsComponent } from './list-requests/list-requests.component';
import { RequestComponent } from './request/request.component';

@NgModule({
  declarations: [ListRequestsComponent, RequestComponent],
  imports: [
    CommonModule
  ]
})
export class RequestModule { }
