package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Main implements ActionListener{
	JFrame main;
	JButton Save, Search;
	JPanel[] grid;
	JLabel welcome;
	Image windowIcon;
	Color orange;
	
	public Main() {
		// trying to change the Titlebar colour
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// Initializes elements of the main Window
		JFrame.setDefaultLookAndFeelDecorated(true);
		main = new JFrame("Graphic Grabber");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setMinimumSize(new Dimension(400,500));
		main.setLocationRelativeTo(null);
		windowIcon = Toolkit.getDefaultToolkit().getImage("GraphicGrabberLogo.png");
		main.setIconImage(windowIcon);
		

		
		//Creates the Panels for the Window
		grid = new JPanel[3];
		for(int i = 0; i<3; i++) {
			grid[i] = new JPanel();
			grid[i].setBackground(new Color(220, 220, 220));
		}
		
		welcome = new JLabel("Graphic Grabber");
		welcome.setFont(new Font("Avenir", Font.CENTER_BASELINE, 40));
		grid[0].add(welcome);

		orange = new Color(233,144,74);
		
		// Creates the Button and puts them into panels
		Save = new JButton("Save");
		Save.setBackground(orange);
		Save.setFont(new Font("Avenir", Font.PLAIN, 25));

		Search = new JButton("Search");
		Search.setBackground(orange);
		Search.setFont(new Font("Avenir", Font.PLAIN, 25));

		Save.addActionListener(this);
		Search.addActionListener(this);
		
		// Sets the layout of the window
		main.setLayout(new GridLayout(5,1));
		
		// Sets up and shows the window
		main.add(grid[0]);
		main.add(Save);
		main.add(grid[1]);
		main.add(Search);
		main.add(grid[2]);
		main.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Save") {
			System.out.println("Save");
		}
		else if(e.getActionCommand() == "Search") {
			System.out.println("Search");
		}
	}
}