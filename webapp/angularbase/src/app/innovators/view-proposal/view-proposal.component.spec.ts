import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProposalComponent } from './view-proposal.component';

describe('ViewProposalComponent', () => {
  let component: ViewProposalComponent;
  let fixture: ComponentFixture<ViewProposalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewProposalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewProposalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
