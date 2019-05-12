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
import { RequestTypesListComponent } from './request-types/request-types-list/request-types-list.component';
import { RequestTypeDataComponent } from './request-types/request-type-data/request-type-data.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule, NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { RequestTypeViewEditComponent } from './request-types/request-type-view-edit/request-type-view-edit.component';
import { RequestStatusDataComponent } from './list-request-status/request-status-data/request-status-data.component';
import { RequestStatusViewEditComponent } from './list-request-status/request-status-view-edit/request-status-view-edit.component';
import { AlertModule } from 'ngx-bootstrap/alert';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { ConfirmationModalComponent } from './confirmation-modal/confirmation-modal.component';
import { ConfirmationModalService } from './confirmation-modal/confirmation-modal.service';
import { CustomDataComponent } from './list-customs/custom-data/custom-data.component';
import { CustomViewEditComponent } from './list-customs/custom-view-edit/custom-view-edit.component';
import { CommitteeDataComponent } from './list-committees/committee-data/committee-data.component';
import { CommitteeViewEditComponent } from './list-committees/committee-view-edit/committee-view-edit.component';
import { DisabilityViewEditComponent } from './list-disabilities/disability-view-edit/disability-view-edit.component';
import { DisabilityDataComponent } from './list-disabilities/disability-data/disability-data.component';
import { EquipmentDataComponent } from './list-equipments/equipment-data/equipment-data.component';
import { EquipmentViewEditComponent } from './list-equipments/equipment-view-edit/equipment-view-edit.component';
import { GovernateViewEditComponent } from './list-governates/governate-view-edit/governate-view-edit.component';
import { GovernateDataComponent } from './list-governates/governate-data/governate-data.component';
import { OccupationDataComponent } from './list-occupations/occupation-data/occupation-data.component';
import { OccupationViewEditComponent } from './list-occupations/occupation-view-edit/occupation-view-edit.component';
import { DocumentViewEditComponent } from './list-required-documents/document-view-edit/document-view-edit.component';
import { DocumentDataComponent } from './list-required-documents/document-data/document-data.component';
import { ZoneDataComponent } from './list-zones/zone-data/zone-data.component';
import { ZoneViewEditComponent } from './list-zones/zone-view-edit/zone-view-edit.component';
import { UserDataComponent } from './list-users/user-data/user-data.component';
import { UserViewEditComponent } from './list-users/user-view-edit/user-view-edit.component';
import { CityDataComponent } from './list-cities/city-data/city-data.component';
import { CityViewEditComponent } from './list-cities/city-view-edit/city-view-edit.component';
import { ListCommitteeMembersComponent } from './list-committee-members/list-committee-members.component';
import { CommitteeMemberDataComponent } from './list-committee-members/committee-member-data/committee-member-data.component';
import { CommitteeMemberViewEditComponent } from './list-committee-members/committee-member-view-edit/committee-member-view-edit.component';
import { EyeMeasureDataComponent } from './list-eye-measures/eye-measure-data/eye-measure-data.component';
import { EyeMeasureViewEditComponent } from './list-eye-measures/eye-measure-view-edit/eye-measure-view-edit.component';
import { EyeRevealSettingViewEditComponent } from './list-eye-reveal-setting/eye-reveal-setting-view-edit/eye-reveal-setting-view-edit.component';
import { EyeRevealSettingDataComponent } from './list-eye-reveal-setting/eye-reveal-setting-data/eye-reveal-setting-data.component';
import { ListEyeRevealSettingComponent } from './list-eye-reveal-setting/list-eye-reveal-setting.component';

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
    RequestTypesListComponent,
    RequestTypeDataComponent,
    RequestTypeViewEditComponent,
    RequestStatusDataComponent,
    RequestStatusViewEditComponent,
    ConfirmationModalComponent,
    CustomDataComponent,
    CustomViewEditComponent,
    CommitteeDataComponent,
    CommitteeViewEditComponent,
    DisabilityViewEditComponent,
    DisabilityDataComponent,
    EquipmentDataComponent,
    EquipmentViewEditComponent,
    GovernateViewEditComponent,
    GovernateDataComponent,
    OccupationDataComponent,
    OccupationViewEditComponent,
    DocumentViewEditComponent,
    DocumentDataComponent,
    ZoneDataComponent,
    ZoneViewEditComponent,
    UserDataComponent,
    UserViewEditComponent,
    CityDataComponent,
    CityViewEditComponent,
    ListCommitteeMembersComponent,
    CommitteeMemberDataComponent,
    CommitteeMemberViewEditComponent,
    EyeMeasureDataComponent,
    EyeMeasureViewEditComponent,
    EyeRevealSettingViewEditComponent,
    EyeRevealSettingDataComponent,
    ListEyeRevealSettingComponent
  ],
  providers :[ ConfirmationModalService ],
  imports: [
    AdministrationRoutingModule, CommonModule,FormsModule,
    AlertModule,
    FormsModule,
    ReactiveFormsModule,
    CollapseModule.forRoot(),
    NgbModalModule
  ], 
  entryComponents : [ ConfirmationModalComponent],
  exports : [ ConfirmationModalComponent]

})
export class AdministrationModule { }
