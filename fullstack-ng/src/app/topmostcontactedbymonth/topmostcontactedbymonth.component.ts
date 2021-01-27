import { Component, OnInit } from '@angular/core';
import { TopmostcontactedbymonthService } from '../topmostcontactedbymonth.service';

@Component({
  selector: 'app-topmostcontactedbymonth',
  templateUrl: './topmostcontactedbymonth.component.html',
  styleUrls: ['./topmostcontactedbymonth.component.css']
})
export class TopmostcontactedbymonthComponent implements OnInit {

  constructor(private topMostContactedByMonth: TopmostcontactedbymonthService) { }
  // data store
  results: any[][] = [];

  ngOnInit(): void {
    this.topMostContactedByMonth.getResult().subscribe((data: any) => {      
      this.results = data;
      console.log(JSON.stringify(this.results));
    });
  }

}
