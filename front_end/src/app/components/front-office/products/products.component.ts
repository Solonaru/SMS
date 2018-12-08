import { Component, OnInit } from '@angular/core';

import { Item } from '../../../entities/item';
import { DataService } from '../../../providers/services/data.service';
import { Category } from '../../../entities/category';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  category: Category;
  items: Item[] = [];

  itemsCount: Number;
  currentCount: any = -1;

  constructor(public dataService: DataService) {

  }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(category => this.category = category);
    this.populateItems();
  }

  populateItems() {
    /* Check if the selected category has childCategories */
    if (this.category.childCategories.length != 0) {
      /* Loop childCategories and concat items to the items list */
      this.category.childCategories.forEach(function (value, key) {
        this.items = this.items.concat(value.items);
      }, this);
    } else {
      this.items = this.items.concat(this.category.items);
    }
  }

}
