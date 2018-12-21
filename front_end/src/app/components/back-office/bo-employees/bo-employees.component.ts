import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../providers/services/employee.service';
import { Employee } from '../../../entities/employee';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Component({
  selector: 'app-bo-employees',
  templateUrl: './bo-employees.component.html',
  styleUrls: ['./bo-employees.component.css']
})
export class BoEmployeesComponent implements OnInit {

  employees: Employee[];
  private BASE_URL = 'http://localhost:8090/employee';

  constructor(private employeeService: EmployeeService, private http: HttpClient) { }

  ngOnInit() {
    this.populateItems();
  }

  populateItems() {
    this.employeeService.getEmployees().subscribe(data => { this.employees = data; console.log(this.employees); });
  }

  removeEmployee ( employee:Employee): Observable<any> {
    return this.http.delete(this.BASE_URL + 'delete/' + employee.id)
    .pipe(
      map(
      (response:any) => {return response}
        )
        );
  }
}


