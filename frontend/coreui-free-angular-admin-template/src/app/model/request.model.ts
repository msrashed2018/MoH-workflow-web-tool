import { Citizen } from './citizen.model';
import { RequestType } from './request-type.model';
import { Committee } from './committee.model';
import { RequestStatus } from './request-status.model';
import { Custom } from './custom.model';
import { RequestDocument } from './request-document.model';
import { TrafficManagement } from './traffic-management.model';
import { RequestDetail } from './request-detail.model';

export class Request {
    public id : number;
    public name: string;
    public citizen: Citizen;
    public requestDate: String;
    public requestType: RequestType;
    public receiptSerialNumber: string;
    public custom: Custom;
    public oldRequestId: number;
    public paymentDone: string;
    public paymentDate: string;
    public committee: Committee;
    public createdBy: string;
    public modifiedBy: string;
    public modifiedDate: string;
    public requestStatus: RequestStatus;
    public requestDetail: RequestDetail;
    public trafficManagement: TrafficManagement;
    public documents: RequestDocument[];
}
