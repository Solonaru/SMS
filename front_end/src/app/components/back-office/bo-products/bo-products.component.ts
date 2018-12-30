import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Category } from '../../../entities/category';
import { CategoryService } from 'src/app/providers/services/category.service';

@Component({
  selector: 'app-bo-products',
  templateUrl: './bo-products.component.html',
  styleUrls: ['./bo-products.component.css']
})
export class BoProductsComponent implements OnInit {

  item: Item;
  items: Item[];
  shouldShow: boolean;

  constructor(private itemService: ItemService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.populateItems();
  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; console.log(this.items); });
  }

  onAdd() {
    this.item = new Item();
    this.item.category = new Category();
    this.shouldShow = true;
  }

  onUpdate(item: Item) {
    this.item = item;
    this.shouldShow = true;
  }

  onDelete(item: Item) {
    this.itemService.deleteItem(item).subscribe(data => { location.reload(); });
  }

  onCloseModel() {
    this.shouldShow = false;
  }

  insertItem() {
    /* Insert the address, first of all */
    this.categoryService.insertCategory(this.item.category).subscribe(data => {
      /* Set the newly persisted category */
      this.item.category = data;
      /* Insert the item */
      this.categoryService.insertCategory(this.item.category).subscribe(data => {
        this.shouldShow = false;
        /* Reload page to display newly added employee */
        location.reload();
      });
    });
  }

  updateItem() {
    /* Update the address, first of all */
    this.categoryService.updateCategory(this.item.category).subscribe(data => {
      /* Update the item */
      this.itemService.updateItem(this.item).subscribe(data => {
        /* No need to reload page, due two-way data binding */
        this.shouldShow = false;
      });
    });
  }

}
