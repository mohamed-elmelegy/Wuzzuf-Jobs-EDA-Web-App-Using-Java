import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private baseUrl = 'http://localhost:8080/api'
  constructor(private readonly http:HttpClient) { }

  public getAllData():Observable<any> {
    return this.http.get(`${this.baseUrl}/all_data`)
  }

  public getSummery(){
    return this.http.get(`${this.baseUrl}/summary`)
  }

  public getJobsPerCompany(){
    return this.http.get(`${this.baseUrl}/jobs_per_company/data`)
  }

  public getJobPath(){
    return this.http.get(`${this.baseUrl}/jobs_per_company/plot_path`)
  }

  public getPopularJobsData(){
    return this.http.get(`${this.baseUrl}/popular_jobs/data`)
  }

  public getPopularJobsPath(){
    return this.http.get(`${this.baseUrl}/popular_jobs/plot_path`)
  }

  public getPopularAreasData(){
    return this.http.get(`${this.baseUrl}/popular_areas/data`)
  }

  public getPopularAreasPath(){
    return this.http.get(`${this.baseUrl}/popular_areas/plot_path`)
  }

  public getWantedSkillsData(){
    return this.http.get(`${this.baseUrl}/most_wanted_skills/data`)
  }

  public getKMeansPath(){
    return this.http.get(`${this.baseUrl}/kmeans_plot`)
  }
}
