import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInnovationComponent } from './view-innovation.component';

describe('ViewInnovationComponent', () => {
  let component: ViewInnovationComponent;
  let fixture: ComponentFixture<ViewInnovationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewInnovationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInnovationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
