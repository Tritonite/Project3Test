package projectTest;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MesonetFrame extends JFrame {
	
	// Mesonet panel
	JPanel panel1 = new JPanel();
	
	MesonetFrame(String title)
	{
		super(title); 
		setSize(600, 400); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		// Making Menu Object
		MenuBar test = new MenuBar(); 
		setJMenuBar(test);
		
		// Making Parameter panel object 
		ParameterPanel ppanel = new ParameterPanel(); 
		// Making Statistics panel object 
		StatisticsPanel spanel = new StatisticsPanel(); 
		// New Panel to combine parameter and statistics 
		JPanel paramStats = new JPanel(new GridBagLayout()); 
		paramStats.add(ppanel);
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.gridx = 1; 
		gbc.gridy = 0; 
		paramStats.add(spanel,gbc);
		
		
		
		// North Panel		
		JLabel label = new JLabel("Mesonet - We don't set records, we report them!"); 
		
		// Adding the panels 
		panel1.add(label); 
		add(panel1, BorderLayout.NORTH);
		add(paramStats, BorderLayout.WEST); 
	
		
		
	}
	
	// Class for creating the file menu bar 
	public class FileMenuBar {
		public FileMenuBar() {
			
		JMenu file = new JMenu("File"); 
		JMenuItem dataFile = new JMenuItem("Open Data File"); 
		JMenuItem exit = new JMenuItem("Exit"); 
		
		file.add(dataFile); 
		file.add(exit); 
		add(file); 
	}

		}

	// Tests the program 
	public static void main(String[] args) {
		
			MesonetFrame test = new MesonetFrame("Oklahoma Mesonet - Statistics Calculator"); 
			test.setVisible(true); 

	}
}


