import { AutenticacaoService } from './../../autenticacao/autenticacao.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService, SocialUser } from 'angularx-social-login';
import { NovoUsuarioComponent } from '../novo-usuario/novo-usuario.component';
import { NovoUsuarioService } from '../novo-usuario/novo-usuario.service';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { UsuarioExisteService } from '../novo-usuario/usuario-existe.service';
import { NovoUsuario } from '../novo-usuario/novo-usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario='';
  senha='';
  loginForm!: FormGroup;
  socialUser!: SocialUser;
  isLoggedin: boolean = false;

  constructor(private authService: AutenticacaoService, private router:Router,
    private formBuilder: FormBuilder, 
    private socialAuthService: SocialAuthService,
    private http: HttpClient) {
      console.log(this.isLoggedin)
    }

  ngOnInit() {
    this.getLocation()
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

  public signInWithGoogle(): void {
    this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID)

    var novoUsarioService = new NovoUsuarioService(this.http)
    var novoUsuarioComponent = new NovoUsuarioComponent(this.formBuilder,
      novoUsarioService, new UsuarioExisteService(novoUsarioService), this.router)

    const novoUsuario = {
      userName: this.socialUser.email,
      email: this.socialUser.email,
      fullName: this.socialUser.firstName + this.socialUser.lastName,
      password: "1"
    } as NovoUsuario;
    
    novoUsuarioComponent.cadastrarUsuarioPorRedeSocial(novoUsuario)
    this.usuario = this.socialUser.email
    this.senha = "1"
    this.login()
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

  getLocation(): void{
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position)=>{
          const longitude = position.coords.longitude;
          const latitude = position.coords.latitude;
          this.callApi(longitude, latitude);
        });
    } else {
       console.log("No support for geolocation")
    }
  }

  callApi(Longitude: number, Latitude: number){
    //const url = `https://api-adresse.data.gouv.fr/reverse/?lon=${Longitude}&lat=${Latitude}`
    //Call API
    console.log(Longitude, Latitude)
    const url = `https://api.hgbrasil.com/weather?key=6ce85309&lat=${Latitude}&lon=${Longitude}&user_ip=remote`
  }

}
