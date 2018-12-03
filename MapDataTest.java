import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

/**
 * This class tests the MapData classe's public methods.
 * 
 * @author Triston
 * @version 2018-10-03
 *
 */ 
public class MapDataTest
{

    /**
     * @throws IOException 
     */
    @Test
    public void testCreateFileName() throws IOException
    {
        MapData filetest = new MapData(2018, 8, 30, 17, 45, "data");
        assertEquals("data/201808301745.mdf", filetest.createFileName(2018, 8, 30, 17, 45, "data"));
 
        MapData filetest2 = new MapData(2018, 12, 11, 12, 13, "data");
        assertEquals("data/201808301745.mdf", filetest2.createFileName(2018, 8, 30, 17, 45, "data"));
 
    }
    
   
    /**
     * @throws IOException 
     */
    @Test
    public void testParseFile() throws IOException
    {
        // Creating MapData object 
        MapData parseTest = new MapData(2018, 8, 30, 17, 45, "data");

        // Method to parse the file 
        parseTest.parseFile();
        
        // Expected and actual results 
        assertEquals(968.0, parseTest.getStatistics(StatsType.MAXIMU, "SRAD").getValue(), 0.01);
        assertEquals(163.0, parseTest.getStatistics(StatsType.MINIMUM, "SRAD").getValue(), 0.01);
        assertEquals(828.1, parseTest.getStatistics(StatsType.AVERAGE, "SRAD").getValue(), 0.1);
        assertEquals(97720.0, parseTest.getStatistics(StatsType.TOTAL, "SRAD").getValue(), 0.1);
        assertEquals(36.5, parseTest.getStatistics(StatsType.MAXIMU, "TAIR").getValue(), 0.1);
        assertEquals(20.8, parseTest.getStatistics(StatsType.MINIMUM, "TAIR").getValue(), 0.1);
        assertEquals(32.4, parseTest.getStatistics(StatsType.AVERAGE, "TAIR").getValue(), 0.1);
        assertEquals(34.9, parseTest.getStatistics(StatsType.MAXIMU, "TA9M").getValue(), 0.1);
        assertEquals(20.7, parseTest.getStatistics(StatsType.MINIMUM, "TA9M").getValue(), 0.1);
        assertEquals(31.6, parseTest.getStatistics(StatsType.AVERAGE, "TA9M").getValue(), 0.1);
 
    }

    /**
     * @throws IOException
     */
    @Test
    public void testParseFileErrors() throws IOException
    {
        // Map object 
        MapData parseTest2 = new MapData(2018, 8, 1, 7, 0, "data");
       // Parsefile method 
        parseTest2.parseFile();
        // Observation objects 
        Observation expect = new Observation(0, "Error"); 
        String expected = expect.toString() + " MAXIMU"; 

        Observation actual = parseTest2.getStatistics(StatsType.MAXIMU, "SRAD");
        String actually = actual.toString();
        // The expected and actual 
        assertEquals(expected, actually);
    }

    /**
     * @throws IOException
     */
    @Test
    public void testToString() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        // The date
        int year = 2018;
        int month = 8;
        int day = 30;
        int hour = 17;
        int minute = 45;

        // Expected values
        double TairMax = 36.5;
        double TairMin = 20.8;
        double TairAvg = 32.4;
        double Ta9mMax = 34.9;
        double Ta9mMin = 20.7;
        double Ta9mAvg = 31.6;
        double sradMax = 968.0;
        double sradMin = 163.0;
        double sradAvg = 828.1;

        // STID Names
        String Hook = "HOOK"; 
        String Miam = "MIAM";
        String Mesonet = "Mesonet";
        String Slap = "SLAP";
        String expected = String
                .format("========================================================= \n"
                        + "=== %d-%02d-%02d %02d:%02d === \n", year, month, day, hour, minute)
                + String.format(
                        "========================================================= \n"
                                + "Maximum Air Temperature[1.5m] = %3.1f C at %s \n"
                                + "Minimum Air Temperature[1.5m] = %3.1f C at %s \n"
                                + "Average Air Temperature[1.5m] = %3.1f C at %s \n",
                        TairMax, Hook, TairMin, Miam, TairAvg, Mesonet)
                + String.format(
                        "========================================================= \n"
                                + "========================================================= \n"
                                + "Maximum Air Temperature[9.0m] = %3.1f C at %s \n"
                                + "Minimum Air Temperature[9.0m] = %3.1f C at %s \n"
                                + "Average Air Temperature[9.0m] = %3.1f C at %s \n"
                                + "========================================================= \n"
                                + "========================================================= \n",
                        Ta9mMax, Hook, Ta9mMin, Miam, Ta9mAvg, Mesonet)
                + String.format(
                        "Maximum Solar Radiation[1.5m] = %3.1f W/m^2 at %s \n"
                                + "Minimum Solar Radiation[1.5m] = %3.1f W/m^2 at %s \n"
                                + "Average Solar Radiation[1.5m] = %3.1f W/m^2 at %s \n"
                                + "========================================================= \n",
                        sradMax, Slap, sradMin, Miam, sradAvg, Mesonet);
        String actual = test.toString();
        assertEquals(expected, actual);
    }

}
