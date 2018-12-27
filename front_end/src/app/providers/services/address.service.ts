import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Address } from '../../entities/address';

import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AddressService {

    private BASE_URL: string = "http://localhost:8090/address/";

    constructor(private http: HttpClient) { }

    updateAddress(address: Address) {
        const httpOptions = {
            headers: new HttpHeaders({ 'Content-Type': 'application/json' })
        };

        return this.http.put<Address>(this.BASE_URL + 'update', JSON.stringify(address), httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }

}