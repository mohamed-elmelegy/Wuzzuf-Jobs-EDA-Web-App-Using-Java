import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllDataComponent } from './all-data/all-data.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import { HttpClientModule } from "@angular/common/http";
import { NavbarComponent } from './navbar/navbar.component';
import { SummeryComponent } from './summary/summery.component';
import { JobsPerCompanyComponent } from './jobs-per-company/jobs-per-company.component';
import { JobPathComponent } from './job-path/job-path.component';
import { PopularJobsDataComponent } from './popular-jobs-data/popular-jobs-data.component';
import { PopularJobsPathComponent } from './popular-jobs-path/popular-jobs-path.component';
import { PopulraAreasDataComponent } from './populra-areas-data/populra-areas-data.component';
import { PopularAreaPathComponent } from './popular-area-path/popular-area-path.component';
import { WantedSkillsDataComponent } from './wanted-skills-data/wanted-skills-data.component';
import { KMeansPathComponent } from './k-means-path/k-means-path.component';

@NgModule({
  declarations: [
    AppComponent,
    AllDataComponent,
    NavbarComponent,
    SummeryComponent,
    JobsPerCompanyComponent,
    JobPathComponent,
    PopularJobsDataComponent,
    PopularJobsPathComponent,
    PopulraAreasDataComponent,
    PopularAreaPathComponent,
    WantedSkillsDataComponent,
    KMeansPathComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
