import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalhePessoaComponent } from './detalhe-pessoa.component';

describe('DetalhePessoaComponent', () => {
  let component: DetalhePessoaComponent;
  let fixture: ComponentFixture<DetalhePessoaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalhePessoaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalhePessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
