import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WantedSkillsDataComponent } from './wanted-skills-data.component';

describe('WantedSkillsDataComponent', () => {
  let component: WantedSkillsDataComponent;
  let fixture: ComponentFixture<WantedSkillsDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WantedSkillsDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WantedSkillsDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
