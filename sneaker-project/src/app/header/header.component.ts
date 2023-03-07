import { Component, OnInit } from '@angular/core';
import {TokenService} from '../service/token.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
isSignedIn = false;
userName = '';
  constructor(private tokenService: TokenService,
              private router: Router) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()){
      this.isSignedIn = true;
      this.userName = this.tokenService.getName();
    }
  }

  signOut(): void {
    window.localStorage.clear();
    location.href = '/login';
  }
}
