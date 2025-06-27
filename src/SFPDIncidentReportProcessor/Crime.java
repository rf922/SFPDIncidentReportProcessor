/*
 * File : Crime.java
 */
package SFPDIncidentReportProcessor;

import java.util.HashMap;

/**
 * An Enumeration for the reason or category each report falls into
 *
 * @author Rafael Fabiani
 */
public enum Crime {

    ARSON("Arson"), ASSAULT("Assualt"), BURGLARY("Burglary"), CASE_CLOSURE("Case Closure"), CIVIL_SIDEWALKS("Civil Sidewalks"), COURTESY_REPORT("Courtesy Report"), DISORDERLY_CONDUCT("Disorderly Conduct"), DRUG_OFFENCE("Drug Offence"), EMBEZZLEMENT("Embezzlement"),
    EXCEPTIONAL_ADULT("Exceptional Adult"), FAMILY_OFFENCE("Family Offence"), FIRE_REPORT("Fire Report"), FORGERY_AND_COUNTERFEITING("Forgery And Counterfeiting"), FRAUD("Fraud"), GAMBLING("Gambling"), HOMICIDE("Homicide"), HUMAN_TRAFFICKING("Human Trafficking"), JUVENILE_OFFENCES("Juvenile Offences"),
    LARCENY_THEFT("Larceny Theft"), LIQUOR_LAWS("Liquor Laws"), LOST_PROPERTY("Lost Property"), MALICIOUS_MISCHIEF("Malicious Mischief"), MISCELLANEOUS_INVESTIGATION("Miscellaneous Investigation"), MISSING_PERSON("Missing Person"), MOTOR_VEHICLE_THEFT("Motor Vehicle Theft"), NON_CRIMINAL("Non-Criminal"),
    OPEN_OR_ACTIVE("Open or Active"), OTHER("Other"), PROSTITUTION("Prostitution"), RAPE("Rape"), RECOVERED_VEHICLE("Recovered Vehicle"), ROBBERY("Robbery"), SEX_OFFENCE("Sex Offence"), STOLEN_PROPERTY("Stolen Property"), SUICIDE("Suicide"), SUSPICIOUS("Suspicious"), TRAFFIC_COLLISION("Traffic Collision"),
    TRAFFIC_VIOLATION_ARREST("Traffic Violation Arrest"), UNFOUNDED("Unfounded"), VANDALISM("Vandalism"), VEHICLE_IMPOUNDED("Vehicle Impounded"), VEHICLE_MISPLACED("Vehicle Misplaced"), WARRANT("Warrant"), WEAPONS_OFFENCE("Weapons Offence");

    private static final HashMap<String, Crime> CRIMES = new HashMap<>();

    static {
        for (Crime crime : Crime.values()) {
            Crime.CRIMES.put(crime.name(), crime);
        }
    }

    private String name;

    private Crime(String name) {
        this.name = name;
    }

    public static HashMap<String, Crime> getCrimes() {
        return Crime.CRIMES;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
