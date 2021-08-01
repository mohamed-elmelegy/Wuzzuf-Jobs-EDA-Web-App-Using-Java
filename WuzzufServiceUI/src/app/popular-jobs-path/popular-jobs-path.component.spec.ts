import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopularJobsPathComponent } from './popular-jobs-path.component';

describe('PopularJobsPathComponent', () => {
  let component: PopularJobsPathComponent;
  let fixture: ComponentFixture<PopularJobsPathComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopularJobsPathComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopularJobsPathComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
