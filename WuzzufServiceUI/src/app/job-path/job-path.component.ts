import {Component, OnInit} from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-job-path',
  templateUrl: './job-path.component.html',
  styleUrls: ['./job-path.component.scss']
})
export class JobPathComponent implements OnInit{

  constructor(private readonly data: DataService) { }

  public jobPath:any;

  ngOnInit(): void {
    this.data.getJobPath().subscribe(res=>{
      this.jobPath = res;
    });
  }
}
