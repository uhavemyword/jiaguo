import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import * as moment from 'moment';
import { LoginModel } from './../models';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/shareReplay';
import * as JwtDecode from 'jwt-decode';

@Injectable()
export class AuthService {
    constructor(private http: HttpClient) { }

    login(loginModel: LoginModel) {
        return this.http.post<any>('/api/account/login', loginModel, { responseType: 'text' as 'json' })
            .do(res => this.setSession(res))
            .shareReplay();
    }

    private setSession(tokenString) {
        const jwtToken = JwtDecode(tokenString);
        const expiresAt = moment('1970-01-01').add(jwtToken.exp, 'second');

        localStorage.setItem('token', tokenString);
        localStorage.setItem('user', jwtToken.user);
        localStorage.setItem('expires', JSON.stringify(expiresAt.valueOf()));
    }

    getToken() {
        return localStorage.getItem('token');
    }

    logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('expires');
        localStorage.removeItem('user');
    }

    isLoggedIn() {
        return moment().isBefore(this.getExpiration());
    }

    isLoggedOut() {
        return !this.isLoggedIn();
    }

    getExpiration() {
        const expiration = localStorage.getItem('expires');
        const expiresAt = JSON.parse(expiration);
        return moment(expiresAt);
    }
}