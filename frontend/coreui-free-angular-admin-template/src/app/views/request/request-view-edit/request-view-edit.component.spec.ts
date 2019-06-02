import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestViewEditComponent } from './request-view-edit.component';

describe('RequestViewEditComponent', () => {
  let component: RequestViewEditComponent;
  let fixture: ComponentFixture<RequestViewEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequestViewEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestViewEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
