import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RevealListComponent } from './reveal-list.component';

describe('RevealListComponent', () => {
  let component: RevealListComponent;
  let fixture: ComponentFixture<RevealListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RevealListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RevealListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
