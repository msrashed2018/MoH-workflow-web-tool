import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentViewEditComponent } from './document-view-edit.component';

describe('DocumentViewEditComponent', () => {
  let component: DocumentViewEditComponent;
  let fixture: ComponentFixture<DocumentViewEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentViewEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentViewEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
