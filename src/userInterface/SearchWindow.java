package userInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import application.DBImage;
import application.PreviewImage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

public class SearchWindow extends JFrame implements ActionListener  {
	private  JLabel searchLabel;
	private  JCheckBox checkbox1;
	private  JCheckBox checkbox2;
	private  JLabel searchParameter;
	private  JTextField userText;
	private  JButton button1;
	
	public SearchWindow()
	{ 
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		setTitle("Graphic Grabber");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(800,800);
		setLayout(new GridLayout(1,2));

		panel1.setLayout(new GridLayout(0,2));
		
		PreviewImage img1[] = new PreviewImage[15];
		Image img = Toolkit.getDefaultToolkit().getImage("GraphicGrabberLogo.png");
		for(int i = 0; i < 15; i++) {
			img1[i] = new PreviewImage(new DBImage(img, "Super Cool", "Matteus DiPietro", "Graphic Grabber"));
			panel1.add(img1[i]);

			System.out.println("Added Image "+i);
		}

		
		JScrollPane scrollimages = new JScrollPane(panel1);
		JFrame root = new JFrame();
		root.setLayout(new BorderLayout());
		root.add(BorderLayout.CENTER,scrollimages);
		panel2.setLayout(new GridLayout(6,1));
   
		searchLabel = new JLabel("Search by:");
		panel2.add(searchLabel);
      
       
       // Checkboxes
		checkbox1 = new JCheckBox("Author");
		panel2.add(checkbox1);
		
		checkbox2 = new JCheckBox("Tags");
		panel2.add(checkbox2);
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
		String user = userText.getText();
		
			System.out.println("Searched for "+ user );
		}
	}
		
	


