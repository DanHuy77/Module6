import { Component, OnInit } from '@angular/core';
import {SneakerDetailDto} from '../dto/sneaker-detail-dto';
import {AppService} from '../app.service';
import {TokenService} from '../service/token.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
sneakersInCart: SneakerDetailDto[] = [];
accountId = 0;
  constructor(private appService: AppService,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()){
      this.accountId = Number(this.tokenService.getIdAccount());
    }
    this.appService.showCart(this.accountId).subscribe(data => {
      this.sneakersInCart = data.content;
    });
  }

}
