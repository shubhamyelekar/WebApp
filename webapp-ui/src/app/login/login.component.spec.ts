import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {ValidatorComponent} from '../validator/validator';
import {ClarityModule} from '@clr/angular';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClarityModule, RouterTestingModule, FormsModule],
      declarations: [LoginComponent, ValidatorComponent],
      providers: []

    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should allow valid user name', () => {
    component.formUsername = 'abc@gmail.com';
    expect(component.isValidUserName(true)).toBe(null);
  });

  it('should block invalid user name', () => {
    component.formUsername = 'test@';
    expect(component.isValidUserName(true)).not.toBe(null);
  });

  it('should allow valid password', () => {
    component.formPassword = '123456';
    expect(component.isValidPassword(true)).toBe(null);
  });

  it('should block invalid user name', () => {
    component.formPassword = '1234';
    expect(component.isValidPassword(true)).not.toBe(null);
  });

  it('should disable login button', () => {
    spyOn(component, 'isValidUserName').and.returnValue('dummy');
    spyOn(component, 'isValidPassword').and.returnValue('dummy');
    expect(component.isFormValid()).toBe(false);
  });

  it('should enable login button', () => {
    spyOn(component, 'isValidUserName').and.returnValue(null);
    spyOn(component, 'isValidPassword').and.returnValue(null);
    expect(component.isFormValid()).toBe(true);
  });
});
