import { Component, OnInit } from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-popular-jobs-path',
  templateUrl: './popular-jobs-path.component.html',
  styleUrls: ['./popular-jobs-path.component.scss']
})
export class PopularJobsPathComponent implements OnInit {

  constructor(private readonly data: DataService) { }

  public jobPath:any;

  ngOnInit(): void {
    this.data.getPopularJobsPath().subscribe(res=>{
      this.jobPath = res;
    });
  }

}
