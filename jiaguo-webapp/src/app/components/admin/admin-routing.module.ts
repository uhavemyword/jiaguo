import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UsersComponent } from './users.component';
import { UserDetailComponent } from './user-detail.component';

const routes: Routes = [
    {
        path: 'users',
        component: UsersComponent,
        data: {
            title: 'Users'
        }
    },
    {
        path: 'users/:id',
        component: UserDetailComponent,
        data: {
            title: 'User Detail'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule { }
