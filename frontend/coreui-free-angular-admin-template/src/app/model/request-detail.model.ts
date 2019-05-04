import { Request } from './request.model';
import { Disability } from './disability.model';

export class RequestDetail {
    public id: number;
    public request: Request;
    public rightEye: string;
    public leftEye: string;
    public useGlasses: string;
    public distinguishColor: string;
    public fieldOfSight: string;
    public squint: string;
    public disability: Disability;
    public rejectionReason: string;
    public acceptanceReasonsquint: string;
}
