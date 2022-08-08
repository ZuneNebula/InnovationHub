import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InnovationDetailsComponent } from './innovation-details.component';

describe('InnovationDetailsComponent', () => {
  let component: InnovationDetailsComponent;
  let fixture: ComponentFixture<InnovationDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InnovationDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InnovationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
