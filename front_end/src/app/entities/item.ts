import { Category } from './category';

export interface Item {
    id: Number;
    name: String;
    stockQuantity: Number;
    updateDate: Date;
    description: Date;
    category: Category;
    imageUrl: String;
    listed: Boolean;
    price: Number;
}