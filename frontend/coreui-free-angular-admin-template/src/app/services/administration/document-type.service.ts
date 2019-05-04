import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../../app.constants';
import { DocumentType } from '../../model/document-type.model';

@Injectable({
  providedIn: 'root'
})
export class DocumentTypeService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllDocumentType() {
    return this.http.get<DocumentType[]>(`${API_URL}/document-type`);

  }

  deleteDocumentType(id){
    return this.http.delete(`${API_URL}/document-type/${id}`);
  }

  retrieveDocumentType(id){
    return this.http.get<DocumentType>(`${API_URL}/document-type/${id}`);
  }

  updateDocumentType(id, documentType){
    return this.http.put(
          `${API_URL}/document-type/${id}`
                , documentType);
  }

  createDocumentType(documentType){
    return this.http.post(
              `${API_URL}/document-type`
                , documentType);
  }
}