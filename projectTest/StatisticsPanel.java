package projectTest;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StatisticsPanel extends JFrame{
	
	JPanel panel2 = new JPanel(new GridBagLayout()); 
	
	public StatisticsPanel(){
		
		
	

	
	
	GridBagConstraints gbc = new GridBagConstraints(); 
	gbc.insets = new Insets(5,5,5,10); 
	
	JRadioButton min = new JRadioButton("MINIMUM");
	JRadioButton max = new JRadioButton("MAXIMUM"); 
	JRadioButton avg = new JRadioButton("AVERAGE"); 
	
	JLabel label3 = new JLabel("Statistics"); 
	
	// min placement 
			gbc.gridx = 1; 
			gbc.gridy = 3; 
			panel2.add(min, gbc); 
			
			// avg placement
			gbc.gridx = 1; 
			gbc.gridy = 8; 
			panel2.add(avg, gbc); 
			
			// max placement 
			gbc.gridx = 1; 
			gbc.gridy = 11; 
			panel2.add(max, gbc); 
			
			
	}
	
	public JComponent getStatisticsPanel()
	{
		return panel2; 
	}
	
	

}
