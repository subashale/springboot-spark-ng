import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AveragesellingpriceComponent } from './averagesellingprice.component';

describe('AveragesellingpriceComponent', () => {
  let component: AveragesellingpriceComponent;
  let fixture: ComponentFixture<AveragesellingpriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AveragesellingpriceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AveragesellingpriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
