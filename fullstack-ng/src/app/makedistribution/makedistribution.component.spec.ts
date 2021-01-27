import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakedistributionComponent } from './makedistribution.component';

describe('MakedistributionComponent', () => {
  let component: MakedistributionComponent;
  let fixture: ComponentFixture<MakedistributionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakedistributionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakedistributionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
