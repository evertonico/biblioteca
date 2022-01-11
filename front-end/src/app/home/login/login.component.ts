import { AutenticacaoService } from './../../autenticacao/autenticacao.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FacebookLoginProvider, SocialAuthService, SocialUser } from 'angularx-social-login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario='';
  senha='';
  loginForm!: FormGroup;
  socialUser: SocialUser = new SocialUser;
  isLoggedin: boolean = false;

  constructor(private authService: AutenticacaoService, private router:Router,
    private formBuilder: FormBuilder, 
    private socialAuthService: SocialAuthService) {
      console.log(this.isLoggedin)
    }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });    
    
    this.socialAuthService.authState.subscribe((user) => {
      this.socialUser = user;
      this.isLoggedin = (user != null);
    });
  }

  loginWithFacebook(): void {
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID);
  }

  signOut(): void {
    this.socialAuthService.signOut();
  }

  login(){
    this.authService.autenticar(this.usuario, this.senha).subscribe(() => {
      this.router.navigate(['galeria']);
    },(error) => {
      alert('Usuário ou senha inválido');
      console.log(error);
    }
    );
  }

}
