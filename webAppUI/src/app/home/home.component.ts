import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) {
  }

  username: string;

  ngOnInit(): void {
    this.username = localStorage.getItem('username');
    if (!this.username) {
      this.router.navigateByUrl('/login');
    }
  }

  logout(): void {
    localStorage.removeItem('username');
    this.router.navigateByUrl('/login');
  }

}
