import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserService, DialogService } from '../../services';
import { UserModel } from 'app/models';

@Component({
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  busy: Subscription;
  users: UserModel[];

  constructor(
    private userService: UserService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private dialogService: DialogService) { }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.busy = this.userService.getAll().subscribe(u => this.users = u);
  }

  create() {
    this.router.navigate([0], { relativeTo: this.activatedRoute });
  }

  edit(id: number) {
    this.router.navigate([id], { relativeTo: this.activatedRoute });
  }

  delete(id: number) {
    this.dialogService.confirm(
      `Are you sure you want to delete user ${id}?`,
      'Confirm',
      () => {
        this.userService.delete(id).subscribe(res => {
          this.refresh();
        });
      }
    );
  }
}
