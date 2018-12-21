import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Component({
  selector: 'app-bo-products',
  templateUrl: './bo-products.component.html',
  styleUrls: ['./bo-products.component.css']
})
export class BoProductsComponent implements OnInit {

  items: Item[];
  private BASE_URL = 'http://localhost:8090/item';

  constructor(private itemService: ItemService, private http: HttpClient) { }

  ngOnInit() {
    this.populateItems();
  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; console.log(this.items); });
  }

  removeItem ( items:Item): Observable<any> {
    return this.http.delete(this.BASE_URL + 'delete/' + items.id)
    .pipe(
      map(
      (response:any) => {return response}
        )
        );
  }

}
