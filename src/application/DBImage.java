package application;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class DBImage {
	private Image img;
	private ArrayList<String> tags;
	private int numTags;
	private String artist;
	private String name;
	private String type;
	
	public DBImage() {
		img = null;
		tags = new ArrayList<String>();
		artist = "";
		name = "";
		numTags = 0;
	}
	
	public DBImage(Image img, String name, String type) {
		this.img = img;
		this.name = name;
		this.type = type;
	}
	
	public DBImage(Image img, ArrayList<String> tags, String artist, String name) {
		this.img = img;
		this.tags = tags;
		this.artist = artist;
		this.numTags = tags.size();
		this.name = name;
	}
	
	public DBImage(Image img, String tags, String artist, String name, String type) {
		this.img = img;
		this.tags = new ArrayList<String>();
		this.tags.add(tags);
		this.artist = artist;
		this.numTags = 1;
		this.name = name;
	}
	
	
	
	public void setImage(Image image) {
		this.img = image;
	}
	
	public void setImage(String image) {
		this.img = Toolkit.getDefaultToolkit().getImage(image);
	}
	
	public void setImage(File image) throws IOException {
		this.img = ImageIO.read(image);
	}
	
	public void addTag(String tag) {
		this.tags.add(tag);
		numTags += 1;
	}
	
	public void removeTag(String tag) {
		this.tags.remove(tag);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Image getImage() {
		return img;
	}
	
	public int getNumTags() {
		return numTags;
	}
	
	public void setAuthor(String auth) {
		this.artist = auth;
	}
	
	public String getAuthor() {
		return artist.toString();
	}
	
	public String getName() {
		return name.toString();
	}
}
