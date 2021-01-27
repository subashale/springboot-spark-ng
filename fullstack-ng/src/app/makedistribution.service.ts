import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MakedistributionService {
  // URL response
  private api = 'http://127.0.0.1:8080/api/dist';

  constructor(private http: HttpClient) { }

  // get data
  getResult(): Observable<any> {
    return this.http.get(`${this.api}`);
  }
}
