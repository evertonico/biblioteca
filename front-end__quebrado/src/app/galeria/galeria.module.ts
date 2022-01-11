import { QuadroModule } from './../componentes/quadro/quadro.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GaleriaRoutingModule } from './galeria-routing.module';
import { ListaComponent } from './lista/lista.component';
import { CardComponent } from './card/card.component';
import { GradeFotosComponent } from './grade-fotos/grade-fotos.component';


@NgModule({
  declarations: [
    ListaComponent,
    CardComponent,
    GradeFotosComponent
  ],
  imports: [
    CommonModule,
    GaleriaRoutingModule,
    QuadroModule,
  ]
})
export class GaleriaModule { }
