import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindInnovationsComponent } from './find-innovations.component';

describe('FindInnovationsComponent', () => {
  let component: FindInnovationsComponent;
  let fixture: ComponentFixture<FindInnovationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindInnovationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindInnovationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
