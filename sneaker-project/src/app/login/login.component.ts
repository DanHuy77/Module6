import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AppService} from '../app.service';
import {dashCaseToCamelCase} from '@angular/compiler/src/util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formGroup: FormGroup = new FormGroup({
    username: new FormControl(),
    password: new FormControl()
  });
  username: string;
  errorMessage = '';
  roles: string[] = [];
  returnUrl: string;

  constructor(private formBuild: FormBuilder,
              private appService: AppService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
  }


  onSubmit(): void {
    console.log(this.formGroup.value);
    this.appService.login(this.formGroup.value).subscribe(data => {
      alert('Đã đăng nhập');
      this.router.navigateByUrl('/');
    });
  }
}
