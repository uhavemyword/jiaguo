import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html'
})
export class AppHeaderComponent {
  username: string;

  ngOnInit() {
    this.username = localStorage.getItem('user');
  }
}
