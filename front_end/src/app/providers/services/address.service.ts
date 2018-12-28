import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Address } from '../../entities/address';

import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AddressService {

    private BASE_URL: string = "http://localhost:8090/address/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    insertAddress(address: Address) {
        return this.http.post<Address>(this.BASE_URL + 'add', JSON.stringify(address), this.httpOptions)
            .pipe(map((resp: Address) => { return resp }));
    }

    updateAddress(address: Address) {
        return this.http.put<Address>(this.BASE_URL + 'update', JSON.stringify(address), this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }

}