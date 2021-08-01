import { Component } from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-all-data',
  templateUrl: './all-data.component.html',
  styleUrls: ['./all-data.component.scss']
})
export class AllDataComponent {

  displayedColumns: string[] = ['title', 'company', 'location', 'type', 'country', 'level', 'skills'];

  constructor(private readonly dataService: DataService) { }

  public allData$ = this.dataService.getAllData();
}
