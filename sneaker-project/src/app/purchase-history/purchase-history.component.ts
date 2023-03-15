import {Component, OnInit} from '@angular/core';
import {TokenService} from '../service/token.service';
import {AppService} from '../app.service';
import {PaymentHistoryDto} from '../dto/PaymentHistoryDto';
import {PaymentDetailDto} from '../dto/PaymentDetailDto';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {
  paymentHistoryList: PaymentHistoryDto[] = [];
  paymentDetailList: PaymentDetailDto[] = [];
  customerName = '';

  constructor(private tokenService: TokenService,
              private appService: AppService) {
    if (this.tokenService.getToken()) {
      this.appService.getCustomerPaymentHistory(this.tokenService.getIdCustomer()).subscribe(data => {
        this.paymentHistoryList = data.content;
        this.customerName = this.paymentHistoryList[0].customerName;
      });
    }
  }

  ngOnInit(): void {
  }

  getDetail(purchaseOrderId: number): void {
    this.appService.getPaymentDetail(purchaseOrderId).subscribe(data => {
      this.paymentDetailList = data.content;
    });
  }
}
