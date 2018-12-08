import { Injectable } from '@angular/core';
import { Item } from '../../entities/item';
import { BehaviorSubject } from 'rxjs';
import { Category } from '../../entities/category';

@Injectable()
export class DataService {
  
  category: Category;
  private messageSource = new BehaviorSubject<Category>(this.category);
  currentMessage = this.messageSource.asObservable();

  constructor() {
    this.changeMessage(JSON.parse(localStorage.getItem('Category')));
  }

  changeMessage(message: Category) {
    /* Used to remember last items, in case of refresh or page change */
    localStorage.setItem('Category', JSON.stringify(message));
    this.messageSource.next(message);
  }

}