import { Component, OnInit } from '@angular/core';

import { CategoryService } from '../../../providers/services/category.service';
import { DataService } from '../../../providers/services/data.service';
import { Category } from '../../../entities/category';
import { Item } from '../../../entities/item';
import { Router } from '@angular/router';

//import fontawesome from '@fortawesome/angular-fontawesome';
//import {faChevronLeft, faChevronRight} from '@fortawesome/free-solid-svg-icons';

//fontawesome.library.add(faChevronLeft, faChevronRight);

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[];
  items: Item[];

  constructor(private categoryService: CategoryService, private dataService: DataService, private router: Router) { }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(items => this.items = items);
    this.populateCategories();
  }

  populateCategories() {
    this.categoryService.getCategories().subscribe(data => { this.categories = data; console.log(this.categories); });
  }

  onClick(category: Category) {
    /* Empty array */
    this.items = [];
    /* Check if the selected category has childCategories */
    if (category.childCategories.length != 0) {
      /* Loop childCategories and concat items to the items list */
      category.childCategories.forEach(function (value, key) {
        this.items = this.items.concat(value.items);
      }, this);
    } else {
      this.items = this.items.concat(category.items);
    }

    /* Share data with other components */
    this.newMessage();

    /* Load the products page */
    this.router.navigate(['products']);
  }

  newMessage() {
    this.dataService.changeMessage(this.items);
  }

}
