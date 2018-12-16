import { Component, OnInit } from '@angular/core';

import { CategoryService } from '../../../providers/services/category.service';
import { Category } from '../../../entities/category';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  categories: Category[];

  constructor(private categoryService: CategoryService, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit() {
    this.populateCategories();
  }

  populateCategories() {
    this.categoryService.getCategories().subscribe(data => { this.categories = data });
  }

  onClick(category: Category) {
    /* Load the products page */
    this.router.navigate(['products/', { cat: category.id }]);
  }

  onLogout() {
    localStorage.removeItem('loggedIn');
    this.router.navigate(['login']);
  }

  toggleMenu() {
    document.querySelector(".sidebar-list").classList.toggle("show");
  }

}
