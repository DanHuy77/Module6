import {Component, OnInit} from '@angular/core';

import {render} from 'creditcardpayments/creditCardPayments';
import {TokenService} from '../service/token.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  isSignin = false;

  constructor(private tokenService: TokenService) {
    if (tokenService.getToken()) {
      this.isSignin = true;
    }
    render(
      {
        id: '#payPalBtn',
        currency: 'USD',
        value: '10',
        onApprove: (details) => {
          alert('Payment Succeed');
        }
      }
    );
  }

  ngOnInit(): void {
  }

}
