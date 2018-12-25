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

  deleteEmployee(employee: Employee) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    console.log(this.BASE_URL + 'delete/' + employee.id);
    return this.http.delete(this.BASE_URL + 'delete/' + employee.id, httpOptions)
      .pipe(map((resp : any)=> {return resp}));
  }

}