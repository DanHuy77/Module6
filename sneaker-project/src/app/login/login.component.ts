import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AppService} from '../app.service';
import {dashCaseToCamelCase} from '@angular/compiler/src/util';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Token} from '@angular/compiler';
import {TokenService} from '../service/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formGroup: FormGroup = new FormGroup({
    email: new FormControl(),
    password: new FormControl()
  });
  username: string;
  errorMessage = '';
  roles: string[] = [];
  returnUrl: string;

  constructor(private formBuild: FormBuilder,
              private appService: AppService,
              private router: Router,
              private route: ActivatedRoute,
              private tokenService: TokenService) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.appService.login(this.formGroup.value).subscribe(data => {
      if (data.status === 202) {
        window.localStorage.clear();
        alert('Sai tài khoản');
        this.formGroup.reset();
      } else {
        console.log(data);
        this.tokenService.setIdAccount(data.id);
        if (data.idCustomer !== null) {
          this.tokenService.setIdCustomer(data.idCustomer);
        }
        this.tokenService.setName(data.name);
        this.tokenService.setEmail(data.email);
        this.tokenService.setToken(data.token);
        this.tokenService.setRole(data.roles);
        console.log(data.id);
        alert('Đã đăng nhập');
        this.username = localStorage.getItem('username');
        console.log(this.username);
        location.reload();
        location.href = '/';
      }
    }, error => {

    });
  }
}
