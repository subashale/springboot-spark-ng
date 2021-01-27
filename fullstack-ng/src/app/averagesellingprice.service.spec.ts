import { TestBed } from '@angular/core/testing';

import { AveragesellingpriceService } from './averagesellingprice.service';

describe('AveragesellingpriceService', () => {
  let service: AveragesellingpriceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AveragesellingpriceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
