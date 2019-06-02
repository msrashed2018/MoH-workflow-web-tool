import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListRequestsComponent } from './list-requests/list-requests.component';
import { RequestComponent } from './request/request.component';
import { AlertModule } from 'ngx-bootstrap/alert';
import { RequestViewEditComponent } from './request-view-edit/request-view-edit.component';
import { RequestRoutingModule } from './request-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmModalService } from '../confirm-modal/confirm-modal.service';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { PaymentComponent } from './request/payment/payment.component';

@NgModule({
  declarations: [ListRequestsComponent, RequestComponent, RequestViewEditComponent, PaymentComponent],
  imports: [
    CommonModule,
    AlertModule,
    RequestRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModalModule,
    CollapseModule.forRoot()
  ],
  providers :[ ConfirmModalService ]
})
export class RequestModule { }
