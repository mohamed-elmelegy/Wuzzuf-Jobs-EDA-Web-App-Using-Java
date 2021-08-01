import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopulraAreasDataComponent } from './populra-areas-data.component';

describe('PopulraAreasDataComponent', () => {
  let component: PopulraAreasDataComponent;
  let fixture: ComponentFixture<PopulraAreasDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopulraAreasDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopulraAreasDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
