import { Injectable } from '@angular/core';
import { HttpClient , HttpEvent, HttpRequest, HttpHeaders} from '@angular/common/http';
import { Request } from '../model/request.model';
import { API_URL } from '../app.constants';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { text } from '@angular/core/src/render3';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveCitizenRequests(citizenId) {
    return this.http.get<Request[]>
      (`${API_URL}/citizens/${citizenId}/requests`).pipe( map(
        data => {
        return data;
      }));
  }

  retrieveRequestsByDate(date :string) {
    return this.http.get<Request[]>
      (`${API_URL}`+'/requests/search/findAllByDate?date='+date).pipe( map(
        data => {
        return data;
      }));
  }

  retrieveRequestsByNationalId(nationalId) {
    return this.http.get<Request[]>
      (`${API_URL}`+'/requests/search/findAllByNationalId?nationalId='+nationalId).pipe( map(
        data => {
        return data;
      }));
  }
  retreiveRequestsForEyeReveal() {
    return this.http.get<Request[]>
      (`${API_URL}`+'/requests/retreiveRequestsForEyeReveal').pipe( map(
        data => {
        return data;
      }));
  }
  retreiveRequestsForBonesReveal() {
    return this.http.get<Request[]>
      (`${API_URL}`+'/requests/retreiveRequestsForBonesReveal').pipe( map(
        data => {
        return data;
      }));
  }
  retreiveRequestsForRevealsRegistering() {
    return this.http.get<Request[]>
      (`${API_URL}`+'/requests/retreiveRequestsForRevealsRegistering').pipe( map(
        data => {
        return data;
      }));
  }
  retreiveRequestsForReviewing() {
    return this.http.get<Request[]>
      (`${API_URL}`+'/requests/retreiveRequestsForReviewing').pipe( map(
        data => {
        return data;
      }));
  }
  retreiveRequestsForApproving() {
    return this.http.get<Request[]>
      (`${API_URL}`+'/requests/retreiveRequestsForApproving').pipe( map(
        data => {
        return data;
      }));
  }
  retrieveByRequestState(state) {
    return this.http.get<Request[]>
      (`${API_URL}`+'/requests/retreiveByRequestState?state='+state).pipe( map(
        data => {
        return data;
      }));
  }
  deleteRequest(citizenId, requestId){
    return this.http.delete(`${API_URL}/citizens/${citizenId}/requests/${requestId}`);
  }

  retrieveRequest(id){
    return this.http.get<Request>(`${API_URL}/requests/${id}`);
  }

  approveRequest(requestId,request){
    return this.http.put(
          `${API_URL}/requests/${requestId}/approve`
                , request);
  }
  reviewRequest(requestId,request){
    return this.http.put(
          `${API_URL}/requests/${requestId}/review`
                , request);
  }
  updateRequest(citizenId, requestId, request){
    return this.http.put(
          `${API_URL}/citizens/${citizenId}/requests/${requestId}`
                , request);
  }
  updateRequestStatus(citizenId, requestId, requestStatus){
    return this.http.put(
          `${API_URL}/citizens/${citizenId}/requests/${requestId}/updateStatus`
                , requestStatus);
  }

  createRequest(citizenId,request){
    return this.http.post(
              `${API_URL}/citizens/${citizenId}/requests`
                , request);
  }

  // saveRequestEyeCommitte(id,eyeReveal){
  //   return this.http.post(
  //             `${API_URL}/requests/${id}/eye-reveal`
  //               , eyeReveal);
  // }
  saveRequestPayment(id,payment){
    return this.http.post(
              `${API_URL}/requests/${id}/payment`
                , payment);
  }

  retreiveRequestPayment(id){
    return this.http.get(
              `${API_URL}/requests/${id}/payment`);
  }
  saveRequestEyeReveal(id,eyeReveal){
    return this.http.post(
              `${API_URL}/requests/${id}/eye-reveal`
                , eyeReveal);
  }
  updateRequestEyeReveal(requestId,eyeRevealId,eyeReveal){
    return this.http.put(
              `${API_URL}/requests/${requestId}/eye-reveal/${eyeRevealId}`
                , eyeReveal);
  }
  retreiveRequestEyeReveal(id){
    return this.http.get(
              `${API_URL}/requests/${id}/eye-reveal`);
  }
  saveRequestBonesReveal(id,eyeReveal){
    return this.http.post(
              `${API_URL}/requests/${id}/bones-reveal`
                , eyeReveal);
  }
  updateRequestBonesReveal(requestId,bonesRevealId,bonesReveal){
    return this.http.put(
              `${API_URL}/requests/${requestId}/bones-reveal/${bonesRevealId}`
                , bonesReveal);
  }
  retreiveRequestBonesReveal(id){
    return this.http.get(
              `${API_URL}/requests/${id}/bones-reveal`);
  }
  pushFileToStorage(id, documentType,fileList: FileList): Observable<HttpEvent<{}>> {
    
    let formdata: FormData = new FormData();
    for (var i=0; i< fileList.length; i++){
      formdata.append('file', fileList.item(i));
    }
    const req = new HttpRequest('POST', `${API_URL}/requests/${id}/documents/${documentType}`, formdata, {
      reportProgress: true,
      responseType: "text"
    });
 
    return this.http.request(req);
  }
 
  getFiles(id,documentType) {
    return this.http.get<string[]>(`${API_URL}/requests/${id}/documents/${documentType}`)
  }
//https://stackoverflow.com/questions/51682514/how-download-a-file-from-httpclient
  getFile(id, fileName){
    this.http.get(`${API_URL}/requests/${id}/document/${fileName}`,{responseType: 'arraybuffer'} )
      .subscribe(response => this.downLoadFile(response, "application/pdf"));
  }

      /**
     * Method is use to download file.
     * @param data - Array Buffer data
     * @param type - type of the document.
     */
  downLoadFile(data: any, type: string) {
    var blob = new Blob([data], { type: type});
    var url = window.URL.createObjectURL(blob);
    var pwa = window.open(url);
    if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
        alert( 'Please disable your Pop-up blocker and try again.');
    }
  }
}
