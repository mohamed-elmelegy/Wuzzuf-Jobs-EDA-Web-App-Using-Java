import { Component, OnInit } from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-popular-area-path',
  templateUrl: './popular-area-path.component.html',
  styleUrls: ['./popular-area-path.component.scss']
})
export class PopularAreaPathComponent implements OnInit {

  constructor(private readonly data: DataService) { }

  public areaPath:any;

  ngOnInit(): void {
    this.data.getPopularAreasPath().subscribe(res=>{
      this.areaPath = res;
    });
  }

}
