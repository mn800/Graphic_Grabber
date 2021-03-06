package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import application.DBImage;

// Class to display image as well as possiblely download image when pressed
public class PreviewImage extends JButton implements ActionListener{
	private JLabel display;
	private JLabel text;
	private DBImage image;
	
	public PreviewImage(DBImage img) {
		super();
		this.setLayout(new BorderLayout());
		this.image = img;
		this.display = new JLabel();
		this.display.setIcon(new ImageIcon(this.image.getImage().getScaledInstance(150, 150, Image.SCALE_FAST)));
		this.text = new JLabel(image.getName());
		this.text.setSize(10,10);
		this.add(BorderLayout.CENTER, display);
		this.add(BorderLayout.SOUTH, text);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setSize(100, 120);
		this.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.setEnabled(false);
		System.out.println("You want to download this image");
		JFrame showImage = new JFrame();
		JLabel biggerimg = new JLabel(new ImageIcon(image.getImage()));
		showImage.add(biggerimg);
		showImage.setSize(biggerimg.getIcon().getIconWidth(),biggerimg.getIcon().getIconHeight());
		showImage.setVisible(true);
		
		showImage.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setEnabled(true);
				showImage.dispose();
			}
		});
	}
}
