import { GaleriaService } from './../galeria.service';
import { UsuarioService } from './../../autenticacao/usuario/usuario.service';
import { Pessoas } from './../pessoa';
import { Component, OnInit } from '@angular/core';
import { switchMap, Observable } from 'rxjs';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  pessoas$ !:  Observable<Pessoas>;

  constructor(private usuarioService:UsuarioService, private galeriaService:GaleriaService) { }

  ngOnInit(): void {

    this.pessoas$ = this.usuarioService.retornaUsuario().pipe(
      switchMap(
        (usuario)=>{
          const userName = usuario.name ?? '';
          return this.galeriaService.listaDoUsuario(userName);
        }
      )
    )


  }

}
