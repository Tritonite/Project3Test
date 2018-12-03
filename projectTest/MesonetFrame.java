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
	
	JPanel panel1 = new JPanel(); 
	JPanel panel2 = new JPanel(new GridBagLayout()); 
	
	MesonetFrame()
	{
		super("Oklahoma Mesonet - Statistics Calculator"); 
		setSize(600, 400); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		MenuBar test = new MenuBar(); 
		setJMenuBar(test);
		
		StatisticsPanel spanel = new StatisticsPanel(); 
		
		
		
				
		JLabel label = new JLabel("Mesonet - We don't set records, we report them!"); 
		
		panel1.add(label); 
		add(panel1, BorderLayout.NORTH); 
		
		add(panel2, BorderLayout.WEST);
		
		
	}
	
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

	public static void main(String[] args) {
		
			MesonetFrame test = new MesonetFrame(); 
			test.setVisible(true); 

	}
}


