import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class CartLineService {

    private BASE_URL: string = "http://localhost:8090/cartLine/";

    constructor(private http: HttpClient) { }

    getProductsStatisticData(productId: Number) {
        return this.http.get(this.BASE_URL + 'statisticDataDay/' + productId)
            .pipe(map((res: Map<Date, Number>) => { return res }));
    }

    getProductsStatisticDataMonth(productId: Number) {
        return this.http.get(this.BASE_URL + 'statisticDataMonth/' + productId)
            .pipe(map((res: Map<Number, Number>) => { return res }));
    }
}