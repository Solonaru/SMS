import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Category } from '../../../entities/category';

@Component({
  selector: 'app-bo-products',
  templateUrl: './bo-products.component.html',
  styleUrls: ['./bo-products.component.css']
})
export class BoProductsComponent implements OnInit {

  item: Item;
  items: Item[];
  shouldShow: boolean;

  constructor(private itemService: ItemService) { }

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

  onCloseModel() {
    this.shouldShow = false;
  }

}
