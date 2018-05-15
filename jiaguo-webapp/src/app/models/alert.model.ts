export class Alert {
    type: AlertType;
    message: string;
    getTypes() {
        switch (this.type) {
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

export enum AlertType {
    Success,
    Error,
    Info,
    Warning
}