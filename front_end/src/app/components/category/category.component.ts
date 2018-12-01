import { Component, OnInit } from '@angular/core';

import { Category } from '../../entities/category';
import { CategoryService } from '../../providers/category.service';

//import fontawesome from '@fortawesome/angular-fontawesome';
//import {faChevronLeft, faChevronRight} from '@fortawesome/free-solid-svg-icons';


//fontawesome.library.add(faChevronLeft, faChevronRight);

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories : Category[];

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.populateCategories();
  }

  populateCategories(){
    this.categoryService.getCategories().subscribe(data => {this.categories = data; console.log(this.categories); });
  }

}
