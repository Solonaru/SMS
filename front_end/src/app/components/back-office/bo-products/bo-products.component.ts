import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Category } from '../../../entities/category';
import { CategoryService } from '../../../providers/services/category.service';

@Component({
  selector: 'app-bo-products',
  templateUrl: './bo-products.component.html',
  styleUrls: ['./bo-products.component.css']
})
export class BoProductsComponent implements OnInit {

  categories: Category[];
  selectedCategory: Number;

  items: Item[];
  item: Item;
  shouldShow: boolean;

  constructor(private categoryService: CategoryService, private itemService: ItemService) { }

  ngOnInit() {
    this.populateCategories();
    this.populateItems();
  }

  populateCategories() {
    this.categoryService.getCategories().subscribe(data => { this.categories = data; });
  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; });
  }

  onAdd() {
    this.item = new Item();
    this.item.category = new Category();
    this.selectedCategory = null;
    this.shouldShow = true;
  }

  onUpdate(item: Item) {
    this.item = item;
    this.selectedCategory = item.category.id;
    this.shouldShow = true;
  }

  onDelete(item: Item) {
    this.itemService.deleteItem(item).subscribe(data => { location.reload(); });
  }

  onSubmit() {
    if (this.item.id != undefined) {
      this.updateItem();
    } else {
      this.insertItem();
    }
  }

  onCloseModel() {
    this.shouldShow = false;
  }

  insertItem() {
    this.categoryService.getCategoryById(this.selectedCategory).subscribe(data => {
      this.item.category = data;
 
        this.itemService.insertItem(this.item).subscribe(data => {
          this.shouldShow = false;
          /* Reload page to display newly added employee */
          location.reload();
        });
      });
  }

  updateItem() {
    this.categoryService.getCategoryById(this.selectedCategory).subscribe(data => {
      this.item.category = data;
      this.itemService.updateItem(this.item).subscribe(data => {
          this.shouldShow = false;
      });
    });
  }

}
