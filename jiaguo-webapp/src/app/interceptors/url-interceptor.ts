
import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../services';
import { environment } from '../../environments/environment';

@Injectable()
export class UrlInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.url.startsWith('/api/')) {
            req = req.clone({
                url: environment.apiBaseUrl + req.url
            });
        }
        return next.handle(req);
    }
}