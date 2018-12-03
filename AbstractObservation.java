/**
 * This class is used specifcally by the Observation class. 
 * @author Triston
 *@version 2018-09-26
 */
public abstract class AbstractObservation 
{

	/**
	 * boolean variable initialized valid. 
	 */
	protected boolean valid; 
	
	/**
	 *  AbstractObservation Constructor 
	 */
	public AbstractObservation() 
	{
		valid = true;  
	}
	 
	/**
	 * @return boolean valid 
	 */
	public boolean isValid()
	{
	    return valid;  
	}
}
