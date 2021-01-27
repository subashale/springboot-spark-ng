import { Component, OnInit } from '@angular/core';
import { AveragemostcontactedService } from '../averagemostcontacted.service';

@Component({
  selector: 'app-averagemostcontacted',
  templateUrl: './averagemostcontacted.component.html',
  styleUrls: ['./averagemostcontacted.component.css']
})
export class AveragemostcontactedComponent implements OnInit {

  constructor(private averageMostContactedService: AveragemostcontactedService) { }
  // result holder
  result: string = "";

  ngOnInit(): void {
    this.averageMostContactedService.getResult().subscribe((data: any) => {      
      this.result = data['Average price'];
      console.log(JSON.stringify(this.result));
    });
  }

}
