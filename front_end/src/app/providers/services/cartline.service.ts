import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class CartLineService {

    private BASE_URL: string = "http://localhost:8090/cartLine/";

    constructor(private http: HttpClient) { }

}