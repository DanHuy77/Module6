import {Component, OnInit} from '@angular/core';
import {SneakerDetailDto} from '../dto/sneaker-detail-dto';
import {AppService} from '../app.service';
import {TokenService} from '../service/token.service';
import {ShareService} from '../service/share.service';
import {Router} from '@angular/router';
import {render} from 'creditcardpayments/creditCardPayments';
import {Customer} from '../model/customer';
import {Title} from '@angular/platform-browser';
import {PurchaseOrder} from '../model/PurchaseOrder';
import {PurchaseOrderDetail} from '../model/PurchaseOrderDetail';
import {ToastrService} from 'ngx-toastr';

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
  isSignedIn = false;
  customerId = 0;
  customer: Customer = {};
  payPalValue = '';
  isClicked = false;
  deliveryAddress = '';
  deliveryPhone = '';
  length = 0;
  detailId = 0;
  size = '';
  sneakerName = '';

  constructor(private appService: AppService,
              private tokenService: TokenService,
              private shareService: ShareService,
              private router: Router,
              private title: Title,
              private toast: ToastrService) {
    if (this.tokenService.getToken()) {
      this.appService.getCustomerInfo(this.tokenService.getIdCustomer()).subscribe(data => {
        this.customer = data;
        console.log(data);
      });
    }
    title.setTitle('Cart and Payment');
  }

  ngOnInit(): void {
    this.getCart();
  }

  getCart(): void {
    if (this.isClicked === true) {
      location.reload();
    }
    if (this.tokenService.getToken()) {
      this.accountId = Number(this.tokenService.getIdAccount());
      this.isSignedIn = true;
    }
    this.cartSubtotal = 0;
    this.appService.showCart(this.accountId).subscribe(data => {
      this.sneakersInCart = data.content;
      this.sneakersInCart[0].deliveryAddress = this.deliveryAddress;
      this.sneakersInCart[0].deliveryPhoneNumber = this.deliveryPhone;
      this.sneakersInCart[0].customer = this.customer;
      if (this.sneakersInCart.length === 0) {
        this.isCartEmpty = true;
      }
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < this.sneakersInCart.length; i++) {
        this.sneakersInCart[i].subTotal = this.sneakersInCart[i].price * this.sneakersInCart[i].inCartQuantity;
        this.cartSubtotal += this.sneakersInCart[i].subTotal;
        this.payPalValue = this.cartSubtotal.toString();

      }
      this.sneakersInCart[0].totalValue = this.cartSubtotal;
    }, error => {
      if (error.status === 400) {
        this.isCartEmpty = true;
      }
    });
  }

  changeQuantity(newQuantity: number, detailId: number): void {
    this.appService.changeInCartQuantity(detailId, newQuantity).subscribe(data => {
      this.ngOnInit();
    }, error => {
      this.toast.info('Invalid amount, please try again.');
      this.ngOnInit();
    });
  }

  removeItemFromCart(): void {
    this.appService.removeItemFromCart(this.detailId).subscribe(data => {
      this.appService.getSlotQuantity(this.accountId).subscribe(length => {
        this.length = length;
        this.shareService.setLength(this.length.toString());
      });
      this.getCart();
      this.toast.success('Removed item ' + this.sneakerName + ' with size ' + this.size + '.');
    });
  }

  changeMethodToCod(): void {
    this.method = 'Cash';
  }

  changeMethodToPaypal(payPalValue: number, sneakerInCart: any): void {
    this.isClicked = true;
    this.method = 'Paypal';
    // this.payPalValue = payPalValue.toString();
    console.log(this.payPalValue);
    render(
      {
        id: '#payPal',
        currency: 'USD',
        value: this.payPalValue,
        onApprove: (details) => {
          this.appService.checkout(this.sneakersInCart).subscribe(data => {
            this.appService.getSlotQuantity(this.accountId).subscribe(length => {
              this.length = length;
              this.shareService.setLength(this.length.toString());
            });
            this.getCart();
            this.toast.success('Payment Succeed.');
          });
        }
      }
    );
  }

  changeMethodToCod2(payPalValue: number): void {
    this.method = 'Paypal';
    this.payPalValue = payPalValue.toString();
  }

  setDeliveryInfo(address: string, phone: string): void {
    this.deliveryAddress = address;
    this.deliveryPhone = phone;
    this.getCart();
  }

  passRemoveInfo(detailId: number, sneakerName: string, size: string): void {
    this.detailId = detailId;
    this.size = size;
    this.sneakerName = sneakerName;
  }

  checkoutCOD(): void {
    this.appService.checkout(this.sneakersInCart).subscribe(data => {
      this.appService.getSlotQuantity(this.accountId).subscribe(length => {
        this.length = length;
        this.shareService.setLength(this.length.toString());
      });
      this.getCart();
      this.toast.success('Payment Succeed.');
    });
  }
}
