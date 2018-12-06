import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from '../../entities/employee';

import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private BASE_URL: string = "http://localhost:8090/employee/";

  constructor(private http: HttpClient) { }

  getEmployees() {
    return this.http.get(this.BASE_URL + 'all').pipe(map((res: Employee[]) => { return res }));
  }
  
}