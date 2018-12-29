import { Component, OnInit } from '@angular/core';

import { Item } from '../../../entities/item';
import { ItemService } from '../../../providers/services/item.service';
import { ActivatedRoute } from '../../../../../node_modules/@angular/router';
import { Category } from '../../../entities/category';
import { CategoryService } from '../../../providers/services/category.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  categoryId: string;
  category: Category;
  items: Item[] = [];
  shouldShow: boolean;
  item: Item;

  constructor(private itemService: ItemService, private categoryService: CategoryService, private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.categoryId = this.route.snapshot.paramMap.get('cat');
    this.populateCategory();
    this.populateItems();
  }

  populateCategory() {
    this.categoryService.getCategoryById(this.categoryId).subscribe(data => { this.category = data });
  }

  populateItems() {
    this.itemService.getListedItemsByCategoryId(this.categoryId).subscribe(data => { this.items = data });
  }

  counter() {
    return new Array(Math.ceil(this.items.length / 5)).fill(0).map((x, i) => i);
  }

  onClick(item: Item) {
    this.item = item;
    this.shouldShow = true;
  }

  onCloseModel() {
    this.shouldShow = false;
  }

}
