import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { DialogButton } from '../../models';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html'
})
export class DialogComponent implements OnInit {
  title: string;
  message: string;
  buttons: DialogButton[];

  constructor(public bsModalRef: BsModalRef) { }

  ngOnInit() { }

}
