import { Item } from './item';

export class Category {
    id: Number;
    name: String;
    updateDate: Date;
    parentCategory: Category;
    childCategories: Category[];
    items: Item[];
}