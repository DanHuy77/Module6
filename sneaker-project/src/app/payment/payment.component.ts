import {Component, OnInit} from '@angular/core';

import {render} from 'creditcardpayments/creditCardPayments';
import {TokenService} from '../service/token.service';
import {ShareService} from '../service/share.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  isSignin = false;
  totalPrice = '';
  method = 'Paypal';

  constructor(private tokenService: TokenService,
              private shareService: ShareService) {
    if (tokenService.getToken()) {
      this.isSignin = true;
    }
    render(
      {
        id: '#payPal',
        currency: 'USD',
        value: '100',
        onApprove: (details) => {
          alert('Payment Succeed');
        }
      }
    );
    this.shareService.getTotalPrice().subscribe(data => {
      this.totalPrice = data;
      console.log(data);
    });
  }

  ngOnInit(): void {

  }

  changeMethodToCod(): void {
    this.method = 'Cash';
  }

  changeMethodToPaypal(): void {
    this.method = 'Paypal';
  }
}
