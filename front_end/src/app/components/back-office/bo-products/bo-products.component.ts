import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';

@Component({
  selector: 'app-bo-products',
  templateUrl: './bo-products.component.html',
  styleUrls: ['./bo-products.component.css']
})
export class BoProductsComponent implements OnInit {

  items: Item[];

  constructor(private itemService: ItemService) { }

  ngOnInit() {
    this.populateItems();
  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; console.log(this.items); });
  }

}
