import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../providers/services/auth.service';
import { Router } from '@angular/router';

import { User } from '../../../entities/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;
  foundUser: User;

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {

  }

  loginUser(event) {
    this.user = new User();

    event.preventDefault();
    const target = event.target;
    this.user.username = target.querySelector('#username').value;
    this.user.password = target.querySelector('#password').value;

    this.auth.login(this.user).subscribe(data => { this.foundUser = data; console.log(data); this.loginCheck(); });
  }

  loginCheck() {
    if (this.foundUser != null) {
      this.router.navigate(['admin']);
      this.auth.setLoggedIn(true);
    } else {
      window.alert("No user found!!");
    }
  }
}
