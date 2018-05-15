import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { SharedModule } from '../_shared';
import { LoginComponent } from './login.component';
import { AccountRoutingModule } from './account-routing.module';
import { RegisterComponent } from './register.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    AccountRoutingModule
  ],
  declarations: [LoginComponent, RegisterComponent]
})
export class AccountModule { }
