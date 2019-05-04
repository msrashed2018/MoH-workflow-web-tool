import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ListCitizensComponent } from './list-citizens/list-citizens.component';
import { CitizenComponent } from './citizen/citizen.component';
const routes: Routes = [
  {

    // path: 'citizens',
    // component: ListCitizensComponent,
    // data: {
    //   title: 'الصفحه الرئيسيه'
    // }

    path: '',
    data: {
      title: 'Citizen'
    },
    children: [
      // {
      //   path: '',
      //   redirectTo: 'citizens'
      // },
      {
        path: 'search',
        component: ListCitizensComponent,
        data: {
          title: 'Search'
        }
      },
      {
        path: 'add',
        component: CitizenComponent,
        data: {
          title: 'Add'
        }
      }
    ]
  }
];
@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CitizenRoutingModule { }
