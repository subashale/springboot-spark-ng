import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatadefinationComponent } from './datadefination.component';

describe('DatadefinationComponent', () => {
  let component: DatadefinationComponent;
  let fixture: ComponentFixture<DatadefinationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DatadefinationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DatadefinationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
