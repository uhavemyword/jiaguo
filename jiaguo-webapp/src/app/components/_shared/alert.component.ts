import { Component, OnInit } from '@angular/core';

import { Alert, AlertType } from '../../models';
import { AlertService } from '../../services';

@Component({
    selector: 'app-alert',
    templateUrl: 'alert.component.html'
})
export class AlertComponent {
    alerts: Alert[] = [];
    dismissible = true;

    constructor(private alertService: AlertService) { }

    ngOnInit() {
        this.alertService.getAlert().subscribe((alert: Alert) => {
            if (!alert) {
                // clear alerts when an empty alert is received
                this.alerts = [];
                return;
            }

            // add alert to array
            this.alerts.push(alert);
        });
    }

    getType(alert: Alert) {
        if (!alert) {
            return;
        }

        switch (alert.type) {
            case AlertType.Success:
                return 'success';
            case AlertType.Error:
                return 'danger';
            case AlertType.Info:
                return 'info';
            case AlertType.Warning:
                return 'warning';
        }
    }
}