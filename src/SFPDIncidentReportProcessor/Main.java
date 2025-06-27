/**
 * File : Main.java
 * A Driver that uses Process Report to create Incident Report objects and then gathers those
 * to various maps to answer Queries
 *
 * 1.How many total reports for each year were made ?
 * 2.How many reports were made for each category/Crime overall ?
 *
 */
package SFPDIncidentReportProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Rafael Fabiani
 */
public class Main {

    public enum Districts {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * Note to alter the path as needed before running to avoid errors;
         */
        File data = new File("C:\\Users\\User\\Desktop\\SFPD_Incident_Reports__2018_to_Present.csv");

        ProcessReport dataProcessor = new ProcessReport(data);
        ArrayList<IncidentReport> reportList = dataProcessor.getReports();
        HashMap<Crime, LinkedList<IncidentReport>> reportByCrime = ProcessReport.processToHashMap(reportList);
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("-------------REPORT-DATA-OVERALL----FROM-2018-TO-2020-MARCH-13--------------------------------");
        System.out.println(String.format("%-30s%s", "Reason :", "Number of Reports :"));
        reportByCrime.forEach((x1, x2) -> System.out.println(String.format("%-30s%s", x1, x2.size())));
        HashMap<Integer, LinkedList<IncidentReport>> reportsByYear = new HashMap<>();
        reportList.forEach(x -> {
            if (reportsByYear.containsKey(x.getYear())) {
                reportsByYear.get(x.getYear()).add(x);
            } else {
                reportsByYear.put(x.getYear(), new LinkedList<>());
                reportsByYear.get(x.getYear()).add(x);
            }
        });
        LinkedList<HashMap<Crime, LinkedList<IncidentReport>>> listCrimeByYear = new LinkedList<>();
        reportsByYear.values().iterator();
        for (LinkedList<IncidentReport> r : reportsByYear.values()) {
            HashMap<Crime, LinkedList<IncidentReport>> crimeByYear = new HashMap<>();
            Iterator<IncidentReport> reports = r.iterator();
            while (reports.hasNext()) {
                IncidentReport report = reports.next();
                if (crimeByYear.containsKey(report.getCategory())) {
                    crimeByYear.get(report.getCategory()).add(report);
                } else {
                    crimeByYear.put(report.getCategory(), new LinkedList<>());
                    crimeByYear.get(report.getCategory()).add(report);
                }
            }
            listCrimeByYear.add(crimeByYear);
        }
        System.out.println("----------------------------------------------------------------------------------------------");
        ArrayList<Integer> numIncidents = new ArrayList<>();
        Iterator<Integer> years = reportsByYear.keySet().iterator();
        listCrimeByYear.forEach(y -> {
            System.out.println("------------YEAR---" + years.next() + "----------------INCIDENT-DATA------------------------------------------");
            System.out.println(String.format("%-30s%s", "Reason :", "Reports Made :"));
            y.forEach((z1, z2) -> {
                System.out.println(String.format("%-30s%s", z1, z2.size()));
                numIncidents.add(z2.size());
            });
            int totalReports = 0;
            for (Integer reports : numIncidents) {
                totalReports += reports;
            }
            System.out.println(String.format("%-30s%s", "Total year reports :", totalReports));
            numIncidents.clear();
        });

    }
}
