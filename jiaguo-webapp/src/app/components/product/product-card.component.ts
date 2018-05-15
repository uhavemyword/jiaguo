import { Component, OnInit, Input } from '@angular/core';
import { ProductInfo } from './../../models';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss']
})
export class ProductCardComponent implements OnInit {
  @Input()
  product: ProductInfo;

  constructor() { }

  ngOnInit() {
  }

}
