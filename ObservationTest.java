
import org.junit.Assert;
import org.junit.Test;
/**
 * This class tests the Observation class's public methods. 
 * @author Triston Luzanta
 *@version 2018-09-12
 */

public class ObservationTest 
{
	
	/**
	 * Testing getMygetValue method 
	 */
	@Test 
	public void getMygetValueTest()
	{
		double input = 3.0; 
		Observation m = new Observation(input, ""); 
		// Expected 
		double expected = 3.0; 
		//actual 
		double actual = m.getValue(); 
		Assert.assertEquals(expected,actual, 0.01); 
	 
	}
	
	/**
	 * Testing isValid method
	 */
	@Test 
	public void isValidTest()
	{
		Observation test = new Observation(3.0,"");  
		// Expected 
		boolean valid = true;
		Assert.assertEquals("Testing is inValid:" , valid ,test.isValid()); 
		 }   
	/**
	 * Testing get getStid method 
	 */
	@Test 
	public void getStidTest()
	{
	
		Observation test = new Observation (2.4, "hello"); 
		// expected 
		String exp = "hello"; 
		Assert.assertEquals("Testing getStidTest:", exp, test.getStid());
		
	}
	/**
	 * Testing toString method 
	 */
	@Test
	public void toStringTest()
	{
		 
		Observation test1 = new Observation(3.0, "test1"); 
		Assert.assertEquals("3.0 C at test1",test1.toString()); 

	} 
	


}
