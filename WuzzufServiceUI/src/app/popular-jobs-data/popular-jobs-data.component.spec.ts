import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopularJobsDataComponent } from './popular-jobs-data.component';

describe('PopularJobsDataComponent', () => {
  let component: PopularJobsDataComponent;
  let fixture: ComponentFixture<PopularJobsDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopularJobsDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopularJobsDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
