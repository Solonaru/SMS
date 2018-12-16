import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

import { map } from 'rxjs/operators';

import { Category } from '../../entities/category';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {

    private BASE_URL: string = "http://localhost:8090/category/";

    constructor(private http: HttpClient) { }

    getCategories() {
        return this.http.get(this.BASE_URL + 'all').pipe(map((res: Category[]) => { return res }));
    }

    getCategoryById(categoryId: string) {
        return this.http.get(this.BASE_URL + categoryId)
            .pipe(map((res: Category) => { return res }));
    }

}