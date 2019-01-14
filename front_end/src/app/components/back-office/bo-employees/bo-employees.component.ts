import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../providers/services/employee.service';
import { Employee } from '../../../entities/employee';
import { AddressService } from '../../../providers/services/address.service';
import { Address } from '../../../entities/address';
import { City } from '../../../entities/city';
import { CityService } from '../../../providers/services/city.service';

@Component({
  selector: 'app-bo-employees',
  templateUrl: './bo-employees.component.html',
  styleUrls: ['./bo-employees.component.css']
})
export class BoEmployeesComponent implements OnInit {

  cities: City[];
  selectedCity: Number;

  employees: Employee[];
  employee: Employee;
  shouldShow: boolean;

  constructor(private cityService: CityService, private employeeService: EmployeeService, private addressService: AddressService) { }

  ngOnInit() {
    this.populateCities();
    this.populateEmployees();
  }

  populateCities() {
    this.cityService.getCities().subscribe(data => { this.cities = data; });
  }

  populateEmployees() {
    this.employeeService.getEmployees().subscribe(data => { this.employees = data; });
  }

  onAdd() {
    this.employee = new Employee();
    this.employee.address = new Address();
    this.selectedCity = null;
    this.shouldShow = true;
  }

  onUpdate(employee: Employee) {
    this.employee = employee;
    this.selectedCity = employee.address.city.id;
    this.shouldShow = true;
  }

  onDelete(employee: Employee) {
    this.employeeService.deleteEmployee(employee).subscribe(data => { location.reload(); });
  }

  onSubmit() {
    if (this.employee.id != undefined) {
      this.updateEmployee();
    } else {
      this.insertEmployee();
    }
  }

  onCloseModel() {
    this.shouldShow = false;
  }

  insertEmployee() {
    /* Insert the address, first of all */
    this.cityService.getCityById(this.selectedCity).subscribe(data => {
      this.employee.address.city = data;
      this.addressService.insertAddress(this.employee.address).subscribe(data => {
        /* Set the newly persisted address */
        this.employee.address = data;
        /* Insert the employee */
        this.employeeService.insertEmployee(this.employee).subscribe(data => {
          this.shouldShow = false;
          /* Reload page to display newly added employee */
          location.reload();
        });
      });
    });
  }

  updateEmployee() {
    /* Update the address, first of all */
    this.cityService.getCityById(this.selectedCity).subscribe(data => {
      this.employee.address.city = data;
      this.addressService.updateAddress(this.employee.address).subscribe(data => {
        /* Update the employee */
        this.employeeService.updateEmployee(this.employee).subscribe(data => {
          /* No need to reload page, due two-way data binding */
          this.shouldShow = false;
        });
      });
    });
  }

}
