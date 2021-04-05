import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-validator',
  styleUrls: ['validator.scss'],
  templateUrl: 'validator.html'
})

export class ValidatorComponent {
  @Input('error') error: string;

  isValid(): boolean {
    return (this.error == null) || this.error.length === 0;
  }
}
