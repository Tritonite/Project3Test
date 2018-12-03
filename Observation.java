/**
 * This class holds a single measurement and flag it if its bad. 
 * 
 * @author Triston Luzanta
 *@version 2018-09-26
 */
public class Observation extends AbstractObservation
{
	/**
	 * 
	 */
	protected double value; 
	/**
	 * 
	 */
	protected boolean valid; 
	/**
	 * 
	 */
	protected String stid; 
	
	/**Observation constructor 
	 * @param value return current value 
	 * @param stid return current stid 
	 */
	public Observation(double value, String stid) 
	{
		this.value = value; 
		this.stid = stid; 
		super.valid = true; 
	} 
	/** Gets a value
	 * @return value in form of double
	 */
	public double getValue() 
	{
		return this.value; 
	}
	
	/** Test if value is valid 
	 * @return value a boolean 
	 */ 
	public boolean isValid()  
	{
	 if (getValue() <= -999) {
		 this.valid = false; 
	 }
	 else 
	 {
		 this.valid = true;
	 }
	 return this.valid; 
	}
	

	 /** Gets a stid 
	 * @return stid in form of string 
	 */
	public String getStid()
	{
		return this.stid;  
	}
	 
	/* Outputs the format 
	 * @return String in the format of (getValue) C at (getStid)
	 */
	public String toString()
	{
		
		return String.format("%2.1f C at %s", getValue(), getStid()); 
	}
	}


