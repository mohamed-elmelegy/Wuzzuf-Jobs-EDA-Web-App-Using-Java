import {Component, OnInit} from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-summery',
  templateUrl: './summery.component.html',
  styleUrls: ['./summery.component.scss']
})
export class SummeryComponent implements OnInit{
  constructor(private readonly dataService: DataService) {
  }
  public summery:any;

  ngOnInit() {
    this.getData();
  }

  getData(){
    this.dataService.getSummery().subscribe(next=>{
      this.summery = next;
    })
  }
}

