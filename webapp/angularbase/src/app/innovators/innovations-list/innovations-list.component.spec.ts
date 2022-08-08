import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InnovationsListComponent } from './innovations-list.component';

describe('InnovationsListComponent', () => {
  let component: InnovationsListComponent;
  let fixture: ComponentFixture<InnovationsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InnovationsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InnovationsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
