import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';

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

    getAverageFromStatisticData(statisticData: Map<Number, Number>) {
        return this.http.post<Number>(this.BASE_URL + 'average', JSON.stringify(statisticData), this.httpOptions).
            pipe(map((resp: any) => { return resp }));
    }

    getMovingAverageForecast(statisticData: Map<Number, Number>, periods: Number) {
        return this.http.post<Number>(this.BASE_URL + 'forecast/movingAverage', { statisticData, periods },
            this.httpOptions).pipe(map((resp: any) => { return resp }));
    }

    getWeightedMovingAverageForecast(statisticData: Map<Number, Number>, periods: Number) {
        return this.http.post<Number>(this.BASE_URL + 'forecast/weightedMovingAverage', { statisticData, periods },
            this.httpOptions).pipe(map((resp: any) => { return resp }));
    }

    getExponentialMovingAverageForecast(statisticData: Map<Number, Number>, periods: Number) {
        return this.http.post<Number>(this.BASE_URL + 'forecast/exponentialMovingAverage', { statisticData, periods },
            this.httpOptions).pipe(map((resp: any) => { return resp }));
    }

    getProductsStatisticDataBasedOnPrice(productId: Number) {
        return this.http.get(this.BASE_URL + 'month/price/' + productId)
            .pipe(map((res: Map<Number, Number>) => { return res }));
    }

}