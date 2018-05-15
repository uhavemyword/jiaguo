import { Injectable } from '@angular/core';
import {
    CanActivate, Router,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    CanActivateChild,
    NavigationExtras
} from '@angular/router';
import { AuthService } from './auth.service';
import { environment } from '../../environments/environment';

@Injectable()
export class AuthGuardService implements CanActivate, CanActivateChild {
    constructor(private authService: AuthService, private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let url: string = state.url;

        return this.checkLogin(url);
    }

    canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        return this.canActivate(route, state);
    }

    checkLogin(url: string): boolean {
        //FIXME: remove
        if (!environment.production) {
            return true;
        }

        if (this.authService.isLoggedIn()) {
            return true;
        }

        let navigationExtras: NavigationExtras = {
            queryParams: { returnUrl: url }
        };

        // Navigate to the login page with extras
        this.router.navigate(['/account/login'], navigationExtras);
        return false;
    }
}