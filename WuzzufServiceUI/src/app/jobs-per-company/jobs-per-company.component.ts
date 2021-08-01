import { Component, OnInit } from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-jobs-per-company',
  templateUrl: './jobs-per-company.component.html',
  styleUrls: ['./jobs-per-company.component.scss']
})
export class JobsPerCompanyComponent implements OnInit {

  constructor(private readonly dataService:DataService) { }
  public keys : string[] = [];
  public values : string[] = [];
  ngOnInit(): void {
    this.getJobs();
  }

  getJobs(){
    this.dataService.getJobsPerCompany().subscribe(next=>{
      this.keys = Object.keys(next);
      this.values = Object.values(next)
    })
  }
}
