import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TopmostcontactedbymonthService {
  // URL response
  private api = 'http://127.0.0.1:8080/api/top';
  
  constructor(private http: HttpClient) { }

  // get result
  getResult(): Observable<any> {
    return this.http.get(`${this.api}`);
  }
}
