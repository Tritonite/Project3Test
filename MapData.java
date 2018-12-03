import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This MapData class is a remodification of project 2's MapData class but with
 * different variable types. This class reads in a textfile and parses  the
 * Max,Min, and Average of the following data types: SRAD, TAIR, and TA9M. The
 * class also displays their STID as well.
 * 
 * @author Triston Luzanta
 * @version 2018-09-26
 * 
 * 
 */
public class MapData
{

    // calculations null if it hits 10 or more
    private final int NUMBER_OF_MISSING_OBSERVATIONS = 10;

    private Integer numberOfStations = null;

    private final String TA9M = "TA9M";
    private final String TAIR = "TAIR";
    private final String SRAD = "SRAD";
    private final String STID = "STID";

    private final String MESONET = "Mesonet";

    private HashMap<String, ArrayList<Observation>> dataCatalog = new HashMap<String, ArrayList<Observation>>();

    private TreeMap<String, Integer> paramPositions = new TreeMap<String, Integer>();

    private EnumMap<StatsType, TreeMap<String, Statistics>> statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>(
            StatsType.class);

    private String filename;

    private GregorianCalendar utcDateTime;

    /**
     * MapData constructor
     * 
     * @param year
     *            year input
     * @param month
     *            month input
     * @param day
     *            day input
     * @param hour
     *            hour input
     * @param minute
     *            minute input
     * @param directory
     *            name of inputed directory
     * @throws IOException
     *             for weird input
     */

    public MapData(int year, int month, int day, int hour, int minute, String directory) throws IOException
    {
        // Creating a new GregorianCalender of utcDateTime.
        utcDateTime = new GregorianCalendar(year, month, day, hour, minute);

        // this filename string stores in the method createFileName
        filename = createFileName(year, month, day, hour, minute, directory);
    }

    /**
     * Creates and sets the filename *
     * 
     * @param year
     *            year input
     * @param month
     *            month input
     * @param day
     *            day input
     * @param hour
     *            hour input
     * @param minute
     *            minute input
     * @param directory
     *            directory filename. Inputs needed to format the filename.
     * @return a string filename
     */
    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {

        String mth = "";
        String dy = "";
        String hr = "";
        String min = "";
        // determines if the month,day,hour,minute needs a 0 pad otherwise no 0 is
        // needed.
        if (month < 10)
        {
            mth = "0" + month;
        }
        else
        {
            mth = "" + month;
        }

        if (day < 10)
        {
            dy = "0" + day;
        }
        else
        {
            dy = "" + day;
        }
        if (hour < 10)
        {
            hr = "0" + hour;
        }
        else
        {

            hr = "" + hour;
        }
        if (minute < 10)
        {
            min = "0" + minute;
        }
        else
        {
            min = "" + minute;
        }

        return String.format("%s/%d%s%s%s%s" + ".mdf", directory, year, mth, dy, hr, min);

    }

    /**
     * 
     * @param inParamStr
     *            Used to find the positions of data types TAIR,STID,TA9M, and SRAD
     * @throws IOException
     *             throws IOException
     */
    private void parseParamHeader(String inParamStr) throws IOException
    {
        // filename is being read
        BufferedReader br = new BufferedReader(new FileReader(filename));
        // Reads lines of data
        String strg = br.readLine();
        strg = br.readLine();
        strg = br.readLine();
        // Splits the white spaces between each information
        String[] split = strg.trim().split("\\s+");
        // Stores the position of the data types in the Treemap.
        for (int i = 0; i < split.length; i++)
        {
            if (split[i].equalsIgnoreCase(inParamStr))
            {
                if (inParamStr.equalsIgnoreCase(TAIR))
                {
                    paramPositions.put(TAIR, i);
                }

                else if (inParamStr.equalsIgnoreCase(STID))
                {
                    paramPositions.put(STID, i);
                }

                else if (inParamStr.equalsIgnoreCase(TA9M))
                {
                    paramPositions.put(TA9M, i);
                }

                else if (inParamStr.equalsIgnoreCase(SRAD))
                {
                    paramPositions.put(SRAD, i);
                }
            }
        }
        br.close();
    }

    /**
     * 
     * @param inParamStr the string that the method will parse 
     * @return Integer, the index number of the position data types
     * @throws IOException throws IOException 
     */
    public Integer getIndexOfString(String inParamStr) throws IOException
    {

        this.parseParamHeader(inParamStr);
        return paramPositions.get(inParamStr);
    }

    /**
     * reads the filename and outputs the data line by line reads the tairData,
     * sradData, and ta9mData and calculate their statistics.
     * 
     * @throws IOException
     *             throws IOException
     */
    public void parseFile() throws IOException
    {

        // Observation array called sradData
        ArrayList<Observation> sradData = new ArrayList<Observation>();
        // Observation array called tairData
        ArrayList<Observation> tairData = new ArrayList<Observation>();
        // Observation array called ta9mData
        ArrayList<Observation> ta9mData = new ArrayList<Observation>();

        // reads the filename
        FileReader stuff = new FileReader(filename);
        BufferedReader br = new BufferedReader(stuff);

        // reads lines of data
        String strg = br.readLine();
        strg = br.readLine();
        strg = br.readLine();
        strg = br.readLine();

        numberOfStations = 0;

        String[] info = strg.split("\\s+");
        // Reads each line and stores the data types in their corresponding array list.
        while (strg != null)
        {
            info = strg.trim().split("\\s+");

            // added the index number and stid of each of the corresponding data types
            tairData.add(
                    new Observation(Double.parseDouble(info[getIndexOfString(TAIR)]), info[getIndexOfString(STID)]));
            sradData.add(
                    new Observation(Double.parseDouble(info[getIndexOfString(SRAD)]), info[getIndexOfString(STID)]));
            ta9mData.add(
                    new Observation(Double.parseDouble(info[getIndexOfString(TA9M)]), info[getIndexOfString(STID)]));

            numberOfStations++;
            strg = br.readLine();

        }

        // Intialized hashmap of dataCatalog
        this.prepareDataCatalog();

        // Hashmap stores the key and value of datatypes
        dataCatalog.put(TAIR, tairData);
        dataCatalog.put(SRAD, sradData);
        dataCatalog.put(TA9M, ta9mData);

        // Calculates the statistics of the datatypes
        calculateStatistics();

        br.close();
    }

    /**
     * Calls the calculateStatistics method
     */
    private void calculateStatistics()
    {
        this.calculateAllStatistics();
    }

    /**
     * Initialized the hashmap into a method.
     */
    private void prepareDataCatalog()
    {
        dataCatalog = new HashMap<String, ArrayList<Observation>>();
    }

    /**
     * 
     */
    private void calculateAllStatistics()
    {
        // Three observation arrays of data types.
        ArrayList<Observation> sradData = new ArrayList<Observation>();
        ArrayList<Observation> tairData = new ArrayList<Observation>();
        ArrayList<Observation> ta9mData = new ArrayList<Observation>();

        // TreeMaps initialized to keep track of the data's min,max,total,and average
        TreeMap<String, Statistics> dataMin = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> dataMax = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> dataAvg = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> dataTot = new TreeMap<String, Statistics>();

        // Assigning the Observation array to the hashMap object value
        sradData = dataCatalog.get(SRAD);
        tairData = dataCatalog.get(TAIR);
        ta9mData = dataCatalog.get(TA9M);

        double maxData = -Double.MAX_VALUE; // compares the value to the negative maximum value holder
        int countMax = 0;
        // calculates sradMax Data
        for (int i = 0; i < numberOfStations; i++)
        {
            // checks if data is valid then the value is stored
            if (sradData.get(i).isValid() && sradData.get(i).getValue() > maxData)
            {

                maxData = sradData.get(i).getValue();
                countMax = i;
            }
        }

        int countMin = 0;
        double minData = Double.MAX_VALUE;
        // Calculates sradMin data
        for (int i = 0; i < numberOfStations; i++)
        {
            // checks if data is valid, the value is stored
            if (sradData.get(i).isValid() && sradData.get(i).getValue() < minData)
            {

                minData = sradData.get(i).getValue();
                countMin = i;

            }
        }

        int loss = 0;
        double averageData = 0;
        double dataTotal = 0;
        int countAvg = 0;
        // Calculates sradAverage data
        for (int i = 0; i < numberOfStations; i++)
        {
            // checks if data is valid

            if (sradData.get(i).isValid())
            {
                dataTotal += sradData.get(i).getValue();
                countAvg++;
            }

            else
            {
                loss++;
            }
        }

        // Calculates the sradData average
        averageData = dataTotal / countAvg;
        // Checks if there is less than the NUMBER_OF_MISSING_OBSERVATIONS, then the
        // program will run.
        if (loss < NUMBER_OF_MISSING_OBSERVATIONS)
        {

            // Treemaps stores the information
            dataMax.put(SRAD, new Statistics(maxData, sradData.get(countMax).getStid(), utcDateTime,
                    (numberOfStations - loss), StatsType.MAXIMU));
            dataMin.put(SRAD, new Statistics(minData, sradData.get(countMin).getStid(), utcDateTime,
                    (numberOfStations - loss), StatsType.MINIMUM));
            dataAvg.put(SRAD,
                    new Statistics(averageData, MESONET, utcDateTime, (numberOfStations - loss), StatsType.AVERAGE));

            dataTot.put(SRAD,
                    new Statistics(dataTotal, MESONET, utcDateTime, (numberOfStations - loss), StatsType.TOTAL));
        }

        else
        {
            // Treemaps will store this information
            dataMax.put(SRAD, new Statistics(0.0, "Error", utcDateTime, (numberOfStations - loss), StatsType.MAXIMU));
            dataMin.put(SRAD, new Statistics(0.0, "Error", utcDateTime, loss, StatsType.MINIMUM));
            dataAvg.put(SRAD, new Statistics(0.0, "Error", utcDateTime, (numberOfStations - loss), StatsType.AVERAGE));

            dataTot.put(SRAD, new Statistics(0.0, "Error", utcDateTime, (numberOfStations - loss), StatsType.TOTAL));

        }

        countMax = 0;
        maxData = -Double.MAX_VALUE;
        // calculates tairDataMax Data
        for (int i = 0; i < numberOfStations; i++)
        {
            // checks if data is valid then the value is stored
            if (tairData.get(i).isValid() && tairData.get(i).getValue() > maxData)
            {

                maxData = tairData.get(i).getValue();
                countMax = i;
            }
        }

        countMin = 0;
        minData = Double.MAX_VALUE;
        // Calculates tairData Min data
        for (int i = 0; i < numberOfStations; i++)
        {
            // checks if data is valid, the value is stored
            if (tairData.get(i).isValid() && tairData.get(i).getValue() < minData)
            {

                minData = tairData.get(i).getValue();
                countMin = i;

            }
        }

        loss = 0;
        averageData = 0;
        dataTotal = 0;
        countAvg = 0;
        // Calculates tairData Average data
        for (int i = 0; i < numberOfStations; i++)
        {
            // checks if data is valid

            if (tairData.get(i).isValid())
            {
                dataTotal += tairData.get(i).getValue();
                countAvg++;
            }

            else
            {
                loss++;
            }
        }

        // Calculates the tairData average
        averageData = dataTotal / countAvg;
        // Checks if there is less than the NUMBER_OF_MISSING_OBSERVATIONS, then the
        // program will run.
        if (loss < NUMBER_OF_MISSING_OBSERVATIONS)
        {

            dataMax.put(TAIR, new Statistics(maxData, tairData.get(countMax).getStid(), utcDateTime,
                    (numberOfStations - loss), StatsType.MAXIMU));
            dataMin.put(TAIR, new Statistics(minData, tairData.get(countMin).getStid(), utcDateTime,
                    (numberOfStations - loss), StatsType.MINIMUM));
            dataAvg.put(TAIR,
                    new Statistics(averageData, MESONET, utcDateTime, (numberOfStations - loss), StatsType.AVERAGE));
        }

        else
        {
            dataMax.put(TAIR, new Statistics(0.0, "Error", utcDateTime, (numberOfStations - loss), StatsType.MAXIMU));
            dataMin.put(TAIR, new Statistics(0.0, "Error", utcDateTime, (numberOfStations - loss), StatsType.MINIMUM));
            dataAvg.put(TAIR, new Statistics(0.0, "Error", utcDateTime, (numberOfStations - loss), StatsType.AVERAGE));
        }

        countMax = 0;
        maxData = -Double.MAX_VALUE;
        // calculates ta9m Max Data
        for (int i = 0; i < numberOfStations; i++)
        {
            // checks if data is valid then the value is stored
            if (ta9mData.get(i).isValid() && ta9mData.get(i).getValue() > maxData)
            {

                maxData = ta9mData.get(i).getValue();
                countMax = i;
            }
        }

        countMin = 0;
        minData = Double.MAX_VALUE;
        // Calculates ta9m Min data
        for (int i = 0; i < numberOfStations; i++)
        {
            // checks if data is valid, the value is stored
            if (ta9mData.get(i).isValid() && ta9mData.get(i).getValue() < minData)
            {

                minData = ta9mData.get(i).getValue();
                countMin = i;

            }
        }

        loss = 0;
        averageData = 0;
        dataTotal = 0;
        int count3 = 0;
        // Calculates ta9m Average data
        for (int i = 0; i < numberOfStations; i++)
        {
            // checks if data is valid

            if (ta9mData.get(i).isValid())
            {
                dataTotal += ta9mData.get(i).getValue();
                count3++;
            }

            else
            {
                loss++;
            }
        }

        // Calculates the average
        averageData = dataTotal / count3;
        // Checks if there is less than the NUMBER_OF_MISSING_OBSERVATIONS, then the
        // program will run.
        if (loss < NUMBER_OF_MISSING_OBSERVATIONS)
        {
            dataMax.put(TA9M, new Statistics(maxData, ta9mData.get(countMax).getStid(), utcDateTime,
                    (numberOfStations - loss), StatsType.MAXIMU));
            dataMin.put(TA9M, new Statistics(minData, ta9mData.get(countMin).getStid(), utcDateTime,
                    (numberOfStations - loss), StatsType.MINIMUM));
            dataAvg.put(TA9M,
                    new Statistics(averageData, MESONET, utcDateTime, (numberOfStations - loss), StatsType.AVERAGE));
        }

        else
        {
            dataMax.put(TA9M, new Statistics(0.0, "Error", utcDateTime, (numberOfStations - loss), StatsType.MAXIMU));
            dataMin.put(TA9M, new Statistics(0.0, "Error", utcDateTime, (numberOfStations - loss), StatsType.MINIMUM));
            dataAvg.put(TA9M, new Statistics(0.0, "Error", utcDateTime, (numberOfStations - loss), StatsType.AVERAGE));
        }

        // Storing the Statistics into an EnumMap
        statistics.put(StatsType.MAXIMU, dataMax);
        statistics.put(StatsType.MINIMUM, dataMin);
        statistics.put(StatsType.AVERAGE, dataAvg);
        statistics.put(StatsType.TOTAL, dataTot);

    }

    /**
     * 
     * @param type The type of constant from Statstype class 
     * @param paramid returns the specified string data
     * @return returns the type and string value of the EnumMap
     */
    public Statistics getStatistics(StatsType type, String paramid)
    {
        return statistics.get(type).get(paramid);
    }

    /**
     * @return the format of the .mdf file
     */
    public String toString()
    {
        String map = "========================================================= \n";
        String one = String.format("=== %d-%02d-%02d %02d:%02d === \n", utcDateTime.get(Calendar.YEAR),
                utcDateTime.get(Calendar.MONTH), utcDateTime.get(Calendar.DATE), utcDateTime.get(Calendar.HOUR_OF_DAY),
                utcDateTime.get(Calendar.MINUTE));
        String two = String.format("Maximum Air Temperature[1.5m] = %3.1f C at %s \n",
                getStatistics(StatsType.MAXIMU, TAIR).getValue(), getStatistics(StatsType.MAXIMU, TAIR).getStid());
        String three = String.format("Minimum Air Temperature[1.5m] = %3.1f C at %s \n",
                getStatistics(StatsType.MINIMUM, TAIR).getValue(), getStatistics(StatsType.MINIMUM, TAIR).getStid());
        String four = String.format("Average Air Temperature[1.5m] = %3.1f C at %s \n",
                getStatistics(StatsType.AVERAGE, TAIR).getValue(), getStatistics(StatsType.AVERAGE, TAIR).getStid());
        String five = String.format("Maximum Air Temperature[9.0m] = %3.1f C at %s \n",
                getStatistics(StatsType.MAXIMU, TA9M).getValue(), getStatistics(StatsType.MAXIMU, TA9M).getStid());
        String six = String.format("Minimum Air Temperature[9.0m] = %3.1f C at %s \n",
                getStatistics(StatsType.MINIMUM, TA9M).getValue(), getStatistics(StatsType.MINIMUM, TA9M).getStid());
        String seven = String.format("Average Air Temperature[9.0m] = %3.1f C at %s \n",
                getStatistics(StatsType.AVERAGE, TA9M).getValue(), getStatistics(StatsType.AVERAGE, TA9M).getStid());
        String eight = String.format("Maximum Solar Radiation[1.5m] = %3.1f W/m^2 at %s \n",
                getStatistics(StatsType.MAXIMU, SRAD).getValue(), getStatistics(StatsType.MAXIMU, SRAD).getStid());
        String nine = String.format("Minimum Solar Radiation[1.5m] = %3.1f W/m^2 at %s \n",
                getStatistics(StatsType.MINIMUM, SRAD).getValue(), getStatistics(StatsType.MINIMUM, SRAD).getStid());
        String ten = String.format("Average Solar Radiation[1.5m] = %3.1f W/m^2 at %s \n",
                getStatistics(StatsType.AVERAGE, SRAD).getValue(), getStatistics(StatsType.AVERAGE, SRAD).getStid());

        String output = (map + one + map + two + three + four + map + map + five + six + seven + map + map + eight
                + nine + ten + map);
        return output;
    }

}
