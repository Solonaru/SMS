import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class CategoryStatsService {

    private BASE_URL: string = "http://localhost:8090/categoryStats/";

    constructor(private http: HttpClient) { }

    getCategoriesStatisticDataMonth(categoryId: Number) {
        return this.http.get(this.BASE_URL + 'month/' + categoryId)
            .pipe(map((res: Map<Number, Number>) => { return res }));
    }

    getCompleteCategoriesStatisticDataMonth(categoryId: Number) {
        return this.http.get(this.BASE_URL + 'month/complete/' + categoryId)
            .pipe(map((res: Map<Number, Number>) => { return res }));
    }

}