import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KMeansPathComponent } from './k-means-path.component';

describe('KMeansPathComponent', () => {
  let component: KMeansPathComponent;
  let fixture: ComponentFixture<KMeansPathComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KMeansPathComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KMeansPathComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
