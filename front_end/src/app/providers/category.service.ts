import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Response } from '@angular/http';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { Category } from '../entities/category';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {

    private BASE_URL: string = "http://localhost:8090/category/";

    constructor(private http: HttpClient) { }

    getCategories() {
        return this.http.get(this.BASE_URL + 'all').pipe(map((res: Category[]) => { return res }));
    }

}