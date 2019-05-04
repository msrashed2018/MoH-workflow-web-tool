import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../../app.constants';
import { RequestDetail } from '../../model/request-detail.model';

@Injectable({
  providedIn: 'root'
})
export class RequestDetailService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllRequestDetail() {
    return this.http.get<RequestDetail[]>(`${API_URL}/request-detail`);

  }

  deleteRequestDetail(id){
    return this.http.delete(`${API_URL}/request-detail/${id}`);
  }

  retrieveRequestDetail(id){
    return this.http.get<RequestDetail>(`${API_URL}/request-detail/${id}`);
  }

  updateRequestDetail(id, requestDetail){
    return this.http.put(
          `${API_URL}/request-detail/${id}`
                , requestDetail);
  }

  createRequestDetail(requestDetail){
    return this.http.post(
              `${API_URL}/request-detail`
                , requestDetail);
  }
}