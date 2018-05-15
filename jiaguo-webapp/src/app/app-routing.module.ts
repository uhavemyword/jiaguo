import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuardService } from './services'

// Import Containers
import {
  FullLayoutComponent,
  SimpleLayoutComponent
} from './components/_layouts';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: '',
    component: FullLayoutComponent,
    canActivate: [AuthGuardService],
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'dashboard',
        canActivateChild: [AuthGuardService],
        loadChildren: './components/dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'admin',
        canActivateChild: [AuthGuardService],
        loadChildren: './components/admin/admin.module#AdminModule'
      }
    ]
  },
  {
    path: 'account',
    component: SimpleLayoutComponent,
    data: {
      title: 'Account'
    },
    children: [
      {
        path: '',
        loadChildren: './components/account/account.module#AccountModule',
      }
    ]
  },
  {
    path: 'pages',
    component: SimpleLayoutComponent,
    data: {
      title: 'Pages'
    },
    children: [
      {
        path: '',
        loadChildren: './components/pages/pages.module#PagesModule',
      }
    ]
  },
  // {
  //   path: '**',
  //   redirectTo: 'pages/404'
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
