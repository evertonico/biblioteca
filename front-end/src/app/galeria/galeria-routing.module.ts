import { DetalhePessoaComponent } from './detalhe-pessoa/detalhe-pessoa.component';
import { ListaComponent } from './lista/lista.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path:'',
    component: ListaComponent,
  },
  {
    path: ':pessoaId',
    component: DetalhePessoaComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GaleriaRoutingModule { }
