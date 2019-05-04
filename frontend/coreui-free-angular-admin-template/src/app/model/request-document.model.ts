import { Request } from './request.model';
import { DocumentType } from './document-type.model';

export class RequestDocument {
    public id: number;
    public request: Request;
    public type: DocumentType;
    public path: string;
}
