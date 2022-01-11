import { GaleriaService } from './../galeria.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Pessoa } from '../pessoa';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detalhe-pessoa',
  templateUrl: './detalhe-pessoa.component.html',
  styleUrls: ['./detalhe-pessoa.component.css']
})
export class DetalhePessoaComponent implements OnInit {

  pessoaId!: number;
  pessoa$!: Observable<Pessoa>


  constructor(private galeriaService:GaleriaService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.pessoaId = this.activatedRoute.snapshot.params['pessoaId'];
    this.pessoa$ = this.galeriaService.buscaPorID(this.pessoaId);
  }

}
