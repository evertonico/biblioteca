import { Pessoas } from './../pessoa';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-grade-fotos',
  templateUrl: './grade-fotos.component.html',
  styleUrls: ['./grade-fotos.component.css']
})
export class GradeFotosComponent implements OnInit {

  @Input() pessoas !: Pessoas;

  constructor() { }

  ngOnInit(): void {
  }

}
