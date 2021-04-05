import {TestBed} from '@angular/core/testing';

import {UserService} from './user.service';

describe('UserService', () => {
  let service: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should allow valid username', (done: DoneFn) => {
    service.validateUserCreds('abc@gmail.com', 'abcdefgh').subscribe(() => {
      expect(1).toBe(1);
      done();
    }, () => {
      expect(1).toBe(0);
      done();
    });
  });

  it('should block invalid username', (done: DoneFn) => {
    service.validateUserCreds('dummy@gmail.com', 'abcdefgh').subscribe(() => {
      expect(0).toBe(1);
      done();
    }, () => {
      expect(1).toBe(1);
      done();
    });
  });
});
