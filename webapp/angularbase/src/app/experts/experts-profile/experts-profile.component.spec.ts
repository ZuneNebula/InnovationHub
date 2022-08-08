import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpertsProfileComponent } from './experts-profile.component';

describe('ExpertsProfileComponent', () => {
  let component: ExpertsProfileComponent;
  let fixture: ComponentFixture<ExpertsProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExpertsProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpertsProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
