import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class ProductStatsService {

    private BASE_URL: string = "http://localhost:8090/productStats/";

    constructor(private http: HttpClient) { }

    getProductsStatisticData(productId: Number) {
        return this.http.get(this.BASE_URL + 'day/' + productId)
            .pipe(map((res: Map<Date, Number>) => { return res }));
    }

    getProductsStatisticDataMonth(productId: Number) {
        return this.http.get(this.BASE_URL + 'month/' + productId)
            .pipe(map((res: Map<Number, Number>) => { return res }));
    }

    getCompleteProductsStatisticDataMonth(productId: Number) {
        return this.http.get(this.BASE_URL + 'month/complete/' + productId)
            .pipe(map((res: Map<Number, Number>) => { return res }));
    }

    getProductsStatisticDataBasedOnPrice(productId: Number) {
        return this.http.get(this.BASE_URL + 'month/price/' + productId)
            .pipe(map((res: Map<Number, Number>) => { return res }));
    }

}