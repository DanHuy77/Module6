import {Component, OnInit} from '@angular/core';
import {Sneaker} from '../model/Sneaker';
import {AppService} from '../app.service';
import {Image} from '../model/Image';
import {SneakerDto} from '../dto/Sneaker-dto';
import {ShareService} from '../service/share.service';
import {TokenService} from '../service/token.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  sneakerList: SneakerDto[] = [];
  keySearch = '';
  isSignedIn = false;

  constructor(private appService: AppService,
              private shareService: ShareService,
              private tokenService: TokenService,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.shareService.getKey().subscribe(data => {
      this.keySearch = data;
      this.getSneaker(this.keySearch);
    });
  }

  getSneaker(key: string): void {
    this.appService.searchSneakerByKey(key).subscribe(sneakers => {
      this.sneakerList = sneakers.content;
    });
  }
}
