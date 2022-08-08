import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InnovatorsComponent } from './innovators.component';

describe('InnovatorsComponent', () => {
  let component: InnovatorsComponent;
  let fixture: ComponentFixture<InnovatorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InnovatorsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InnovatorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
