
import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../services';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const authToken = localStorage.getItem("token");

        if (authToken) {
            // Clone the request and replace the original headers with
            // cloned headers, updated with the authorization.
            const authReq = req.clone({
                headers: req.headers.set('Authorization', "Bearer " + authToken)
            });
            return next.handle(authReq);
        }

        // send cloned request with header to the next handler.
        return next.handle(req);
    }
}