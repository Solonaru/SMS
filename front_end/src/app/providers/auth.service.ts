import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private BASE_URL: string = "http://localhost:8080/customer/";
  private loggedInStatus = JSON.parse(localStorage.getItem('loggedIn' || 'false'));

  constructor(private http: HttpClient) { }

  setLoggedIn(value: boolean) {
    this.loggedInStatus = value;
    localStorage.setItem('loggedIn', 'true');
  }

  get isLoggedIn() {
    return JSON.parse(localStorage.getItem('loggedIn' || this.loggedInStatus.toString()));
  }

  login(user: User): Observable<any> {
    return this.http.post(this.BASE_URL + 'login', user)
      .pipe(map((res: any) => { return res }));
  }
}
