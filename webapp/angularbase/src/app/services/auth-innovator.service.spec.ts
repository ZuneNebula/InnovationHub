import { TestBed } from '@angular/core/testing';

import { AuthInnovatorService } from './auth-innovator.service';

describe('AuthInnovatorService', () => {
  let service: AuthInnovatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthInnovatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
