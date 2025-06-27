/*
 * File : ProcessReport.java  
 * Maybe implement generics in some way here would be interesting HashMap<T, LinkedList<IncidentReport>>
 * 
 * 
 */
package SFPDIncidentReportProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * A class to read in data and process that data
 *
 * @author User
 */
public class ProcessReport {

    private ArrayList<IncidentReport> reports;

    public ProcessReport(File reports) {
        this.reports = processToList(reports);
    }

    public ArrayList<IncidentReport> getReports() {
        return reports;
    }

    public void setReports(ArrayList<IncidentReport> reports) {
        this.reports = reports;
    }

    /**
     * Reads in the data from the file
     *
     * @param reports1
     * @return
     */
    private ArrayList<IncidentReport> processToList(File reports1) {
        ArrayList<IncidentReport> reports = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(reports1))) {
            String zero = reader.readLine();
            HashMap<String, IncidentReport> crimeMap = new HashMap<>();
            while ((zero = reader.readLine()) != null && !zero.isEmpty()) {
                String line = zero;
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                IncidentReport.IncidentReportBuilder report = new IncidentReport.IncidentReportBuilder()
                        .date(lineScanner.next())
                        .time(lineScanner.next())
                        .year(lineScanner.nextInt())
                        .day(lineScanner.next())
                        .incidentID(lineScanner.nextInt())
                        .category(lineScanner.next())
                        .actionTaken(lineScanner.next());

                IncidentReport report1 = report.build();

                reports.add(report1);
            }
            reader.close();

        } catch (Exception e) {
            System.out.println(String.format("%s%n%s", "Something went wrong ", e));
        } finally {
            return reports;
        }
    }

    /**
     * Takes a list of IncidentReports and maps each entry to the reason for the
     * report
     *
     * @param crimes
     * @return HashMap
     */
    public static HashMap<Crime, LinkedList<IncidentReport>> processToHashMap(List<IncidentReport> crimes) {
        HashMap<Crime, LinkedList<IncidentReport>> mapp = new HashMap<>();
        crimes.forEach(x -> {
            if (mapp.containsKey(x.getCategory())) {
                mapp.get(x.getCategory()).add(x);
            } else {
                mapp.put(x.getCategory(), new LinkedList<>());
                mapp.get(x.getCategory()).add(x);
            }
        });
        return mapp;
    }

    /**
     * Takes a list of IncidentReports and maps each entry to the day the
     * incident was reported
     *
     * @param crimes
     * @return TreeMap
     */
    public static TreeMap<LocalDate, LinkedList<IncidentReport>> processToTreeMap(List<IncidentReport> crimes) {
        TreeMap<LocalDate, LinkedList<IncidentReport>> treemap = new TreeMap<>();
        crimes.forEach(x -> {
            if (treemap.containsKey(x.getDate())) {
                treemap.get(x.getDate()).add(x);
            } else {
                treemap.put(x.getDate(), new LinkedList<>());
                treemap.get(x.getDate()).add(x);
            }
        });
        return treemap;
    }

}
