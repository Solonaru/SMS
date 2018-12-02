import { Category } from '../entities/category';

export class Item {
    id: Number;
    name: String;
    stockQuantity: Number;
    updateDate: Date;
    description: Date;
    category: Category;
}