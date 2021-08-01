import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobPathComponent } from './job-path.component';

describe('JobPathComponent', () => {
  let component: JobPathComponent;
  let fixture: ComponentFixture<JobPathComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobPathComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JobPathComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
