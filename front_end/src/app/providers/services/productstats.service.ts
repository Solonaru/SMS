import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { DataRequest } from '../../entities/helper-classes/request';

@Injectable({
    providedIn: 'root'
})
export class ProductStatsService {

    private BASE_URL: string = "http://localhost:8090/productStats/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

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



    getProductsComplexStatisticDataByMonth(dataRequest: DataRequest) {
        return this.http.post<DataRequest>(this.BASE_URL + 'month/complex', dataRequest,
            this.httpOptions).pipe(map((resp: any) => { return resp }));
    }

}