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
import { PaymentListComponent } from './payment/payment-list/payment-list.component';
import { PaymentDataComponent } from './payment/payment-data/payment-data.component';
import { ContinueRegisteringDataComponent } from './continue-registering/continue-registering-data/continue-registering-data.component';
import { ContinueRegisteringListComponent } from './continue-registering/continue-registering-list/continue-registering-list.component';
import { BonesRevealComponent } from './bones-reveal/bones-reveal.component';
import { EyeRevealComponent } from './eye-reveal/eye-reveal.component';
import { RevealListComponent } from './medical-reveal-result/reveal-list/reveal-list.component';
import { RevealDataComponent } from './medical-reveal-result/reveal-data/reveal-data.component';
import { ReviewRequestsComponent } from './review-requests/review-requests.component';
import { ApproveRequestsComponent } from './approve-requests/approve-requests.component';

@NgModule({
  declarations: [ListRequestsComponent, RequestComponent, RequestViewEditComponent,  PaymentListComponent, PaymentDataComponent, ContinueRegisteringDataComponent, ContinueRegisteringListComponent, BonesRevealComponent, EyeRevealComponent, RevealListComponent, RevealDataComponent, ReviewRequestsComponent, ApproveRequestsComponent],
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
