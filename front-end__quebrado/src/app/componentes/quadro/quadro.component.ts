import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-quadro',
  templateUrl: './quadro.component.html',
  styleUrls: ['./quadro.component.css']
})
export class QuadroComponent implements OnInit {

  @Input() titulo = '';

  constructor() { }

  ngOnInit(): void {
  }

}
