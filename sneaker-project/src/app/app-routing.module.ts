import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {IndexComponent} from './index/index.component';
import {DetailComponent} from './detail/detail.component';
import {LoginComponent} from './login/login.component';
import {CartComponent} from './cart/cart.component';
import {PaymentComponent} from './payment/payment.component';

const routes: Routes = [
  {path: '', component: IndexComponent},
  {path: 'detail/:id', component: DetailComponent},
  {path: 'login', component: LoginComponent},
  {path: 'cart', component: CartComponent},
  {path: 'checkout', component: PaymentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
