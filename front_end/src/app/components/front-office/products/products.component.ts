import { Component, OnInit } from '@angular/core';

import { Item } from '../../../entities/item';
import { ItemService } from '../../../providers/services/item.service';
import { ActivatedRoute } from '../../../../../node_modules/@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  categoryId: string;
  items: Item[] = [];

  constructor(private itemService: ItemService, private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.categoryId = this.route.snapshot.paramMap.get('cat');
    
    this.populateItems();
  }

  populateItems() {
    this.itemService.getListedItemsByCategoryId(this.categoryId).subscribe(data => { this.items = data; console.log(this.items); });
  }

  counter() {
    return new Array(Math.ceil(this.items.length / 5)).fill(0).map((x, i) => i);
  }

}
