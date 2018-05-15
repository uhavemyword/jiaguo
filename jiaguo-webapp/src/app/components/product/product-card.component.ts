import { Component, OnInit, Input } from '@angular/core';
import { ProductInfo } from './../../models';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss']
})
export class ProductCardComponent implements OnInit {
  @Input()
  info: ProductInfo;

  getImgSrc(): string {
    return `${this.info.product.productMediaInfoList[0].mediaUrl}@400w_300h_80Q_1l`;
  }

  getProductLink(): string {
    return `https://www.zhenguo.com/housing/${this.info.product.productId}`;
  }

  constructor() { }

  ngOnInit() {
  }

}
