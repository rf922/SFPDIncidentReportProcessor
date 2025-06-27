/*
 * File : IncidentReport.java
 */
package SFPDIncidentReportProcessor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A class to represent an IncidentReport
 *
 * @author Rafael F.
 */
public class IncidentReport implements Comparable<IncidentReport> {

    private int year, iD;
    private String time, actionTaken;
    private Crime category;
    private DayOfWeek day;
    private LocalDate date;

    /**
     * A Builder Class for IncidentReport
     */
    public static class IncidentReportBuilder {

        private static final String DEFAULT = "N/A";
        private int year, iD;
        private String time, actionTaken;
        private Crime category;
        private DayOfWeek day;
        private LocalDate date;
        private final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("L/d/yyyy");

        public IncidentReportBuilder self() {
            return this;
        }

        public IncidentReportBuilder year(int year) {
            this.year = year;
            return this;
        }

        public IncidentReportBuilder date(String date) {
            this.date = LocalDate.parse(date, DATEFORMAT);
            return this;
        }

        public IncidentReportBuilder incidentID(int id) {
            this.iD = id;
            return this;
        }

        public IncidentReportBuilder time(String time) {
            this.time = time;
            return this;
        }

        public IncidentReportBuilder day(String day) {
            this.day = DayOfWeek.valueOf(day.toUpperCase());
            return this;
        }

        public IncidentReportBuilder category(String category) {
            category = category.replaceAll("\\s+", "_");
            this.category = Crime.valueOf(category.toUpperCase());
            return this;
        }

        public IncidentReportBuilder actionTaken(String actionTaken) {
            this.actionTaken = actionTaken;
            return this;
        }

        public IncidentReport build() {
            return new IncidentReport(this);
        }

    }

    public IncidentReport(IncidentReportBuilder obj) {
        this.year = obj.year;
        this.iD = obj.iD;
        this.date = obj.date;
        this.day = obj.day;
        this.time = obj.time;
        this.category = obj.category;
        this.actionTaken = obj.actionTaken;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public Crime getCategory() {
        return category;
    }

    public void setCategory(Crime category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%-30s%s%n%-30s%s%n%-30s%s%n%-30s%s%n%-30s%s%n%-30s%s%n%-30s%s%n",
                "Date             :", this.date,
                "Year             :", this.getYear(),
                "Day              :", this.getDay(),
                "Time             :", this.getTime(),
                "Incident ID      :", this.getiD(),
                "Category         :", this.getCategory(),
                "Action Taken     :", this.actionTaken
        );
    }

    @Override
    public int compareTo(IncidentReport o) {
        return date.compareTo(o.getDate());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.year;
        hash = 89 * hash + this.iD;
        hash = 89 * hash + Objects.hashCode(this.time);
        hash = 89 * hash + Objects.hashCode(this.actionTaken);
        hash = 89 * hash + Objects.hashCode(this.category);
        hash = 89 * hash + Objects.hashCode(this.day);
        hash = 89 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IncidentReport other = (IncidentReport) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.iD != other.iD) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.actionTaken, other.actionTaken)) {
            return false;
        }
        if (this.category != other.category) {
            return false;
        }
        if (this.day != other.day) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

}
