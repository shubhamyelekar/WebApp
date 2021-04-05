import {Component, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  formUsername = '';
  formPassword = '';
  showSpinner = false;
  showError = false;

  @ViewChild('username') usernameField;
  @ViewChild('password') passwordField;


  constructor(private userService: UserService,
              private router: Router) {
  }

  submitForm(): void {
    this.showError = false;
    this.showSpinner = true;
    this.userService.validateUserCreds(this.formUsername, this.formPassword).subscribe(() => {
      this.showSpinner = false;
      localStorage.setItem('username', this.formUsername);
      this.router.navigateByUrl('/home');
    }, () => {
      this.showError = true;
      this.showSpinner = false;
    });
  }


  isValidUserName(fromSubmitBtn: boolean): string {
    if (this.usernameField && this.usernameField.dirty || fromSubmitBtn) {
      const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      if (!emailRegex.test(this.formUsername)) {
        return 'Please enter valid email address';
      }
    }
    return null;
  }

  isValidPassword(fromSubmitBtn: boolean): string {
    if (this.passwordField && this.passwordField.dirty || fromSubmitBtn) {
      if (this.formPassword.length < 6) {
        return 'Password should have atleast 6 characters';
      }
    }
    return null;
  }


  isFormValid(): boolean {
    return this.isValidUserName(true) === null && this.isValidPassword(true) === null;
  }

}
