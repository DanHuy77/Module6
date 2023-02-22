import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Image} from '../model/Image';
import {Sneaker} from '../model/Sneaker';
import {SneakerDetailList} from '../model/Sneaker-detail-list';

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

  constructor(private activatedRoute: ActivatedRoute,
              private appService: AppService) {
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
  }

  ngOnInit(): void {

  }

  changeImage(url: string): void {
    this.url = url;
  }

  chooseSize(size: string, remainQuantity: number): void {
    this.chosenSize = size;
    this.remainQuantity = remainQuantity;
  }
}
