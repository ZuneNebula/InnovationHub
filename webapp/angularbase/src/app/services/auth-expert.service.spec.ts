import { TestBed } from '@angular/core/testing';

import { AuthExpertService } from './auth-expert.service';

describe('AuthExpertService', () => {
  let service: AuthExpertService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthExpertService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
