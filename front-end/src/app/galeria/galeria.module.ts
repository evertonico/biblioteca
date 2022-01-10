import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GaleriaRoutingModule } from './galeria-routing.module';
import { ListaComponent } from './lista/lista.component';


@NgModule({
  declarations: [
    ListaComponent
  ],
  imports: [
    CommonModule,
    GaleriaRoutingModule
  ]
})
export class GaleriaModule { }
