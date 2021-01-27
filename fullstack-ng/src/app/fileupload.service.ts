import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fileupload } from './fileupload';

@Injectable({
  providedIn: 'root'
})
export class FileuploadService {
  // URLs response
  private api_getFile = 'http://127.0.0.1:8080/api/files'
  private api_uploadFile = 'http://127.0.0.1:8080/api/upload'
  constructor(private http: HttpClient) { }
  
  // send data as POST
  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('files', file);
    const req = new HttpRequest('POST', `${this.api_uploadFile}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }
  
  // get list of files
  getFiles(): Observable<Fileupload[]> {
    return this.http.get<Fileupload[]>(`${this.api_getFile}`);
  }
}
