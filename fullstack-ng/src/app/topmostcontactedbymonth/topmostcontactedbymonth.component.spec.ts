import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopmostcontactedbymonthComponent } from './topmostcontactedbymonth.component';

describe('TopmostcontactedbymonthComponent', () => {
  let component: TopmostcontactedbymonthComponent;
  let fixture: ComponentFixture<TopmostcontactedbymonthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TopmostcontactedbymonthComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TopmostcontactedbymonthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
