import { Item } from './item';

export class Category {
    id: Number;
    name: String;
    updateDate: Date;
    description: Blob;
    parentCategory: Category;
    childCategories: Category[];
    items: Item[];
}
