
import static org.junit.Assert.*;

import org.junit.Test;

import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

/**
 * *This class tests the Statistics class's public methods.
 * 
 * @author Triston
 * @version 2018-010-27
 * 
 */
public class StatisticsTest
{

    /**
     * Testing the createDataFrom String method
     * 
     * @throws ParseException
     *             throws ParseException
     */
    @Test
    public void createDataFromStringTest() throws ParseException
    {

        // Creating a new calendar object
        GregorianCalendar testCalendar = new GregorianCalendar(2018, 10, 3, 10, 30);

        // Created statistics object to store the calender object
        Statistics tester = new Statistics(10, "test", testCalendar, 90, StatsType.TOTAL);

        // Creating an expected string test
        String testDate = "2018-05-02T11:20:00 CDT";

        // Using the method to see if we get the expected
        GregorianCalendar actual = tester.createDateFromString(testDate);

        // Making an actual Gregorian Calendar with the dates to verify
        GregorianCalendar expect = new GregorianCalendar(2018, 4, 2, 11, 20, 0);

        assertEquals(expect, actual);

    }

    /**
     * Testing the createStringFromZone method
     * 
     * @throws ParseException
     */
    @Test
    public void createStringFromZDataTest() throws ParseException
    {
        // Created a testing Zone Id
        ZoneId test = ZoneId.of("America/Chicago");
        // Created a testing ZonedDateTime object
        ZonedDateTime testing = ZonedDateTime.of(2018, 12, 25, 18, 23, 29, 2345, test);
        // Created a statistics testing object
        Statistics implement = new Statistics(30, "test", testing, 5, StatsType.MAXIMU);

        // String is converted to Data
        String actual = implement.createStringFromData(testing);
        // Expected
        String expect = "2018-12-25T18:23:29 CST";

        assertEquals(expect, actual);

    }

    /**
     * Testing the createZDatefromString method
     * 
     * @throws ParseException
     */
    @Test
    public void createZDateFromString() throws ParseException
    {
        // Created ZoneId test object
        ZoneId testCST = ZoneId.of("America/Chicago");
        // Created ZoneDateTime object
        ZonedDateTime test1 = ZonedDateTime.of(2018, 05, 2, 11, 20, 0, 0000, testCST);
        // Created Statistics Object to use the testing method
        Statistics implement = new Statistics(30, "test", test1, 5, StatsType.MAXIMU);

        // String used for method
        String testDate = "2018-05-02T11:20:00 CDT";

        // Sees if the method works
        ZonedDateTime actual = implement.createZDateFromString(testDate);

        assertEquals(test1, actual);

    }

    /**
     * Testing createStringFromData method
     */
    @Test
    public void createStringFromDataTest()
    {
        GregorianCalendar testData = new GregorianCalendar(2018, 1, 3, 6, 35);

        Statistics test = new Statistics(30, "test", testData, 5, StatsType.MAXIMU);

        String actual = test.getUTCDateTimeString();

        String expect = "2018-02-03T06:35:00 CST";

        assertEquals(expect, actual);
    }

    /**
     * Testing the newerThan method
     */
    @Test
    public void GregorianNewerThanTest()
    {
        // Created two GregorianCalender objects
        GregorianCalendar test1 = new GregorianCalendar(2020, 10, 30, 15, 30);
        GregorianCalendar test2 = new GregorianCalendar(2019, 3, 4, 12, 9);

        // Created statistics object
        Statistics testing = new Statistics(30, "test", test1, 5, StatsType.MINIMUM);

        boolean expected = true;
        // Sees which calender is actually newerThan
        boolean actual = testing.newerThan(test2);

        assertEquals(expected, actual);

    }

    /**
     * Testing the olderThan method
     */
    @Test
    public void GregorianOlderThanTest()
    {
        // Created two GregorianCalender objects
        GregorianCalendar test1 = new GregorianCalendar(2019, 10, 30, 15, 30);
        GregorianCalendar test2 = new GregorianCalendar(2020, 3, 4, 12, 9);

        // Created statistics object to store GregorianCalender
        Statistics testing = new Statistics(30, "test", test1, 5, StatsType.MINIMUM);

        boolean expected = true;
        // Sees with calender is olderThan
        boolean actual = testing.olderThan(test2);

        assertEquals(expected, actual);

    }

    /**
     * Testing the sameAs method
     */
    @Test
    public void sameAsTest()
    {
        // Created two GregorianCalender objects
        GregorianCalendar test1 = new GregorianCalendar(2019, 10, 30, 15, 30);
        GregorianCalendar test2 = new GregorianCalendar(2019, 3, 4, 12, 9);

        // Statistics object to store test1
        Statistics testing = new Statistics(30, "test", test1, 5, StatsType.MINIMUM);

        boolean expected = false;
        // Testing the method sameAs for result
        boolean actual = testing.sameAs(test2);

        assertEquals(expected, actual);

    }

    /**
     * Testing ZDT newerThan method
     * 
     * @throws ParseException
     */
    @Test
    public void ZoneDateTimeNewerThanTest() throws ParseException
    {

        // Creating two Zone Id tests objects
        ZoneId testCST = ZoneId.of("America/Chicago");
        ZoneId testET = ZoneId.of("America/New_York");
        // Creating two ZoneDateTime test objects
        ZonedDateTime test1 = ZonedDateTime.of(2017, 12, 25, 15, 50, 30, 1234, testCST);
        ZonedDateTime test2 = ZonedDateTime.of(2018, 12, 25, 15, 50, 30, 1234, testET);

        // Statistics Object to call the method to test
        Statistics testing = new Statistics(30, "test", test1, 5, StatsType.MINIMUM);

        boolean expected = false;
        // Test the actual result
        boolean actual = testing.newerThan(test2);

        assertEquals(expected, actual);

    }

    /**
     * Testing ZDT olderThan method
     * 
     * @throws ParseException
     */
    @Test
    public void ZoneDateTimenOlderThanTest() throws ParseException
    {
        // Created two Zone Id test objects
        ZoneId testCST = ZoneId.of("America/Chicago");
        ZoneId testET = ZoneId.of("America/New_York");

        // Created two ZonedDateTime test objects

        ZonedDateTime test1 = ZonedDateTime.of(2017, 12, 25, 15, 50, 30, 1234, testCST);
        ZonedDateTime test2 = ZonedDateTime.of(2018, 12, 25, 15, 50, 30, 1234, testET);

        // Statistics object to use the method with "test1" stored

        Statistics testing = new Statistics(30, "test", test1, 5, StatsType.MINIMUM);

        // Expecting true
        boolean expected = true;
        // Uses the method to get the actual result
        boolean actual = testing.olderThan(test2);

        assertEquals(expected, actual);

    }

    /**
     * Testing ZDT sameAs method
     * 
     * @throws ParseException
     */
    @Test
    public void ZoneDateTimeSameAsTest() throws ParseException
    {
        // Created two Zone Id test objects
        ZoneId testCST = ZoneId.of("America/Chicago");
        ZoneId testET = ZoneId.of("America/New_York");

        // Created two ZonedDateTime test objects
        ZonedDateTime test1 = ZonedDateTime.of(2017, 12, 25, 15, 50, 30, 1234, testCST);
        ZonedDateTime test2 = ZonedDateTime.of(2018, 12, 25, 15, 50, 30, 1234, testET);

        // Statistics object to use the method with "test1" stored
        Statistics testing = new Statistics(30, "test", test1, 5, StatsType.MINIMUM);

        // Expecting false
        boolean expected = false;
        // Implements the method and what the actual result is
        boolean actual = testing.sameAs(test2);

        assertEquals(expected, actual);

    }

    /**
     * Testing the toString method
     */
    @Test
    public void toStringTest()
    {
        // Created GregorianCalender object
        GregorianCalendar test1 = new GregorianCalendar(2019, 10, 30, 15, 30);
        // Statistics object to store the GregorianCalender
        Statistics test = new Statistics(30, "test", test1, 5, StatsType.MINIMUM);
        // Expected String and actual using the method
        assertEquals("30.0 C at test MINIMUM", test.toString());

    }
}
