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
