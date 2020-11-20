package userInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import application.DBImage;
import connectDB.DBQuery;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

public class SearchWindow extends JFrame implements ActionListener  {
	private  JLabel searchLabel;
	private  JCheckBox checkbox1;
	private  JCheckBox checkbox2;
	private  JLabel searchParameter;
	private  JTextField userText;
	private  JButton button1;
	private JPanel panel1, panel2;
	
	public SearchWindow()
	{ 
		panel1 = new JPanel();
		panel2 = new JPanel();
		setTitle("Graphic Grabber");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(800,800);
		setLayout(new GridLayout(1,2));

		panel1.setLayout(new GridLayout(0,2));
				
		JScrollPane scrollimages = new JScrollPane(panel1);
		JFrame root = new JFrame();
		root.setLayout(new BorderLayout());
		root.add(BorderLayout.CENTER,scrollimages);
		panel2.setLayout(new GridLayout(6,1));
   
		searchLabel = new JLabel("Search by:");
		panel2.add(searchLabel);
      
       
       // Checkboxes
		checkbox1 = new JCheckBox("Artist");
		checkbox1.addActionListener(this);
		panel2.add(checkbox1);
		
		checkbox2 = new JCheckBox("Tags");
		panel2.add(checkbox2);
		checkbox2.addActionListener(this);
		checkbox2.setSelected(false);
       
	       
       // Parameters
		searchParameter = new JLabel("Search Parameter");
		panel2.add(searchParameter);  
       
		userText = new JTextField();
		panel2.add(userText);
   
	   // Button
		button1 = new JButton("Search");
		button1.addActionListener(this);
		panel2.add(button1);	
		
		add(scrollimages);
		add(panel2);
       
       // Resets the Window
	   	this.addWindowListener(new WindowAdapter() {
	   		public void windowClosing(WindowEvent we) {
	   			checkbox1.setSelected(false);
	   			checkbox2.setSelected(false);
	   			userText.setText(null);
	   			setVisible(false);
	   		}
	   	});
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// If Statements used to make sure only one checkbox is selected at a time
		if(e.getActionCommand() == "Artist") {
			if (this.checkbox1.isSelected())
				this.checkbox2.setSelected(false);
		}
		else if(e.getActionCommand() == "Tags") {
			if(this.checkbox2.isSelected()) {
				this.checkbox1.setSelected(false);;
			}
		}
		
		else if(e.getActionCommand() == "Search") {
			// Removes leading and trailing whitespace
			String input = userText.getText().strip();
			
			// Checks if searching by Tag or Artist
			if(this.checkbox1.isSelected()) {
				// If no images were returned display message
				if(!resetPreview(DBQuery.artistQuery(input)))
					JOptionPane.showMessageDialog(this, "No images with Artist: "+input);
				
			}
			else if(this.checkbox2.isSelected()) {
				// If no images were returned display message
				if(!resetPreview(DBQuery.tagQuery(input)))
					JOptionPane.showMessageDialog(this, "No images with Tag: "+input);
			}
			// If neither option is selected, display a message
			else {
				JOptionPane.showMessageDialog(this, "Please select either Artist or Tags to search by.");
			}
			System.out.println("Searched for "+ input );
		}
	}
	
	private boolean resetPreview(ArrayList<DBImage> images) {
		// Checks that there are images to change
		if(images.size() != 0) {
			// Removes previous images in the display
			this.panel1.removeAll();
			PreviewImage img1[] = new PreviewImage[images.size()];
			// Creates the display button for each image
			for(int i = 0; i < images.size(); i++) {
				img1[i] = new PreviewImage(images.get(i));
				img1[i].setSize(100, 100);
				this.panel1.add(img1[i]);
				System.out.println("Added Image "+i);
			}
			// Updates the Frame
			SwingUtilities.updateComponentTreeUI(this.panel1);
			return true;
		}
		else {
			return false;
		}
		
	}
}