package projectTest;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ParameterPanel extends JPanel {
	// Parameter panel title 
	JLabel label2 = new JLabel("Parameter"); 
	
	// Checkboxes for Parameter panel 
	JCheckBox tair = new JCheckBox("TAIR"); 
	JCheckBox ta9m = new JCheckBox("TA9M"); 
	JCheckBox srad = new JCheckBox("SRAD"); 
	JCheckBox wspd = new JCheckBox("WSPD"); 
	JCheckBox pres = new JCheckBox("PRES"); 
	
	public ParameterPanel()
	{
		super(); 
		
	setLayout(new GridLayout(6,0)); 
	
	label2.setVerticalAlignment(JLabel.TOP);
	tair.setVerticalAlignment(JCheckBox.TOP);
	ta9m.setVerticalAlignment(JCheckBox.TOP);
	srad.setVerticalAlignment(JCheckBox.TOP);
	wspd.setVerticalAlignment(JCheckBox.TOP);
	pres.setVerticalAlignment(JCheckBox.TOP);
	
	add(label2); 
	add(tair); 
	add(ta9m); 
	add(srad); 
	add(wspd); 
	add(pres); 
	}

}
