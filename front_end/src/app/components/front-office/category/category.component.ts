import { Component, OnInit } from '@angular/core';

import { CategoryService } from '../../../providers/services/category.service';
import { Category } from '../../../entities/category';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[];

  constructor(private categoryService: CategoryService, private router: Router) { }

  ngOnInit() {
    this.populateCategories();
  }

  populateCategories() {
    this.categoryService.getCategories().subscribe(data => { this.categories = data; console.log(this.categories); });
  }

  onClick(category: Category) {
    /* Load the products page */
    this.router.navigate(['products']);
  }

}
