import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { DataRequest } from '../../entities/helper-classes/request';

@Injectable({
    providedIn: 'root'
})
export class CategoryStatsService {

    private BASE_URL: string = "http://localhost:8090/categoryStats/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    getCategoriesStatisticDataMonth(categoryId: Number) {
        return this.http.get(this.BASE_URL + 'month/' + categoryId)
            .pipe(map((res: Map<Number, Number>) => { return res }));
    }

    getCompleteCategoriesStatisticDataMonth(categoryId: Number) {
        return this.http.get(this.BASE_URL + 'month/complete/' + categoryId)
            .pipe(map((res: Map<Number, Number>) => { return res }));
    }

    getProductsShareInCategoryStatisticDataByMonth(request: Number) {
        return this.http.get(this.BASE_URL + 'share/' + request)
            .pipe(map((res: Map<String, Number>) => { return res }));
    }

    

    getCategoriesComplexStatisticDataByMonth(dataRequest: DataRequest) {
        return this.http.post<DataRequest>(this.BASE_URL + 'month/complex', dataRequest,
            this.httpOptions).pipe(map((resp: any) => { return resp }));
    }

}