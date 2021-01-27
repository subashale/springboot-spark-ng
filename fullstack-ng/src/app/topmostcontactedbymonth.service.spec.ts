import { TestBed } from '@angular/core/testing';

import { TopmostcontactedbymonthService } from './topmostcontactedbymonth.service';

describe('TopmostcontactedbymonthService', () => {
  let service: TopmostcontactedbymonthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TopmostcontactedbymonthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
