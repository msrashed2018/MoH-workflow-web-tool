import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RevealDataComponent } from './reveal-data.component';

describe('RevealDataComponent', () => {
  let component: RevealDataComponent;
  let fixture: ComponentFixture<RevealDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RevealDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RevealDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
