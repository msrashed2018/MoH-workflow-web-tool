import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Request } from '../model/request.model';
import { API_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllRequests() {
    return this.http.get<Request[]>(`${API_URL}/requests`);

  }

  deleteRequest(id){
    return this.http.delete(`${API_URL}/requests/${id}`);
  }

  retrieveRequest(id){
    return this.http.get<Request>(`${API_URL}/requests/${id}`);
  }

  updateRequest(id, request){
    return this.http.put(
          `${API_URL}/requests/${id}`
                , request);
  }

  createRequest(request){
    return this.http.post(
              `${API_URL}/requests`
                , request);
  }
}
