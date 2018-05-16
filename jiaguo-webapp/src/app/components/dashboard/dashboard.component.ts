import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardService } from './../../services';
import { DashboardModel } from './../../models';

@Component({
  templateUrl: 'dashboard.component.html'
})
export class DashboardComponent {
  model: DashboardModel = new DashboardModel();
  date: Date = new Date();
  timer: any;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    this.refresh();
    this.timer = setInterval(() => this.date = new Date(), 1234);
  }

  refresh() {
    this.dashboardService.getDemo().subscribe(m => this.model = m);
  }

  ngOnDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  }
}
