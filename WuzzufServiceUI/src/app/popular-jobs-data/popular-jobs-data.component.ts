import { Component, OnInit } from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-popular-jobs-data',
  templateUrl: './popular-jobs-data.component.html',
  styleUrls: ['./popular-jobs-data.component.scss']
})
export class PopularJobsDataComponent implements OnInit {

  constructor(private readonly dataService:DataService) { }
  public keys : string[] = [];
  public values : string[] = [];
  ngOnInit(): void {
    this.getPopularJobsData();
  }

  getPopularJobsData(){
    this.dataService.getPopularJobsData().subscribe(next=>{
      this.keys = Object.keys(next);
      this.values = Object.values(next)
    })
  }

}
