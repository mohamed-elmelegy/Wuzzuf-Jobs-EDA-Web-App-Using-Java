import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopularAreaPathComponent } from './popular-area-path.component';

describe('PopularAreaPathComponent', () => {
  let component: PopularAreaPathComponent;
  let fixture: ComponentFixture<PopularAreaPathComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopularAreaPathComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopularAreaPathComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
