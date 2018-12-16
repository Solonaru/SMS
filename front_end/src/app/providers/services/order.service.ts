import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Order } from '../../entities/order';

@Injectable({
    providedIn: 'root'
})
export class OrderService {

    private BASE_URL: string = "http://localhost:8090/order/";

    constructor(private http: HttpClient) { }

    getOrders() {
        return this.http.get(this.BASE_URL + 'all').pipe(map((res: Order[]) => { return res }));
    }
}