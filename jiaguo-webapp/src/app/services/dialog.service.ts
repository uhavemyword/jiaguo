import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import { BsModalService, ModalOptions } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';

import { DialogModel } from '../models';
import { DialogComponent } from '../components/_shared/dialog.component';

@Injectable()
export class DialogService {
    private bsModalRef: BsModalRef;

    constructor(private modalService: BsModalService) { }

    success(message: string, title?: string, onClose?: Function) {
        this.show(new DialogModel(
            message,
            title,
            [{
                text: 'Close',
                action: () => {
                    if (onClose)
                        onClose();
                    this.bsModalRef.hide();
                }
            }]
        ), 'success');
    }

    error(message: string, title?: string, onClose?: Function) {
        this.show(new DialogModel(
            message,
            title,
            [{
                text: 'Close',
                action: () => {
                    if (onClose)
                        onClose();
                    this.bsModalRef.hide();
                }
            }]
        ), 'danger');
    }

    info(message: string, title?: string, onClose?: Function) {
        this.show(new DialogModel(
            message,
            title,
            [{
                text: 'Close',
                action: () => {
                    if (onClose)
                        onClose();
                    this.bsModalRef.hide();
                }
            }]
        ), 'info');
    }

    confirm(message: string, title?: string, onConfirm?: Function, onCancel?: Function) {
        this.show(new DialogModel(
            message,
            title,
            [{
                text: 'Confirm',
                action: () => {
                    if (onConfirm)
                        onConfirm();
                    this.bsModalRef.hide();
                }
            }, {
                text: 'Cancel',
                action: () => {
                    if (onCancel)
                        onCancel();
                    this.bsModalRef.hide();
                }
            }]
        ), 'warning');
    }

    show(model: DialogModel, type: string) {
        this.bsModalRef = this.modalService.show(DialogComponent, { initialState: model, class: 'modal-' + type });
    }

    close() {
        this.bsModalRef.hide();
    }
}