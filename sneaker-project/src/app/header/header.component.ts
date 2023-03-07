import {Component, OnInit} from '@angular/core';
import {TokenService} from '../service/token.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import {ShareService} from '../service/share.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isSignedIn = false;
  userName = '';
  formSearch: FormGroup = new FormGroup({
    key: new FormControl()
  });

  constructor(private tokenService: TokenService,
              private router: Router,
              private shareService: ShareService) {
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isSignedIn = true;
      this.userName = this.tokenService.getName();
    }
  }

  signOut(): void {
    window.localStorage.clear();
    location.href = '/login';
  }

  submitSearch(): void {
    this.shareService.setKey(this.formSearch.value);
  }
}
