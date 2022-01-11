import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuadroComponent } from './quadro.component';



@NgModule({
  declarations: [
    QuadroComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [QuadroComponent],
})
export class QuadroModule { }
