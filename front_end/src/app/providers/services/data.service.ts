import { Injectable } from '@angular/core';
import { Item } from '../../entities/item';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class DataService {

  items: Item[];
  private messageSource = new BehaviorSubject<Item[]>(this.items);
  currentMessage = this.messageSource.asObservable();

  constructor() {
    this.changeMessage(JSON.parse(localStorage.getItem('Array')));
  }

  changeMessage(message: Item[]) {
    /* Used to remember last items, in case of refresh or page change */
    localStorage.setItem('Array', JSON.stringify(message));
    this.messageSource.next(message);
  }

}