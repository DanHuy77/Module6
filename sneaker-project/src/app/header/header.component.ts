import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {TokenService} from '../service/token.service';
import {Router} from '@angular/router';
import {ShareService} from '../service/share.service';
import {AppService} from '../app.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class HeaderComponent implements OnInit {
  isSignedIn = false;
  userName = '';
  avatar = '';
  accountId = 0;
  slotQuantity = '';
  isChanged = false;

  constructor(private tokenService: TokenService,
              private router: Router,
              private shareService: ShareService,
              private appService: AppService) {
    if (this.tokenService.getToken()) {
      this.isSignedIn = true;
      this.userName = this.tokenService.getName();
      this.avatar = this.tokenService.getAvatar();
      this.accountId = Number(this.tokenService.getIdAccount());
    }
    this.shareService.getLength().subscribe(cartLength => {
      this.slotQuantity = cartLength;
      console.log(this.slotQuantity);
    });
    this.getSlotQuantity(this.accountId);
  }

  ngOnInit(): void {

  }

  signOut(): void {
    window.localStorage.clear();
    location.href = '/login';
  }

  submitSearch(key: string): void {
    this.shareService.setKey(key);
    this.router.navigateByUrl('/');
  }

  getSlotQuantity(idAccount: number): void{
    this.appService.getSlotQuantity(this.accountId).subscribe(length => {
      this.slotQuantity = length;
      this.shareService.setLength(this.slotQuantity);
    });
  }
}
