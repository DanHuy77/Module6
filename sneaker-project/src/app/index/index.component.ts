import {Component, OnInit} from '@angular/core';
import {Sneaker} from '../model/Sneaker';
import {AppService} from '../app.service';
import {Image} from '../model/Image';
import {SneakerDto} from '../dto/Sneaker-dto';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  sneakerList: SneakerDto[] = [];

  constructor(private appService: AppService) {
  }

  ngOnInit(): void {
    this.appService.getAllSneaker().subscribe(data => {
      this.sneakerList = data.content;
      console.log(this.sneakerList);
    });
  }

}
