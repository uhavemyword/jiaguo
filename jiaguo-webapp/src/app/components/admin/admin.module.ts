import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { NgBusyModule } from 'ng-busy';
import { UsersComponent } from './users.component';
import { AdminRoutingModule } from './admin-routing.module';
import { UserDetailComponent } from './user-detail.component';
import { UserService } from './../../services';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgBusyModule,
    AdminRoutingModule
  ],
  declarations: [UsersComponent, UserDetailComponent],
  providers: [UserService]
})
export class AdminModule { }
