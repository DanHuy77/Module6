import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Image} from '../model/Image';
import {Sneaker} from '../model/Sneaker';
import {SneakerDetailList} from '../model/Sneaker-detail-list';
import {TokenService} from '../service/token.service';
import {ShareService} from '../service/share.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  sneakerDetail: Sneaker;
  imageList: Image[] = [];
  url = '';
  sneakerDetailList: SneakerDetailList[] = [];
  chosenSize: string | null;
  remainQuantity = 0;
  sneakerDetailId: number;
  accountId: number;

  constructor(private activatedRoute: ActivatedRoute,
              private appService: AppService,
              private tokenService: TokenService,
              private shareService: ShareService) {
    this.activatedRoute.paramMap.subscribe(data => {
      const id = data.get('id');
      if (id != null) {
        this.appService.getSneakerDetail(Number(id)).subscribe(sneakerDetail => {
          this.sneakerDetail = sneakerDetail;
          this.sneakerDetailList = sneakerDetail.sneakerDetailList;
        });
      }
      this.appService.getImageListById(Number(id)).subscribe(image => {
        this.imageList = image;
        this.url = this.imageList[0].url;
      });
    });
    if (this.tokenService.getToken()) {
      this.accountId = Number(this.tokenService.getIdAccount());
    }
  }

  ngOnInit(): void {

  }

  changeImage(url: string): void {
    this.url = url;
  }

  chooseSize(id: number, size: string, remainQuantity: number): void {
    this.chosenSize = size;
    this.remainQuantity = remainQuantity;
    this.sneakerDetailId = id;
  }

  addToCart(): void {
    this.appService.addToCart(this.sneakerDetailId, this.accountId).subscribe(data => {
      console.log(this.sneakerDetailId);
      alert('Đã thêm vào giỏ hàng');
    }, error => {
      if (error.status === 400) {
        alert('Vui lòng chọn size giày');
      }
    });
  }
}
