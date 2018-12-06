import { Component, OnInit } from '@angular/core';

import { Item } from '../../../entities/item';
import { DataService } from '../../../providers/services/data.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  items: Item[];

  constructor(public dataService: DataService) { 
    
  }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(items => this.items = items);
  }

}
