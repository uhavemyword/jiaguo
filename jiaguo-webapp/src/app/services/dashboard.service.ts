import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import * as moment from 'moment';
import { DashboardModel } from './../models';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/shareReplay';
import * as JwtDecode from 'jwt-decode';

@Injectable()
export class DashboardService {
    baseUrl: string = "/api/dashboard/";

    constructor(private http: HttpClient) { }

    getDemo() {
        return this.http.get<DashboardModel>(`${this.baseUrl}demo`);
    }
}