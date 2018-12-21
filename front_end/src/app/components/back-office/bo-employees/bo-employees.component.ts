import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../providers/services/employee.service';
import { Employee } from '../../../entities/employee';

@Component({
  selector: 'app-bo-employees',
  templateUrl: './bo-employees.component.html',
  styleUrls: ['./bo-employees.component.css']
})
export class BoEmployeesComponent implements OnInit {

  employees: Employee[];

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.populateItems();
  }

  populateItems() {
    this.employeeService.getEmployees().subscribe(data => { this.employees = data; console.log(this.employees); });
  }

}
