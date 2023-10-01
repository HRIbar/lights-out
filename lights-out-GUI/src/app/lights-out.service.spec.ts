import { TestBed } from '@angular/core/testing';

import { LightsOutService } from './lights-out.service';

describe('LightsOutService', () => {
  let service: LightsOutService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LightsOutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
