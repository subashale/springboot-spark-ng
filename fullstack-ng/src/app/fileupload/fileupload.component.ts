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
  progress = 0;
  // action response
  message = '';

  // file information 
  fileInfos?: Observable<Fileupload[]>;

  constructor(private fileUploadService: FileuploadService) { }

  // holds selected file 
  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  // file uploading
  upload(): void {
    this.progress = 0; 

    // if file selected
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      
      if (file) {
        this.currentFile = file;
        // call to upload service
        this.fileUploadService.upload(this.currentFile).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.fileUploadService.getFiles();
            }
          },
          (err: any) => {
            console.log(err);
            this.progress = 0;
  
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
  
            this.currentFile = undefined;
          });
      }
  
      this.selectedFiles = undefined;
    }
  }

  // get list of files 
  ngOnInit(): void {
    this.fileInfos = this.fileUploadService.getFiles();
    console.log(this.fileInfos);
  }

}
