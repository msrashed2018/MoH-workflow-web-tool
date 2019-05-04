import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListCitiesComponent } from './list-cities/list-cities.component';
import { ListCommitteesComponent } from './list-committees/list-committees.component';
import { ListCustomsComponent } from './list-customs/list-customs.component';
import { ListDisabilitiesComponent } from './list-disabilities/list-disabilities.component';
import { ListEquipmentsComponent } from './list-equipments/list-equipments.component';
import { ListEyeMeasureComponent } from './list-eye-measures/list-eye-measures.component';
import { ListGendersComponent } from './list-genders/list-genders.component';
import { ListOccupationsComponent } from './list-occupations/list-occupations.component';
import { ListRequestStatusComponent } from './list-request-status/list-request-status.component';
import { ListRequestTypesComponent } from './list-request-types/list-request-types.component';
import { ListGovernatesComponent } from './list-governates/list-governates.component';
import { ListUsersComponent } from './list-users/list-users.component';
import { ListRequiredDocumentsComponent } from './list-required-documents/list-required-documents.component';
import { ListZonesComponent } from './list-zones/list-zones.component';
import { GovernateComponent } from './list-governates/governate/governate.component';
const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Administration'
    },
    children: [
      {
        path: '',
        redirectTo: 'request-types'
      },
      {
        path: 'request-types',
        component: ListRequestTypesComponent,
        data: {
          title: 'Request Types'
        }
      },
      {
        path: 'request-status',
        component: ListRequestStatusComponent,
        data: {
          title: 'Request Status'
        }
      },
      {
        path: 'governates',
        component: ListGovernatesComponent,
        data: {
          title: 'Governates'
        }
        // ,
        // children: [
        //   {
        //     path: 'governate',
        //     component: GovernateComponent,
        //     redirectTo: 'governate'
        //   }
        // ]
      },
     
      {
        path: 'cities',
        component: ListCitiesComponent,
        data: {
          title: 'Cities'
        }
      },
      {
        path: 'occupations',
        component: ListOccupationsComponent,
        data: {
          title: 'Occupations'
        }
      },
      {
        path: 'zones',
        component: ListZonesComponent,
        data: {
          title: 'Zones'
        }
      },
      {
        path: 'customs',
        component: ListCustomsComponent,
        data: {
          title: 'Customs'
        }
      },
      {
        path: 'disability-types',
        component: ListDisabilitiesComponent,
        data: {
          title: 'Disability Types'
        }
      },
      {
        path: 'equipments',
        component: ListEquipmentsComponent,
        data: {
          title: 'Equipments'
        }
      },
      {
        path: 'users',
        component: ListUsersComponent,
        data: {
          title: 'Users'
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
export class AdministrationRoutingModule { }
