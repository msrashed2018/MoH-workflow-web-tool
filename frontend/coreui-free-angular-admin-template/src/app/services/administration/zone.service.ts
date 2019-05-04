import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../../app.constants';
import { Zone } from '../../model/zone.model';

@Injectable({
  providedIn: 'root'
})
export class ZoneService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllZones() {
    return this.http.get<Zone[]>(`${API_URL}/zones`);

  }

  deleteZone(id){
    return this.http.delete(`${API_URL}/zones/${id}`);
  }

  retrieveZone(id){
    return this.http.get<Zone>(`${API_URL}/zones/${id}`);
  }

  updateZone(id, zone){
    return this.http.put(
          `${API_URL}/zones/${id}`
                , zone);
  }

  createZone(zone){
    return this.http.post(
              `${API_URL}/zones`
                , zone);
  }
}
