import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../../app.constants';
import { RequestDocument } from '../../model/request-document.model';

@Injectable({
  providedIn: 'root'
})
export class RequestDocumentService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllRequestDocument() {
    return this.http.get<RequestDocument[]>(`${API_URL}/request-document`);

  }

  deleteRequestDocument(id){
    return this.http.delete(`${API_URL}/request-document/${id}`);
  }

  retrieveRequestDocument(id){
    return this.http.get<RequestDocument>(`${API_URL}/request-document/${id}`);
  }

  updateRequestDocument(id, requestDocument){
    return this.http.put(
          `${API_URL}/request-document/${id}`
                , requestDocument);
  }

  createRequestDocument(requestDocument){
    return this.http.post(
              `${API_URL}/request-document`
                , requestDocument);
  }
}