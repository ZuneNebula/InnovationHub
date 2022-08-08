import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InnovatorsProfileComponent } from './innovators-profile.component';

describe('InnovatorsProfileComponent', () => {
  let component: InnovatorsProfileComponent;
  let fixture: ComponentFixture<InnovatorsProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InnovatorsProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InnovatorsProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
