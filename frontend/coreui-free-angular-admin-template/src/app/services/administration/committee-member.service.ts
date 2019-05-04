import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from '../../app.constants';
import { CommitteeMember } from '../../model/committee-member.model';

@Injectable({
  providedIn: 'root'
})
export class CommitteeMemberService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllCommitteeMembers() {
    return this.http.get<CommitteeMember[]>(`${API_URL}/committe-members`);

  }

  deleteCommitteeMember(id){
    return this.http.delete(`${API_URL}/committe-members/${id}`);
  }

  retrieveCommitteeMember(id){
    return this.http.get<CommitteeMember>(`${API_URL}/committe-members/${id}`);
  }

  updateCommitteeMember(id, committeMember){
    return this.http.put(
          `${API_URL}/committe-members/${id}`
                , committeMember);
  }

  createCommitteeMember(committeMember){
    return this.http.post(
              `${API_URL}/committe-members`
                , committeMember);
  }
}
