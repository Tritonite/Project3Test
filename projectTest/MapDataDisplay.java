package projectTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MapDataDisplay extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	


	
	
	JPanel panel1 = new JPanel(); 
	JPanel panel2 = new JPanel(new GridBagLayout()); 
	JPanel panel3 = new JPanel(new GridBagLayout()); 

	
	// checkboxes for datatypes 
	JCheckBox tair = new JCheckBox("TAIR"); 
	JCheckBox ta9m= new JCheckBox("TA9M"); 
	JCheckBox srad = new JCheckBox("SRAD"); 
	JCheckBox wspd = new JCheckBox("WSPD"); 
	JCheckBox pres = new JCheckBox("PRES"); 
	
	
	// buttons for measurements 
	JRadioButton min = new JRadioButton("MINIMUM");
	JRadioButton max = new JRadioButton("MAXIMUM"); 
	JRadioButton avg = new JRadioButton("AVERAGE"); 

	
	// calculation and exit buttons 
	JButton calculate = new JButton("Calculate"); 
	JButton exit = new JButton("Exit"); 
	
	
	JTable table = new JTable(); 
	
	Object[] colums = {"Station", "Parameter", "Statistics", "Value", "Reporting Stations", "Date"}; 
	DefaultTableModel model = new DefaultTableModel();

	
	
	
	public MapDataDisplay()
	{
		
		super("Oklahoma Mesonet - Statistics Calculator"); 
		setSize(600, 400); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		MenuBar test = new MenuBar(); 
		setJMenuBar(test);
		
		GridBagConstraints gbc = new GridBagConstraints(); 
		
		
		
		gbc.insets = new Insets(5,5,5,10); 
		
		
		JLabel label = new JLabel("Mesonet - We don't set records, we report them!"); 
		
		JLabel label2 = new JLabel("Parameter"); 
		
		JLabel label3 = new JLabel("Statistics"); 
		
		
		
		panel1.add(label); 
		
		panel2.add(label2);
		
		panel2.add(label3, gbc); 
		// tair placement
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		panel2.add(tair, gbc); 
		// ta9m placement
		gbc.gridx = 0; 
		gbc.gridy = 4; 
		
		panel2.add(ta9m, gbc); 
		// srad placement
		gbc.gridx = 0; 
		gbc.gridy = 8; 
		panel2.add(srad, gbc); 
	
		// wspd placement
		gbc.gridx = 0; 
		gbc.gridy = 10; 
		panel2.add(wspd, gbc);
	
		// pres placement 
		gbc.gridx = 0; 
		gbc.gridy = 12; 
		panel2.add(pres, gbc); 
		
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
		
		
		// Calculate and Exit button placements 
		panel3.add(calculate); 
		gbc.gridx = 1; 
		gbc.gridy = 0; 
		panel3.add(exit, gbc); 
		
		
		
		
		
		
		
		
		
		// Maximimum set defaulted 
		max.setSelected(true); 
		
		// Displacements of information 
		add(panel1, BorderLayout.NORTH); 
		add(panel2, BorderLayout.WEST);
		add(panel3, BorderLayout.SOUTH); 
		
	
		
	}
	public static void main(String arg[])
	{
		MapDataDisplay test = new MapDataDisplay(); 
		test.setVisible(true); 
	}
	
	
	
}
