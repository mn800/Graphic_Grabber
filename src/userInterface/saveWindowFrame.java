package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class saveWindowFrame extends JFrame {

	private JPanel saveWindow;
	private JTextField saveField;
	private JLabel saveLabel;
	private JLabel lblAddTags;
	private JTextField tagField;
	private JButton btnAddTag;
	private JLabel lblAddArtist;
	private JTextField artistField;
	private JButton btnAddArtist;
	private JCheckBox chckbxDeleteOriginalImage;
	private JButton btnSaveImage;



	/**
	 * Create the frame.
	 */
	public saveWindowFrame() {
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
		
		btnAddTag = new JButton("Add");
		GridBagConstraints gbc_btnAddTag = new GridBagConstraints();
		gbc_btnAddTag.anchor = GridBagConstraints.WEST;
		gbc_btnAddTag.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddTag.insets = new Insets(0, 0, 5, 10);
		gbc_btnAddTag.gridx = 3;
		gbc_btnAddTag.gridy = 5;
		saveWindow.add(btnAddTag, gbc_btnAddTag);
		
		lblAddArtist = new JLabel("Add Artist");
		GridBagConstraints gbc_lblAddArtist = new GridBagConstraints();
		gbc_lblAddArtist.anchor = GridBagConstraints.WEST;
		gbc_lblAddArtist.insets = new Insets(0, 12, 5, 5);
		gbc_lblAddArtist.gridx = 0;
		gbc_lblAddArtist.gridy = 7;
		saveWindow.add(lblAddArtist, gbc_lblAddArtist);
		
		artistField = new JTextField();
		GridBagConstraints gbc_artistField = new GridBagConstraints();
		gbc_artistField.gridwidth = 3;
		gbc_artistField.insets = new Insets(0, 10, 5, 5);
		gbc_artistField.fill = GridBagConstraints.HORIZONTAL;
		gbc_artistField.gridx = 0;
		gbc_artistField.gridy = 8;
		saveWindow.add(artistField, gbc_artistField);
		artistField.setColumns(10);
		
		btnAddArtist = new JButton("Add");
		GridBagConstraints gbc_btnAddArtist = new GridBagConstraints();
		gbc_btnAddArtist.anchor = GridBagConstraints.WEST;
		gbc_btnAddArtist.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddArtist.insets = new Insets(0, 0, 5, 10);
		gbc_btnAddArtist.gridx = 3;
		gbc_btnAddArtist.gridy = 8;
		saveWindow.add(btnAddArtist, gbc_btnAddArtist);
		
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
		
		// Resets the Window
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				saveField.setText(null);
				tagField.setText(null);
				artistField.setText(null);
				chckbxDeleteOriginalImage.setSelected(false);
				setVisible(false);
			}
		});
	}


}
