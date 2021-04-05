import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // Only usernames from this list will return sucess
  testUsername = ['abc@gmail.com', 'xyz@gmail.com', 'test@gmail.com'];

  constructor() {
  }

  validateUserCreds(username: string, password: string): Observable<boolean> {
    return new Observable<boolean>((observer) => {
      const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      // These checks are not needed are UI validations is already taking care of this
      if (emailRegex.test(username) && password && password.length >= 6 && this.testUsername.indexOf(username) !== -1) {
        observer.next(true);
        observer.complete();
      }
      else {
        observer.error('Invalid form details');
      }
    });

  }
}
