import { TestBed } from '@angular/core/testing';

import { DatadefinationService } from './datadefination.service';

describe('DatadefinationService', () => {
  let service: DatadefinationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DatadefinationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
