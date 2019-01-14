import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Catalogue } from '../../entities/catalogue';

@Injectable({
    providedIn: 'root'
})
export class CatalogueService {

    private BASE_URL: string = "http://localhost:8090/catalogue/";

    constructor(private http: HttpClient) { }

    getCatalogues() {
        return this.http.get(this.BASE_URL + 'all').pipe(map((res: Catalogue[]) => { return res }));
    }

    getCatalogueById(catalogueId: Number) {
        return this.http.get(this.BASE_URL + catalogueId)
            .pipe(map((res: Catalogue) => { return res }));
    }
}