import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { ProductInfoService } from './../../services';
import { ProductInfo } from './../../models';

@Component({
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  busy: Subscription;
  products: ProductInfo[]

  constructor(private productService: ProductInfoService, private router: Router, ) { }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.busy = this.productService.getRandom(30).subscribe(ps => this.products = ps);
  }

  showMore() {
    this.productService.getRandom(30).subscribe(ps => ps.forEach((p) => this.products.push(p)));
  }
}
