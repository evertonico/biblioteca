import { TokenService } from './../autenticacao/token.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pessoa, Pessoas } from './pessoa';
import { environment } from 'src/environments/environment';

const API= environment.apiURL;

@Injectable({
  providedIn: 'root'
})
export class GaleriaService {

  constructor(private http:HttpClient, private tokenService:TokenService) { }

  listaDoUsuario(nomeDoUsuario:string):Observable<Pessoas>{

    return this.http.get<Pessoas>(`${API}/${nomeDoUsuario}/photos`);
  }

  buscaPorID(id:number):Observable<Pessoa>{

    return this.http.get<Pessoa>(`${API}/photos/${id}`);
  }
}
