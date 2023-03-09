import {Component, OnInit} from '@angular/core';
import {SneakerDetailDto} from '../dto/sneaker-detail-dto';
import {AppService} from '../app.service';
import {TokenService} from '../service/token.service';
import {ShareService} from '../service/share.service';
import {Router} from '@angular/router';
import {render} from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  sneakersInCart: SneakerDetailDto[] = [];
  accountId = 0;
  cartSubtotal = 0;
  isCartEmpty = false;
  method = 'Paypal';

  constructor(private appService: AppService,
              private tokenService: TokenService,
              private shareService: ShareService,
              private router: Router) {
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.accountId = Number(this.tokenService.getIdAccount());
    }
    this.cartSubtotal = 0;
    this.appService.showCart(this.accountId).subscribe(data => {
      this.sneakersInCart = data.content;
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < this.sneakersInCart.length; i++) {
        this.sneakersInCart[i].subTotal = this.sneakersInCart[i].price * this.sneakersInCart[i].inCartQuantity;
        this.cartSubtotal += this.sneakersInCart[i].subTotal;
        // this.shareService.setTotalPrice(this.cartSubtotal);
      }
      render(
        {
          id: '#payPal',
          currency: 'USD',
          value: this.cartSubtotal.toString(),
          onApprove: (details) => {
            alert('Payment Succeed');
          }
        }
      );
    }, error => {
      if (error.status === 400) {
        this.isCartEmpty = true;
      }
    });
  }

  changeQuantity(newQuantity: number, detailId: number): void {
    console.log(newQuantity);
    console.log(detailId);
    this.appService.changeInCartQuantity(detailId, newQuantity).subscribe(data => {
      this.ngOnInit();
    }, error => {
      alert('Số lượng không hợp lệ hoặc vượt quá số lượng giày trong kho, xin thử lại');
      this.ngOnInit();
    });
  }

  removeItemFromCart(detailId: number, sneakerName: string, size: number): void {
    this.appService.removeItemFromCart(detailId).subscribe(data => {
      this.ngOnInit();
      alert('Removed item ' + sneakerName + ' with size ' + size);
    });
  }

  changeMethodToCod(): void {
    this.method = 'Cash';
  }

  changeMethodToPaypal(): void {
    this.method = 'Paypal';
  }
}
