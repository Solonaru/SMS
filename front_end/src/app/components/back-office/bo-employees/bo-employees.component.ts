import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../providers/services/employee.service';
import { Employee } from '../../../entities/employee';
import { AddressService } from '../../../providers/services/address.service';

@Component({
  selector: 'app-bo-employees',
  templateUrl: './bo-employees.component.html',
  styleUrls: ['./bo-employees.component.css']
})
export class BoEmployeesComponent implements OnInit {

  employees: Employee[];
  employee: Employee;
  shouldShow: boolean;

  constructor(private employeeService: EmployeeService, private addressService: AddressService) { }

  ngOnInit() {
    this.populateEmployees();
  }

  populateEmployees() {
    this.employeeService.getEmployees().subscribe(data => { this.employees = data; console.log(this.employees); });
  }

  onUpdate(employee: Employee) {
    this.employee = employee;
    this.shouldShow = true;
  }

  // TODO: Find solution to initialize an empty employee
  onAdd() {
    this.employee = null;
    this.shouldShow = true;
  }

  onSubmit() {
    /* Update the address first of all */
    this.addressService.updateAddress(this.employee.address).subscribe(data => {
      console.log(data);
    });

    /* Update the employee */
    this.employeeService.updateEmployee(this.employee).subscribe(data => {
      console.log(data);
      this.shouldShow = false;
    });
  }

  onDelete(employee: Employee) {
    this.employeeService.deleteEmployee(employee).subscribe(data => { console.log(data); location.reload(); });
  }

  onCloseModel() {
    this.shouldShow = false;
  }

}
