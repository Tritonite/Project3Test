package projectTest;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StatisticsPanel extends JPanel{
	
	// Title for Statistics panel
	JLabel label3 = new JLabel("Statistics");
	
	JRadioButton min = new JRadioButton("MINIMUM");
	JRadioButton max = new JRadioButton("MAXIMUM"); 
	JRadioButton avg = new JRadioButton("AVERAGE"); 
	
	
	
	public StatisticsPanel(){
		
	super(); 
		
		
	setLayout(new GridLayout(4,0));

	

	// To set the panel to align at the top 
	label3.setVerticalAlignment(JRadioButton.TOP); 
	min.setVerticalAlignment(JRadioButton.TOP);
	max.setVerticalAlignment(JRadioButton.TOP);
	avg.setVerticalAlignment(JRadioButton.TOP);
	
	
	
	add(label3); 
	
	// min placement 
	add(min); 
			
	// avg placement
	add(avg); 
			
	// max placement 
		
	add(max); 
	
	max.setSelected(true); 
			
			
	}
	

	

}
