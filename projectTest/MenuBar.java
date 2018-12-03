package projectTest;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	
	public MenuBar()
	{
		JMenu file = new JMenu("File"); 
		JMenuItem dataFile = new JMenuItem("Open Data File"); 
		JMenuItem exit = new JMenuItem("Exit"); 
		
		file.add(dataFile); 
		file.add(exit); 
		add(file); 
	}
}
