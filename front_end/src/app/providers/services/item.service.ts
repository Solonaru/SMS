import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Item } from '../../entities/item';

import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private BASE_URL: string = "http://localhost:8090/item/";

  constructor(private http: HttpClient) { }

  getItems() {
    return this.http.get(this.BASE_URL + 'all').pipe(map((res: Item[]) => { return res }));
  }
}
