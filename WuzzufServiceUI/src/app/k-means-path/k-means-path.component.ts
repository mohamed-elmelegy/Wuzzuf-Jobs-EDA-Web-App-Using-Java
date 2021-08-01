import { Component, OnInit } from '@angular/core';
import {DataService} from "../data-access/data.service";

@Component({
  selector: 'app-k-means-path',
  templateUrl: './k-means-path.component.html',
  styleUrls: ['./k-means-path.component.scss']
})
export class KMeansPathComponent implements OnInit {

  constructor(private readonly data: DataService) { }

  public kmeansPath:any;

  ngOnInit(): void {
    this.data.getKMeansPath().subscribe(res=>{
      this.kmeansPath = res;
    });
  }

}
