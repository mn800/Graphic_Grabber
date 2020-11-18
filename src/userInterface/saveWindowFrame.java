package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import application.DBImage;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JWindow;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;

public class saveWindowFrame extends JFrame implements ActionListener{

	private JPanel saveWindow;
	private JTextField saveField;
	private JLabel saveLabel;
	private JLabel lblAddTags;
	private JTextField tagField;
	private JButton btnAddTag;
	private JLabel lblAddArtist;
	private JTextField artistField;
	private JCheckBox chckbxDeleteOriginalImage;
	private JButton btnSaveImage;
	
	// Displays tags that are added so far
	private JLabel tagsList;
	
	private ArrayList<String> tags;

	/**
	 * Create the frame.
	 */
	public saveWindowFrame() {
		tags = new ArrayList<String>();
		setTitle("Graphic Grabber");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 400, 372);
		saveWindow = new JPanel();
		saveWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(saveWindow);
		GridBagLayout gbl_saveWindow = new GridBagLayout();
		gbl_saveWindow.columnWidths = new int[] {136, 0, 30, 0, 0};
		gbl_saveWindow.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_saveWindow.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_saveWindow.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		saveWindow.setLayout(gbl_saveWindow);
		
		saveLabel = new JLabel("Choose Image to Save");
		GridBagConstraints gbc_saveLabel = new GridBagConstraints();
		gbc_saveLabel.anchor = GridBagConstraints.WEST;
		gbc_saveLabel.insets = new Insets(0, 12, 5, 5);
		gbc_saveLabel.gridx = 0;
		gbc_saveLabel.gridy = 1;
		saveWindow.add(saveLabel, gbc_saveLabel);
		
		saveField = new JTextField();
		saveField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_saveField = new GridBagConstraints();
		gbc_saveField.gridwidth = 3;
		gbc_saveField.insets = new Insets(0, 10, 5, 5);
		gbc_saveField.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveField.gridx = 0;
		gbc_saveField.gridy = 2;
		saveWindow.add(saveField, gbc_saveField);
		saveField.setColumns(10);
		
		JButton browseButton = new JButton("Browse");
		GridBagConstraints gbc_browseButton = new GridBagConstraints();
		gbc_browseButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_browseButton.anchor = GridBagConstraints.WEST;
		gbc_browseButton.insets = new Insets(0, 0, 5, 10);
		gbc_browseButton.gridx = 3;
		gbc_browseButton.gridy = 2;
		saveWindow.add(browseButton, gbc_browseButton);
		browseButton.addActionListener(this);
		
		lblAddTags = new JLabel("Add Tags");
		GridBagConstraints gbc_lblAddTags = new GridBagConstraints();
		gbc_lblAddTags.anchor = GridBagConstraints.WEST;
		gbc_lblAddTags.insets = new Insets(0, 12, 5, 5);
		gbc_lblAddTags.gridx = 0;
		gbc_lblAddTags.gridy = 4;
		saveWindow.add(lblAddTags, gbc_lblAddTags);
		
		tagField = new JTextField();
		GridBagConstraints gbc_tagField = new GridBagConstraints();
		gbc_tagField.gridwidth = 3;
		gbc_tagField.insets = new Insets(0, 10, 5, 5);
		gbc_tagField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tagField.gridx = 0;
		gbc_tagField.gridy = 5;
		saveWindow.add(tagField, gbc_tagField);
		tagField.setColumns(10);
		
		btnAddTag = new JButton("Add Tag");
		GridBagConstraints gbc_btnAddTag = new GridBagConstraints();
		gbc_btnAddTag.anchor = GridBagConstraints.WEST;
		gbc_btnAddTag.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddTag.insets = new Insets(0, 0, 5, 10);
		gbc_btnAddTag.gridx = 3;
		gbc_btnAddTag.gridy = 5;
		saveWindow.add(btnAddTag, gbc_btnAddTag);
		btnAddTag.addActionListener(this);
		
		tagsList = new JLabel();
		GridBagConstraints gbc_tagsList = new GridBagConstraints();
		gbc_tagsList.anchor = GridBagConstraints.WEST;
		gbc_tagsList.insets = new Insets(0, 12, 5, 5);
		gbc_tagsList.gridx = 0;
		gbc_tagsList.gridy = 6;
		saveWindow.add(tagsList, gbc_tagsList);
		
		lblAddArtist = new JLabel("Add Artist");
		GridBagConstraints gbc_lblAddArtist = new GridBagConstraints();
		gbc_lblAddArtist.anchor = GridBagConstraints.WEST;
		gbc_lblAddArtist.insets = new Insets(0, 12, 5, 5);
		gbc_lblAddArtist.gridx = 0;
		gbc_lblAddArtist.gridy = 7;
		saveWindow.add(lblAddArtist, gbc_lblAddArtist);
		
		artistField = new JTextField();
		GridBagConstraints gbc_artistField = new GridBagConstraints();
		gbc_artistField.gridwidth = 5;
		gbc_artistField.insets = new Insets(0, 10, 5, 5);
		gbc_artistField.fill = GridBagConstraints.HORIZONTAL;
		//gbc_artistField.gridx = 0;
		gbc_artistField.gridy = 8;
		saveWindow.add(artistField, gbc_artistField);
		artistField.setColumns(10);
				
		chckbxDeleteOriginalImage = new JCheckBox("Delete Original Image?");
		chckbxDeleteOriginalImage.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxDeleteOriginalImage.setToolTipText("Checking this off will delete the image off of your computer after saving it to the database.");
		GridBagConstraints gbc_chckbxDeleteOriginalImage = new GridBagConstraints();
		gbc_chckbxDeleteOriginalImage.anchor = GridBagConstraints.WEST;
		gbc_chckbxDeleteOriginalImage.insets = new Insets(0, 6, 5, 5);
		gbc_chckbxDeleteOriginalImage.gridx = 0;
		gbc_chckbxDeleteOriginalImage.gridy = 9;
		saveWindow.add(chckbxDeleteOriginalImage, gbc_chckbxDeleteOriginalImage);
		
		btnSaveImage = new JButton("Save Image");
		GridBagConstraints gbc_btnSaveImage = new GridBagConstraints();
		gbc_btnSaveImage.gridwidth = 4;
		gbc_btnSaveImage.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveImage.gridx = 0;
		gbc_btnSaveImage.gridy = 10;
		saveWindow.add(btnSaveImage, gbc_btnSaveImage);
		btnSaveImage.addActionListener(this);

		
		// Resets the Window
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				saveField.setText("");
				tagField.setText("");
				artistField.setText("");
				tagsList.setText("");
				tags.clear();
				chckbxDeleteOriginalImage.setSelected(false);
				setVisible(false);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("A Button was pressed");
		if(arg0.getActionCommand() == "Browse") {
			browseImages();
		}
		if(arg0.getActionCommand() == "Add Tag") {
			addTags();
		}
		if(arg0.getActionCommand() == "Save Image") {
			saveImage();
		}
	}
	
	/*
	 * Description: Allows the user to select an image from the device to upload to the database
	 * Input: None
	 * Output: Fills the Image text field with the absolute path of the selected image
	 */
	private void browseImages() {
		JFileChooser fchooser = new JFileChooser();
		fchooser.addChoosableFileFilter(new FileNameExtensionFilter("Images","jpg","png","bmp"));
		fchooser.setAcceptAllFileFilterUsed(false);
		int i = fchooser.showOpenDialog(this);
		JFrame test1 = new JFrame();
		test1.add(fchooser);
		test1.setMinimumSize(new Dimension(500,500));
		test1.setVisible(true);
		if(i==JFileChooser.APPROVE_OPTION) {
			File file = fchooser.getSelectedFile();
			String filen = file.getAbsolutePath();
			System.out.println(file);
			test1.removeAll();
			test1.dispose();
			saveField.setText(filen);
		}
		if(i==JFileChooser.CANCEL_OPTION) {
			System.out.println("Cancel Chosen");
			test1.removeAll();
			test1.dispose();
		}
		if(i==JFileChooser.ABORT) {
			System.out.println("Error Chosen");
		}
	}
	
	/*
	 * Description: Action to be performed when the add tags button is pressed. Adds tags to the tag list
	 * 				add updates the list for the user
	 * Input:
	 * Output: Adds another tag to taglist and adds to the text of tagField
	 */
	private void addTags() {
		System.out.println("Pressed Add Tag");
		if(!tagField.getText().isEmpty()) {
			tags.add(tagField.getText());
			if(tagsList.getText().isEmpty())
				tagsList.setText(tagField.getText());
			else
				tagsList.setText(tagsList.getText()+","+tagField.getText());
			System.out.println(tags.toString());
			tagField.setText("");
		}
	}
	
	/*
	 * Description: Creates a DBImage to be sent to the database
	 * Input: None
	 * Output: Image is inserted into the database
	 */
	private void saveImage() {
		try {
			Path name = Path.of(saveField.getText());
			//DBImage image = new DBImage(ImageIO.read(new URL(name.toString())),tags,artistField.getText(),name.getFileName().toString());
			System.out.println("Inserted into Database photo: "+name.getFileName().toString());
			saveField.setText("");
			tagField.setText("");
			artistField.setText("");
			tagsList.setText("");
			tags.clear();
			chckbxDeleteOriginalImage.setSelected(false);
			setVisible(false);
			//Delete original image if selected
			
			//Send to application to send to database
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println("That file does not exist");
			//e.printStackTrace();
		}
		
	}
}