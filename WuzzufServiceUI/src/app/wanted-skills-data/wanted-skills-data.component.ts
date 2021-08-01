import { Component, OnInit } from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-wanted-skills-data',
  templateUrl: './wanted-skills-data.component.html',
  styleUrls: ['./wanted-skills-data.component.scss']
})
export class WantedSkillsDataComponent implements OnInit {

  constructor(private readonly dataService:DataService) { }
  public keys : string[] = [];
  public values : string[] = [];
  ngOnInit(): void {
    this.getWantedSkillsData();
  }

  getWantedSkillsData(){
    this.dataService.getWantedSkillsData().subscribe(next=>{
      this.keys = Object.keys(next);
      this.values = Object.values(next)
    })
  }

}
