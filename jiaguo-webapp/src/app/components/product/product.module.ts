import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { NgBusyModule } from 'ng-busy';

import { ProductRoutingModule } from './product-routing.module';
import { ProductComponent } from './product.component';
import { ProductInfoService } from '../../services';
import { ProductCardComponent } from './product-card.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgBusyModule,
    ProductRoutingModule
  ],
  declarations: [ProductComponent, ProductCardComponent],
  providers: [ProductInfoService]
})
export class ProductModule { }
