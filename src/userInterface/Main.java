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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main extends JFrame implements ActionListener{
	JButton Save, Search;
	JPanel[] grid;
	JLabel welcome;
	Image windowIcon;
	Color orange;
	
	public Main() {
		super();
		// Initializes elements of the main Window
		this.setTitle("Graphic Grabber");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400,500));
		this.setLocationRelativeTo(null);
		windowIcon = Toolkit.getDefaultToolkit().getImage("GraphicGrabberLogo.png");
		this.setIconImage(windowIcon);
		

		
		//Creates the Panels for the Window
		grid = new JPanel[3];
		for(int i = 0; i<3; i++) {
			grid[i] = new JPanel();
			grid[i].setBackground(new Color(220, 220, 220));
		}
		
		// Creates a Title Label
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
		this.setLayout(new GridLayout(5,1));
		
		// Sets up and shows the window
		this.add(grid[0]);
		this.add(Save);
		this.add(grid[1]);
		this.add(Search);
		this.add(grid[2]);
		
		// changes look of the frame
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.updateComponentTreeUI(this);
		
		this.setVisible(true);
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