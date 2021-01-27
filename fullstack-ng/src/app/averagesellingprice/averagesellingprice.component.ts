import { Component, OnInit } from '@angular/core';
import { AveragesellingpriceService } from '../averagesellingprice.service';

@Component({
  selector: 'app-averagesellingprice',
  templateUrl: './averagesellingprice.component.html',
  styleUrls: ['./averagesellingprice.component.css']
})
export class AveragesellingpriceComponent implements OnInit {
  constructor(private averageSellingPriceService: AveragesellingpriceService) { }
  // result holder with any type
  results: any;

  ngOnInit(): void {
    this.averageSellingPriceService.getResult().subscribe((data: any) => {      
      this.results = data;
      console.log(JSON.stringify(this.results));
    });
  }

}
