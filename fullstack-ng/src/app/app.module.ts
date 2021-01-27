import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { FileuploadComponent } from './fileupload/fileupload.component';
import { AveragesellingpriceComponent } from './averagesellingprice/averagesellingprice.component';
import { MakedistributionComponent } from './makedistribution/makedistribution.component';
import { AveragemostcontactedComponent } from './averagemostcontacted/averagemostcontacted.component';
import { DatadefinationComponent } from './datadefination/datadefination.component';
import { TopmostcontactedbymonthComponent } from './topmostcontactedbymonth/topmostcontactedbymonth.component';

@NgModule({
  declarations: [
    AppComponent,
    FileuploadComponent,
    AveragesellingpriceComponent,
    MakedistributionComponent,
    AveragemostcontactedComponent,
    DatadefinationComponent,
    TopmostcontactedbymonthComponent    
  ],
  imports: [
    BrowserModule, 
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
