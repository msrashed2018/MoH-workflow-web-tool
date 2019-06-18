import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RequestComponent } from './request/request.component';
import { ListRequestsComponent } from './list-requests/list-requests.component';
import { PaymentListComponent } from './payment/payment-list/payment-list.component';
import { PaymentDataComponent } from './payment/payment-data/payment-data.component';
import { ContinueRegisteringListComponent } from './continue-registering/continue-registering-list/continue-registering-list.component';
import { ContinueRegisteringDataComponent } from './continue-registering/continue-registering-data/continue-registering-data.component';
import { BonesRevealComponent } from './bones-reveal/bones-reveal.component';
import { EyeRevealComponent } from './eye-reveal/eye-reveal.component';
import { RevealListComponent } from './medical-reveal-result/reveal-list/reveal-list.component';
import { RevealDataComponent } from './medical-reveal-result/reveal-data/reveal-data.component';
import { ReviewRequestsComponent } from './review-requests/review-requests.component';
import { ApproveRequestsComponent } from './approve-requests/approve-requests.component';
const routes: Routes = [
  {
    path: 'search',
    component: ListRequestsComponent,
    data: {
      title: 'requests'
    }
  },
  {
    path: 'payments',
    component: PaymentListComponent,
    data: {
      title: 'Payments'
    }
  },
  {
    path: 'payment-data',
    component: PaymentDataComponent,
    data: {
      title: 'Payment data'
    }
  },
  {
    path: 'continue-registering',
    component: ContinueRegisteringListComponent,
    data: {
      title: 'Continue Registering'
    }
  },
  {
    path: 'continue-registering-data',
    component: ContinueRegisteringDataComponent,
    data: {
      title: 'continue-registering data'
    }
  },
  {
    path: 'bones-reveal',
    component: BonesRevealComponent,
    data: {
      title: 'bones-reveal data'
    }
  },
  {
    path: 'eye-reveal',
    component: EyeRevealComponent,
    data: {
      title: 'eye-reveal data'
    }
  },
  {
    path: 'medical-reveals',
    component: RevealListComponent,
    data: {
      title: 'medical-reveals'
    }
  },
  {
    path: 'medical-reveals-data',
    component: RevealDataComponent,
    data: {
      title: 'medical-reveals-data'
    }
  },
  {
    path: 'review-requests',
    component: ReviewRequestsComponent,
    data: {
      title: 'review-requests'
    }
  },
  {
    path: 'approve-requests',
    component: ApproveRequestsComponent,
    data: {
      title: 'approve-requests'
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
