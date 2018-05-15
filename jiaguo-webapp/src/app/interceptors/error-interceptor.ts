
import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { DialogService } from '../services';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { ErrorResponse } from '../models';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(private dialogService: DialogService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).catch(res => {
            if (res instanceof HttpErrorResponse) {
                try {
                    let error = JSON.parse(res.error);
                    this.dialogService.error(error.message, 'Error')
                } catch {
                    if (typeof res.message === 'string') {
                        this.dialogService.error(res.message, 'Error')
                    }
                    else if (res.status === 0) {
                        // might be CORS error
                        this.dialogService.error('There was a problem with the request.');
                    }
                }
            }
            return Observable.throw(res);
        });
    }
}