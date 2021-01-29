import { Component, OnInit } from '@angular/core';
import { FileuploadService } from '../fileupload.service';
import { Fileupload } from '../fileupload';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-fileupload',
  templateUrl: './fileupload.component.html',
  styleUrls: ['./fileupload.component.css']
})
export class FileuploadComponent implements OnInit {

  // stores files
  selectedFiles?: FileList;
  // current file
  currentFile?: File;
  // for progress bar
  progressInfos: any[] = [];
  // action response
  message: string[] = [];
  // file information 
  fileInfos?: Observable<Fileupload[]>;
    

  constructor(private fileUploadService: FileuploadService) { }

  // holds selected file 
  selectFiles(event: any): void {
    this.message = [];
    this.progressInfos = [];
    this.selectedFiles = event.target.files;
  }

  uploadFiles(): void {
    this.message = [];
  
    if (this.selectedFiles) {
      for (let i = 0; i < this.selectedFiles.length; i++) {
        this.upload(i, this.selectedFiles[i]);
      }
    }
  }

  // file uploading
  upload(idx: number, file: File): void {
    this.progressInfos[idx] = { value: 0, fileName: file.name };

    if (file) {
      this.fileUploadService.upload(file).subscribe(
        (event: any) => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
          } else if (event instanceof HttpResponse) {
            const msg = 'Uploaded the file successfully: ' + file.name;
            this.message.push(msg);
            this.fileInfos = this.fileUploadService.getFiles();
          }
        },
        (err: any) => {
          this.progressInfos[idx].value = 0;
          const msg = 'Could not upload the file: ' + file.name;
          this.message.push(msg);
          this.fileInfos = this.fileUploadService.getFiles();
        });
    }
  }

  // get list of files 
  ngOnInit(): void {
    this.fileInfos = this.fileUploadService.getFiles();
    console.log(this.fileInfos);
  }

}
