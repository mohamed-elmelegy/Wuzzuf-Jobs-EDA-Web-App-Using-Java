package com.project.wuzzufjobservice;

public class WuzzufJob {
    private final String Title;
    private final String Company;
    private final String Location;
    private final String Type;
    private final String Level;
    private final int MinYearsExp;
    private final String Country;
    private final String Skills;

    public WuzzufJob(String title, String company, String location, String type, String level, int minYearsExp, String country, String skills) {
        Title = title;
        Company = company;
        Location = location;
        Type = type;
        Level = level;
        MinYearsExp = minYearsExp;
        Country = country;
        Skills = skills;
    }

    public String getTitle() {
        return Title;
    }

    public String getCompany() {
        return Company;
    }

    public String getLocation() {
        return Location;
    }

    public String getType() {
        return Type;
    }

    public String getLevel() {
        return Level;
    }

    public int getMinYearsExp() {
        return MinYearsExp;
    }

    public String getCountry() {
        return Country;
    }

    public String getSkills() {
        return Skills;
    }

    @Override
    public String toString() {
        return "WuzzufJob{" +
                "Title='" + Title + '\'' +
                ", Company='" + Company + '\'' +
                ", Location='" + Location + '\'' +
                ", Type='" + Type + '\'' +
                ", Level='" + Level + '\'' +
                ", YearsExp='" + MinYearsExp + '\'' +
                ", Country='" + Country + '\'' +
                ", Skills='" + Skills + '\'' +
                '}';
    }
}
