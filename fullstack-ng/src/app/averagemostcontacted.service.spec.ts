import { TestBed } from '@angular/core/testing';

import { AveragemostcontactedService } from './averagemostcontacted.service';

describe('AveragemostcontactedService', () => {
  let service: AveragemostcontactedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AveragemostcontactedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
