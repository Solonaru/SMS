import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class CommonStatsService {

    private BASE_URL: string = "http://localhost:8090/commonStats/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

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

}