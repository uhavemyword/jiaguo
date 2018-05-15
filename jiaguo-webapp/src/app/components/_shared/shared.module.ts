import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlertModule } from 'ngx-bootstrap/alert';
import { AlertComponent } from './alert.component';
import { DialogComponent } from './dialog.component';

@NgModule({
    imports: [
        CommonModule, AlertModule
    ],
    declarations: [AlertComponent, DialogComponent],
    exports: [AlertComponent, DialogComponent]
})
export class SharedModule { }
