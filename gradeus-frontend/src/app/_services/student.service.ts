import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Class, Topic, User } from '../models/models';
import { GlobalService } from './global.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  prefix: string = environment.rootUrl + "/student";
  constructor(
    globalService: GlobalService,
    private http: HttpClient
  ) {

  }

  getAllClasses(): Observable<Class[]> {
    return this.http.get<Class[]>(`${this.prefix}/classes`);
  }
  
  getAllTopics(classId: number): Observable<Topic[]> {
    const url = `${this.prefix}/topics?classId=${classId}`;
    return this.http.get<Topic[]>(url);
  }

  getTopicById(id: number): Observable<Topic> {
    const url = `${this.prefix}/topic/${id}`;
    return this.http.get<Topic>(url);
  }
  
  getGroupMembersInClass(classId: number): Observable<User[]> {
    const url = `${this.prefix}/group-members?classId=${classId}`;
    return this.http.get<User[]>(url);
  }
}
