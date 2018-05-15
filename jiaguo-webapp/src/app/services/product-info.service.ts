import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import * as moment from 'moment';
import { ProductInfo } from './../models';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/shareReplay';
import * as JwtDecode from 'jwt-decode';

@Injectable()
export class ProductInfoService {
    baseUrl: string = "/api/productinfo/";

    constructor(private http: HttpClient) { }

    getRandom(count: number) {
        return this.http.get<ProductInfo[]>(`${this.baseUrl}random/${count}`);
    }
}