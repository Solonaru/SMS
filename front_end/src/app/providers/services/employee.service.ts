import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  updateEmployee(employee: Employee) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    return this.http.put<Employee>(this.BASE_URL + 'update', JSON.stringify(employee), httpOptions)
      .pipe(map((resp: any) => { return resp }));
  }

  deleteEmployee(employee: Employee) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    return this.http.delete(this.BASE_URL + 'delete/' + employee.id, httpOptions)
      .pipe(map((resp: any) => { return resp }));
  }

}