export class DialogModel {
    constructor(public message: string, public title?: string, public buttons?: DialogButton[]) { };
}

export interface DialogButton {
    text: string;
    action: Action;
}

interface Action {
    (): void;
}
