import { Item } from './item';

export interface Category {
    id: Number;
    name: String;
    updateDate: Date;
    description: String;
    parentCategory: Category;
    childCategories: Category[];
    items: Item[];
}