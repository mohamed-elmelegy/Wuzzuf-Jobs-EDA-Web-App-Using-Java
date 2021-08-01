import { Component, OnInit } from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-populra-areas-data',
  templateUrl: './populra-areas-data.component.html',
  styleUrls: ['./populra-areas-data.component.scss']
})
export class PopulraAreasDataComponent implements OnInit {

  constructor(private readonly dataService:DataService) { }
  public keys : string[] = [];
  public values : string[] = [];
  ngOnInit(): void {
    this.getPopularAreasData();
  }

  getPopularAreasData(){
    this.dataService.getPopularAreasData().subscribe(next=>{
      this.keys = Object.keys(next);
      this.values = Object.values(next)
    })
  }

}
