import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

/**
 * This class is implemented by the Statistics class. 
 * @author Triston
 *
 */
public interface DateTimeComparable 
{
	boolean newerThan(GregorianCalendar inDateTimeUTC); 
	
	boolean olderThan(GregorianCalendar inDateTimeUTC); 
	
	boolean sameAs(GregorianCalendar inDateTimeUTC); 
	
boolean newerThan(ZonedDateTime inDateTimeUTC); 
    
    boolean olderThan(ZonedDateTime inDateTimeUTC); 
    
    boolean sameAs(ZonedDateTime inDateTimeUTC); 
	
	
}
 