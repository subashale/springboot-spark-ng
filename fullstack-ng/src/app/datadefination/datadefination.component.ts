import { Component, OnInit } from '@angular/core';
import { DatadefinationService } from '../datadefination.service';

@Component({
  selector: 'app-datadefination',
  templateUrl: './datadefination.component.html',
  styleUrls: ['./datadefination.component.css']
})
export class DatadefinationComponent implements OnInit {

  constructor(private dataDefinationService: DatadefinationService) { }
  // result type with empty list
  results: any[] = [];

  ngOnInit(): void {
    this.dataDefinationService.getResult().subscribe((data: any) => {      
      this.results = data;
      console.log(JSON.stringify(this.results));
    });
  }

}
