import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AllDataComponent} from "./all-data/all-data.component";
import {SummeryComponent} from "./summary/summery.component";
import {JobsPerCompanyComponent} from "./jobs-per-company/jobs-per-company.component";
import {JobPathComponent} from "./job-path/job-path.component";
import {PopularJobsDataComponent} from "./popular-jobs-data/popular-jobs-data.component";
import {PopularJobsPathComponent} from "./popular-jobs-path/popular-jobs-path.component";
import {PopulraAreasDataComponent} from "./populra-areas-data/populra-areas-data.component";
import {PopularAreaPathComponent} from "./popular-area-path/popular-area-path.component";
import {WantedSkillsDataComponent} from "./wanted-skills-data/wanted-skills-data.component";
import {KMeansPathComponent} from "./k-means-path/k-means-path.component";

const routes: Routes = [
  {
    path:'',
    redirectTo:'all-data',
    pathMatch:'full'
  },
  {
    path:'all-data',
    component:AllDataComponent
  },
  {
    path:'summary',
    component:SummeryComponent
  },
  {
    path:'jobs',
    component:JobsPerCompanyComponent
  },
  {
    path: 'jobPath',
    component:JobPathComponent
  },
  {
    path:'popularJobs',
    component:PopularJobsDataComponent
  },
  {
    path:'popularJobsPath',
    component:PopularJobsPathComponent
  },
  {
    path:'popularAreasData',
    component:PopulraAreasDataComponent
  },
  {
    path:'popularAreasPath',
    component:PopularAreaPathComponent
  },
  {
    path: 'wantedSkillsData',
    component:WantedSkillsDataComponent
  },
  {
    path: 'KMeansPath',
    component:KMeansPathComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
