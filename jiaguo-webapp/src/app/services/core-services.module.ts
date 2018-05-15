import { ModuleWithProviders, NgModule, Optional, SkipSelf } from '@angular/core';
import { AuthService } from './auth.service';
import { AuthGuardService } from './auth-guard.service';
import { AlertService } from './alert.service';
import { DialogService } from './dialog.service';
import { UserService } from './user.service';

@NgModule()
export class CoreServicesModule {
    constructor(@Optional() @SkipSelf() parentModule: CoreServicesModule) {
        if (parentModule) {
            throw new Error(
                'CoreServicesModule is already loaded. Import it in the AppModule only');
        }
    }

    static forRoot(): ModuleWithProviders {
        return {
            ngModule: CoreServicesModule,
            providers: [
                AuthService,
                AuthGuardService,
                AlertService,
                DialogService,
                UserService
            ]
        };
    }
}
