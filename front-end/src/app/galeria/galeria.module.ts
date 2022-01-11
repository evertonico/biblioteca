import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GaleriaRoutingModule } from './galeria-routing.module';
import { ListaComponent } from './lista/lista.component';
import { CardComponent } from './card/card.component';


@NgModule({
  declarations: [
    ListaComponent,
    CardComponent
  ],
  imports: [
    CommonModule,
    GaleriaRoutingModule
  ]
})
export class GaleriaModule { }
