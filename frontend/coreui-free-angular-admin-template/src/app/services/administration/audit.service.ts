import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../../app.constants';
import { Audit } from '../../model/audit.model';

@Injectable({
  providedIn: 'root'
})
export class AuditService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllAudits(page,size) {
    return this.http.get<Audit[]>(`${API_URL}/audits?page=${page}&size=${size}`);
  }

  deleteAudit(id){
    return this.http.delete(`${API_URL}/audits/${id}`);
  }

  deleteAllAudits(){
    return this.http.delete(`${API_URL}/audits`);
  }

  retrieveAudit(id){
    return this.http.get<Audit>(`${API_URL}/audits/${id}`);
  }
}
