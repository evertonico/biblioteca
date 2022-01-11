import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GradeFotosComponent } from './grade-fotos.component';

describe('GradeFotosComponent', () => {
  let component: GradeFotosComponent;
  let fixture: ComponentFixture<GradeFotosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GradeFotosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GradeFotosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
