package com.project.wuzzufjobservice;

import smile.data.DataFrame;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public interface WuzzufDAO {
     DataFrame readCSVData() throws IOException, URISyntaxException;
     DataFrame dataCleanAndFactorize () throws IOException, URISyntaxException;
     ArrayList<WuzzufJob> mappingData () throws IOException, URISyntaxException;
     HashMap<String,String> showSummary() throws IOException, URISyntaxException;
     HashMap<String, Long> companiesSortedByJobs() throws IOException, URISyntaxException;
     HashMap<String,String> companiesSortedByJobsPlot() throws IOException, URISyntaxException;
     HashMap<String, Long> popularJobs() throws IOException, URISyntaxException;
     HashMap<String,String> popularJobsPlot() throws IOException, URISyntaxException;
     HashMap<String, Long> popularAreas() throws IOException, URISyntaxException;
     HashMap<String,String> popularAreasPlot() throws IOException, URISyntaxException;
     HashMap<String, Long> skillsOrderedByOccurances() throws IOException, URISyntaxException;
     HashMap<String,String> kmeansClustringPlot() throws IOException, URISyntaxException, InterruptedException, InvocationTargetException;
}
