package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import application.DBImage;
import application.PreviewImage;

public class testdisplay{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JPanel images = new JPanel();
		images.setLayout(new GridLayout(0,2));
		
		PreviewImage img1[] = new PreviewImage[10];
		Image img = Toolkit.getDefaultToolkit().getImage("GraphicGrabberLogo.png");
		File img2 = new File("GraphicGrabber.png");
		for(int i = 0; i < 10; i++) {
			img1[i] = new PreviewImage(new DBImage(img, "Super Cool", "Matteus DiPietro", "Graphic Grabber"));
			img1[i].setVisible(true);
			images.add(img1[i]);

			System.out.println("Added Image "+i);
		}

		images.setVisible(true);
		
		JScrollPane scrollimages = new JScrollPane(images);
	
		
		JFrame root = new JFrame();
		root.setLayout(new BorderLayout());
		root.add(BorderLayout.CENTER,scrollimages);
		root.setSize(300, 300);

		root.setVisible(true);	
	}

}
