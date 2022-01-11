import { RodapeModule } from './componentes/rodape/rodape.module';
import { CabecalhoModule } from './componentes/cabecalho/cabecalho.module';
import { CabecalhoComponent } from './componentes/cabecalho/cabecalho.component';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FacebookLoginProvider, SocialLoginModule, SocialAuthServiceConfig, GoogleLoginProvider } from 'angularx-social-login';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CabecalhoModule,
    RodapeModule,
    SocialLoginModule
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider(
              '1356938731403165'
            )
          },
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '761997916227-8koonf5usqf6ncov6i3h31hrlca7burh.apps.googleusercontent.com'
            )
          }
        ]
      } as SocialAuthServiceConfig,
    }  
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
