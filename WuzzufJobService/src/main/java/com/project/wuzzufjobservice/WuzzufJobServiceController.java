package com.project.wuzzufjobservice;

import org.apache.commons.csv.CSVFormat;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import smile.clustering.KMeans;
import smile.data.DataFrame;
import smile.data.Tuple;
import smile.data.measure.NominalScale;
import smile.data.vector.IntVector;
import smile.io.Read;
import smile.plot.swing.*;
import smile.plot.swing.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class WuzzufJobServiceController implements WuzzufDAO {

    @Override
    public DataFrame readCSVData() throws IOException, URISyntaxException {
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader ();
        return Read.csv ("src/main/resources/datasets/Wuzzuf_Jobs.csv", format);
    }

    @Override
    public DataFrame dataCleanAndFactorize() throws IOException, URISyntaxException {
        DataFrame dataFrame = readCSVData();
        dataFrame = dataFrame.omitNullRows();
        List<Tuple> dataFiltered = dataFrame.stream().distinct().collect(Collectors.toList());
        dataFrame = DataFrame.of(dataFiltered);
        String[] yearsExp = dataFrame.stringVector("YearsExp").toStringArray();
        ArrayList<Integer> temp = new ArrayList<>();
        for (String row : yearsExp){
            String splitted = row.split(" Yrs of Exp")[0].split("[+\\-]")[0];
            if (!splitted.equals("null")) temp.add(Integer.parseInt(splitted));
            else temp.add(0);
        }
        int[] factorized = new int[temp.size()];
        for (int i = 0; i < factorized.length; i++) {
            factorized[i]= temp.get(i);
        }
        dataFrame = dataFrame.merge(IntVector.of("MinYearsExp",factorized));
        dataFrame = dataFrame.select("Title","Company","Location","Type","Level","MinYearsExp","Country","Skills");
        return dataFrame;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/all_data",produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<WuzzufJob> mappingData() throws IOException, URISyntaxException {
        DataFrame dataFrame = dataCleanAndFactorize();
        ArrayList<WuzzufJob> allData = new ArrayList<>();
        for (Tuple t : dataFrame.stream().collect(Collectors.toList())) {
            WuzzufJob wuzzufJob = new WuzzufJob(
                    (String) t.get("Title"),
                    (String) t.get("Company"),
                    (String) t.get("Location"),
                    (String) t.get("Type"),
                    (String) t.get("Level"),
                    (int) t.get("MinYearsExp"),
                    (String) t.get("Country"),
                    (String) t.get("Skills")
            );
            allData.add(wuzzufJob);
        }
        return allData;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/summary",produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String,String> showSummary() throws IOException, URISyntaxException {
        ArrayList<WuzzufJob> arrayList = mappingData();
        HashMap<String,String> output = new HashMap<>();
        output.put("header","The First five Rows of the Data are : ");
        int i =0;
        for(WuzzufJob wuzzufJob : arrayList) {
            String summary = wuzzufJob.getTitle() + " | "
                    + wuzzufJob.getCompany() + " | "
                    + wuzzufJob.getLocation() + " | "
                    + wuzzufJob.getType() + " | "
                    + wuzzufJob.getLevel() + " | "
                    + wuzzufJob.getMinYearsExp() + " | "
                    + wuzzufJob.getCountry() + " | "
                    + wuzzufJob.getSkills();
            i += 1;
            output.put("line" + i, summary);
            if (i == 5) break;
        }
        output.put("footer1","The Data Has "+arrayList.size()+" Records");
        output.put("footer2","The Data Has 8 Columns");
        return output;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/jobs_per_company/data",produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Long> companiesSortedByJobs() throws IOException, URISyntaxException {
        ArrayList<WuzzufJob> arrayList = mappingData();
        HashMap<String, Long> hashMap = (HashMap<String, Long>) arrayList.stream()
                .collect(Collectors.groupingBy(WuzzufJob::getCompany, Collectors.counting()));
        hashMap = hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return hashMap;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/jobs_per_company/plot_path",produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String,String> companiesSortedByJobsPlot() throws IOException, URISyntaxException {
        HashMap<String, Long> hashMap = companiesSortedByJobs();
        String fileName = "jobs_per_company.png";
        String path = "C:/Users/TAREK/WebstormProjects/WuzzufServiceUI/src/assets/charts/"+fileName;
        PieChart chart = new PieChartBuilder().width (1920).height (1080)
                .title ("Top 10 Companies' Jobs")
                .theme(Styler.ChartTheme.XChart)
                .build ();
        Color[] colors = new Color[10];
        int colorValue=100;
        for (int i=0; i<10; i++){
            colors[i] = new Color(colorValue,colorValue,colorValue);
            colorValue+=15;
        }
        chart.getStyler().setSeriesColors(colors);
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        int companiesPlotted = 0;
        for (String key : hashMap.keySet()){
            chart.addSeries (key, hashMap.get(key));
            companiesPlotted += 1;
            if (companiesPlotted==10){
                break;
            }
        }
        BitmapEncoder.saveBitmap(chart, path, BitmapEncoder.BitmapFormat.PNG);
        HashMap<String,String> output = new HashMap<>();
        output.put("plotPath","assets/charts/"+fileName);
        return output;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/popular_jobs/data",produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Long> popularJobs() throws IOException, URISyntaxException {
        ArrayList<WuzzufJob> arrayList = mappingData();
        HashMap<String, Long> hashMap = (HashMap<String, Long>) arrayList.stream()
                .collect(Collectors.groupingBy(WuzzufJob::getTitle, Collectors.counting()));
        hashMap = hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return hashMap;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/popular_jobs/plot_path",produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String,String> popularJobsPlot() throws IOException, URISyntaxException {
        HashMap<String, Long> hashMap = popularJobs();
        String fileName = "popular_jobs.png";
        String path = "C:/Users/TAREK/WebstormProjects/WuzzufServiceUI/src/assets/charts/"+fileName;
        List<String> jobs = new ArrayList<>();
        List<Long> occurrences = new ArrayList<>();
        int jobsPlottedNumber = 0;
        for (String key : hashMap.keySet()){
            jobs.add(key);
            occurrences.add(hashMap.get(key));
            jobsPlottedNumber+=1;
            if (jobsPlottedNumber==10){
                break;
            }
        }
        CategoryChart chart = new CategoryChartBuilder().width (1920).height (1080)
                .title ("Top 10 Job Titles")
                .xAxisTitle ("Job Title")
                .yAxisTitle ("Number of Occurrences")
                .theme(Styler.ChartTheme.XChart)
                .build ();
        chart.getStyler ().setSeriesColors(new Color[]{Color.GRAY});
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        chart.getStyler ().setHasAnnotations (true);
        chart.getStyler ().setStacked (true);
        chart.addSeries ("Jobs' occurrences", jobs, occurrences);
        BitmapEncoder.saveBitmap(chart, path, BitmapEncoder.BitmapFormat.PNG);
        HashMap<String,String> output = new HashMap<>();
        output.put("plotPath","assets/charts/"+fileName);
        return output;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/popular_areas/data",produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Long> popularAreas() throws IOException, URISyntaxException {
        ArrayList<WuzzufJob> arrayList = mappingData();
        HashMap<String, Long> hashMap = (HashMap<String, Long>) arrayList.stream()
                .collect(Collectors.groupingBy(WuzzufJob::getLocation, Collectors.counting()));
        hashMap = hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return hashMap;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/popular_areas/plot_path",produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String,String> popularAreasPlot() throws IOException, URISyntaxException {
        HashMap<String, Long> hashMap = popularAreas();
        String fileName = "popular_areas.png";
        String path = "C:/Users/TAREK/WebstormProjects/WuzzufServiceUI/src/assets/charts/"+fileName;
        List<String> areas = new ArrayList<>();
        List<Long> jobsNo = new ArrayList<>();
        int areasPlottedNumber = 0;
        for (String key : hashMap.keySet()){
            areas.add(key);
            jobsNo.add(hashMap.get(key));
            areasPlottedNumber+=1;
            if (areasPlottedNumber==10){
                break;
            }
        }
        CategoryChart chart = new CategoryChartBuilder().width (1920).height (1080)
                .title ("Top 10 Areas")
                .xAxisTitle ("Area Name")
                .yAxisTitle ("Number of Jobs in it")
                .theme(Styler.ChartTheme.XChart)
                .build ();
        chart.getStyler ().setSeriesColors(new Color[]{Color.GRAY});
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        chart.getStyler ().setHasAnnotations (true);
        chart.getStyler ().setStacked (true);
        chart.addSeries ("Number of Jobs for Each Area", areas, jobsNo);
        BitmapEncoder.saveBitmap(chart, path, BitmapEncoder.BitmapFormat.PNG);
        HashMap<String,String> output = new HashMap<>();
        output.put("plotPath","assets/charts/"+fileName);
        return output;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/most_wanted_skills/data",produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Long> skillsOrderedByOccurances() throws IOException, URISyntaxException {
        ArrayList<WuzzufJob> arrayList = mappingData();
        List<String> multiSkill = arrayList.stream().map(WuzzufJob::getSkills).collect(Collectors.toList());
        List<String> singleSkill = new ArrayList<>();
        for (String skills : multiSkill){
            String[] rowSkills = skills.split(", ");
            singleSkill.addAll(Arrays.asList(rowSkills));
        }
        HashMap<String,Long> hashMap = (HashMap<String, Long>) singleSkill
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        hashMap = hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        hashMap.remove("2021");
        return hashMap;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/api/kmeans_plot",produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> kmeansClustringPlot() throws IOException, URISyntaxException {
        DataFrame dataFrame = dataCleanAndFactorize();
        String fileName = "kmeans_clustering.png";
        String path = "C:/Users/TAREK/WebstormProjects/WuzzufServiceUI/src/assets/charts/"+fileName;
        dataFrame = dataFrame.select("Company","Title");
        String[] companyUnique = dataFrame.stringVector("Company").distinct().toArray(new String[] {});
        int[] companyEncoded = dataFrame.stringVector("Company").factorize(new NominalScale(companyUnique)).toIntArray();
        String[] titleUnique = dataFrame.stringVector("Title").distinct().toArray(new String[] {});
        int[] titleEncoded = dataFrame.stringVector("Title").factorize(new NominalScale(titleUnique)).toIntArray();
        DataFrame df = DataFrame.of(IntVector.of("Company", companyEncoded));
        df = df.merge(IntVector.of("Title", titleEncoded));
        KMeans clusters = KMeans.fit(df.toArray(),5);
        df = df.merge(IntVector.of("Category",clusters.y));
        Canvas canvas = ScatterPlot.of(df, "Title", "Company", "Category", '.').canvas();
        canvas.setAxisLabels("Title","Company");
        BufferedImage image = canvas.toBufferedImage(1920,1080);
        ImageIO.write(image,"png",new File(path));
        HashMap<String,String> output = new HashMap<>();
        output.put("plotPath","assets/charts/"+fileName);
        return output;
    }
}
