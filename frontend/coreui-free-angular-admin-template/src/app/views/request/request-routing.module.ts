import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RequestComponent } from './request/request.component';
import { ListRequestsComponent } from './list-requests/list-requests.component';
import { PaymentListComponent } from './payment/payment-list/payment-list.component';
import { PaymentDataComponent } from './payment/payment-data/payment-data.component';
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
