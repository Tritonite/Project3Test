import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;


/* This class extends the Observation class but implements the Gregorian Calender and ZoneDateTime formatting.  
 * 
 * @author Triston Luzanta 
 * @version 10-29-2018
 */

public class Statistics extends Observation implements DateTimeComparable

{
    /**
     * variable string called DATE_TIME_FORMAT
     */
    protected final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    /**
     * GregorianCalender variable called utcDateTime
     */
    private GregorianCalendar utcDateTime;
    /**
     * an int variable called nummberOfReportingStations
     */
    private int numberOfReportingStations;
    /**
     * Enumeration StatsType 
     */
    private StatsType statsType;
    
    protected DateTimeFormatter format; 
    
    private ZonedDateTime ztdDateTime; 

    /**
     * @param value 
     * uses the Observation value 
     * @param stid 
     * uses the Observation stid
     * @param dateTime 
     * ZonedDateTime calender 
     * @param numberOfValidStations 
     * an int to keep track of the number of stations 
     * @param inStatType 
     * an Constant from the StatsType class  
     * @throws ParseException throws ParseException 
     */
    public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations, StatsType inStatType)
            throws ParseException
    {
        // Able to use the Values and Stid from Observation Class 
        super(value, stid);
        // Initialize ZonedDateTime
        ztdDateTime = dateTime; 
        // Initialize numberOfReportingStations
        numberOfReportingStations = numberOfValidStations;
        statsType = inStatType;
        

    }
 
    /** 
     * @param value
     * uses the Observation value
     * @param stid
     * uses the Observation stid 
     * @param dateTime
     * a GregorianCalender called dateTime
     * @param numberOfValidStations
     * an int 
     * @param inStatType
     * A constant from the StatsType class 
     * 
     */
    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
            StatsType inStatType)
    { 
        // Able to use Values and Stid from Observation Class 
        super(value, stid);
        utcDateTime = dateTime;
        numberOfReportingStations = numberOfValidStations;
        statsType = inStatType;
    }
 
    /**
     * @param dateTimeStr Converts the DATE_TIME_FORMAT string into a GregorianCalender 
     * @return GregorianCalender A GregorianCalender with the set time formatted 
     * @throws ParseException throws ParaseException 
     */
    public GregorianCalendar createDateFromString(String dateTimeStr) throws ParseException
    {

        // Formatting of the date time format 
        SimpleDateFormat dateFromString = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date infoTime = new Date();
        // Gives the specific date and parses it 
        infoTime = dateFromString.parse(dateTimeStr);
        // Created a Gregorian Calender to store 
        GregorianCalendar calender = new GregorianCalendar();
        // Sets that calender that time data format 
        calender.setTime(infoTime);
 
        return calender; 
  
    }
     
    /**
     * @param dateTimeStr Converts the DATE_TIME_FORMAT string into ZoneDateTime
     * @return ZonedDateTime A ZonedDateTime with the set time formatted 
     * @throws ParseException throws ParseException 
     */
    public ZonedDateTime createZDateFromString(String dateTimeStr) throws ParseException
    {
        // Formatting of the date time format 
        format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT); 
        // Retrieves and parses the data in that date time format 
        ZonedDateTime zdt = ZonedDateTime.parse(dateTimeStr, format.withZone(ZoneId.systemDefault())); 
        return zdt;  
         
    } 

    /**
     * @param calender Coverts the GregorianCalender into a String DATE_TIME_FORMAT. 
     * @return String A string of the data formatted of the GregorianCalender 
     */
    public String createStringFromData(GregorianCalendar calender)
    {
        
        SimpleDateFormat stringFromData = new SimpleDateFormat(DATE_TIME_FORMAT);
        // Formats the data into a string 
        String result = stringFromData.format(calender.getTime());

        return result;

    }

    /**
     * @param calender Converts the ZonedDateTime Calender into a string DATE_TIME_FORMAT 
     * @return String A string of the data formatted of the ZonedDateTime 
     */
    public String createStringFromData(ZonedDateTime calender)
    {
        format  = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        // Formats the data into a string 
        String result = format.format(calender); 

        return result;
   
    }
    /**
     * @return the numberOfReportingStations
     */
    public int getNumberOfReportingStations()
    {
        return numberOfReportingStations;
    }
 
    /**
     * @return Converts the utcDateTime Gregorian calender to a string. 
     */
    public String getUTCDateTimeString()
    {
        return createStringFromData(utcDateTime);
    }

    /**
     * @param inDateTime Compares two  ZonedDateTime times to see which one is newer. 
     * @return boolean true if it's newer, false if not  
     */
    
    public boolean newerThan(ZonedDateTime inDateTime)
    {
        // Sees if the ZDT is newer 
        return (ztdDateTime.compareTo(inDateTime) > 0); 
 
    }
 
   /**
    * @param inDateTime Compares two Gregorian Calenders to see which one is older. 
    * @return boolean true if it's older, false if not 
    */
   
    public boolean olderThan(ZonedDateTime inDateTime)
    {   
        // Sees if the ZDT is older 
        return (ztdDateTime.compareTo(inDateTime) < 0); 
        
    }
 
    /**
     * @param inDateTime compares two ZonedDateTime times  to see if they are the same.
     * @return boolean true if it's newer, false if not 
     */
   
    public boolean sameAs(ZonedDateTime inDateTime)
    {
        // Sees if the ZDT is the same as the other 
        return (ztdDateTime.compareTo(inDateTime) == 0); 
        
    }
    /**
     * @param inDateTime compares two Gregorian Calenders to see which one is newer 
     * 
     */
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        // Sees if the calender is newer  
        return (utcDateTime.compareTo(inDateTime) > 0); 

    }
 
   /**
    * @param inDateTime Compares two GregorianCalenders to see which one is older. 
    * @return boolean true if it's older, false if not 
    */
    public boolean olderThan(GregorianCalendar inDateTime)
    {   
        // Sees if the calender is older 
        return (utcDateTime.compareTo(inDateTime) < 0); 
        
    }

    /**
     * @param inDateTime compares two GregorianCalenders to see if they are the same.
     * @return boolean true if it's newer, false if not 
     */
    public boolean sameAs(GregorianCalendar inDateTime)
    {
        // Sess if the calender is the same as the other
        return (utcDateTime.compareTo(inDateTime) == 0); 
        
    }

    
 
    /**
     * @return a String that is the same format as the toString in Observation class. 
     */
    public String toString()
    { 
        
        return String.format("%2.1f C at %s " + statsType, getValue(), getStid());
    }

}
