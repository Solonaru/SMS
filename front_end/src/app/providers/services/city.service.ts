import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { City } from '../../entities/city';

@Injectable({
    providedIn: 'root'
})
export class CityService {

    private BASE_URL: string = "http://localhost:8090/city/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    getCities() {
        return this.http.get(this.BASE_URL + 'all').pipe(map((res: City[]) => { return res }));
    }

    getCityById(cityId: Number) {
        return this.http.get(this.BASE_URL + cityId)
            .pipe(map((res: City) => { return res }));
    }

    updateCity(city: City) {
        return this.http.put<City>(this.BASE_URL + 'update', JSON.stringify(city), this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }
}