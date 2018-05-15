import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';

import { ProductComponent } from './product.component';
import { ProductRoutingModule } from './product-routing.module';

@NgModule({
  imports: [
    ProductRoutingModule
  ],
  declarations: [ProductComponent]
})
export class ProductModule { }
