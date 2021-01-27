import { Component, OnInit } from '@angular/core';
import { MakedistributionService } from '../makedistribution.service';

@Component({
  selector: 'app-makedistribution',
  templateUrl: './makedistribution.component.html',
  styleUrls: ['./makedistribution.component.css']
})
export class MakedistributionComponent implements OnInit {

  constructor(private makeDistributionService: MakedistributionService) { }
  
  results: any;
  
  // request of data
  ngOnInit(): void {
    this.makeDistributionService.getResult().subscribe((data: any) => {      
      this.results = data;
      console.log(JSON.stringify(this.results));
    });
  }

}
