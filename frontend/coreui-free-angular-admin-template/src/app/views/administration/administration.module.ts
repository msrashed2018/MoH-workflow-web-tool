import { NgModule } from '@angular/core';
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
import { AdministrationRoutingModule } from './administration-routing.module';
import { CommonModule } from '@angular/common';
import { GovernateComponent } from './list-governates/governate/governate.component';
import { RequestTypesListComponent } from './request-types/request-types-list/request-types-list.component';
import { RequestTypeDataComponent } from './request-types/request-type-data/request-type-data.component';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RequestTypeViewEditComponent } from './request-types/request-type-view-edit/request-type-view-edit.component';


@NgModule({
  declarations: [
    ListCitiesComponent,
    ListCommitteesComponent,
    ListCustomsComponent,
    ListDisabilitiesComponent,
    ListEquipmentsComponent,
    ListEyeMeasureComponent,
    ListGendersComponent,
    ListOccupationsComponent,
    ListRequestStatusComponent,
    ListRequestTypesComponent,
    ListGovernatesComponent,
    ListUsersComponent,
    ListRequiredDocumentsComponent,
    ListZonesComponent,
    GovernateComponent,
    RequestTypesListComponent,
    RequestTypeDataComponent,
    RequestTypeViewEditComponent
  ],
  imports: [
    AdministrationRoutingModule, CommonModule,FormsModule
  ],

})
export class AdministrationModule { }
