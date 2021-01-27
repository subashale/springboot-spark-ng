import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AveragemostcontactedComponent } from './averagemostcontacted.component';

describe('AveragemostcontactedComponent', () => {
  let component: AveragemostcontactedComponent;
  let fixture: ComponentFixture<AveragemostcontactedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AveragemostcontactedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AveragemostcontactedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
