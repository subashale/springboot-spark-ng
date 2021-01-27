import { TestBed } from '@angular/core/testing';

import { MakedistributionService } from './makedistribution.service';

describe('MakedistributionService', () => {
  let service: MakedistributionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MakedistributionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
