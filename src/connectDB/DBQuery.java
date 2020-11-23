package connectDB;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.imageio.ImageIO;

import application.Base64Encoder;
import application.DBImage;

public class DBQuery {
	// php files for search and upload
	private static String searchURL = "http://dipie111.myweb.cs.uwindsor.ca/GraphicGrabber/search.php";
	private static String insertURL = "http://dipie111.myweb.cs.uwindsor.ca/GraphicGrabber/upload.php";	
	
	/*
	 * Description: function that takes an image and inserts it into the database
	 * Input: An ArrayList that contains the information of the image. Needs to be in form (PId, PName, PType, Picture, Artist, Tag)
	 * Output: Image and appropriate tags are inserted into the database
	 */
	public static boolean insertImage(ArrayList<String> args) {
		try {
			// Create Arguments from image
			insertQuery(args);
		
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/*
	 * Description: Searches the database for images that have a given tag.
	 * Input: Tag to search by
	 * Output: A list of images from the database
	 */
	public static ArrayList<DBImage> tagQuery(String arg) {
		try {
			ArrayList<String> params = new ArrayList<String>();
			params.add("Tags");
			params.add("Tag");
			params.add(arg);
			
			ArrayList<String> response = selectQuery(params);
			// Format response
			
			ArrayList<DBImage> images = new ArrayList<DBImage>();
			for(int i = 0; i < response.size(); i++) {
				String[] output = response.get(i).split(":");
				BufferedImage image = ImageIO.read(Base64Encoder.decodeBase64Binary(output[output.length-1]));
				images.add(new DBImage(image, output[0], output[1]));
			}
			return images;
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}
	
	/*
	 * Description: Searches the database for images by a given artist.
	 * Input: Artist to search by
	 * Output: A list of images from the database
	 */
	public static ArrayList<DBImage> artistQuery(String arg) {
		try {
			ArrayList<String> params = new ArrayList<String>();
			params.add("Artists");
			params.add("Artist");
			params.add(arg);
			
			ArrayList<String> response = selectQuery(params);
			// Format response
			if(response == null)
				return null;
			
			ArrayList<DBImage> images = new ArrayList<DBImage>();
			for(int i = 0; i < response.size(); i++) {
				String[] output = response.get(i).split(":");
				BufferedImage image = ImageIO.read(Base64Encoder.decodeBase64Binary(output[output.length-1]));
				images.add(new DBImage(image, output[0], output[1]));
			}
			
			return images;
		} catch(Exception e) {
			return null;
		}
	}

	/*
	 * Description: Generic function for searching through the database
	 * Input: A list of arguments in the order (Table, Column, value)
	 * Output: The String representation of each image
	 */
	private static ArrayList<String> selectQuery(ArrayList<String> args) throws Exception {
		 
	    URL UrlObj = new URL(searchURL);
	 
	    // Creates the Connection to website, and sets the Request and Output forms to POST
	    HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setDoOutput(true);
	    
	    // Creates the message to be sent by POST
	    Map<String,String> arguments = new HashMap<>();
	    
	    // First arguments is used in php script
	    for(int i = 0; i < args.size(); i++) {
	    	System.out.println(String.format("p%d", i)+args.get(i));
	    	arguments.put(String.format("p%d", i), args.get(i));
	    }
	    
	    // Formats arguments into a POST message
	    StringJoiner sj = new StringJoiner("&");
	    for(Map.Entry<String,String> entry : arguments.entrySet())
	        sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" 
	             + URLEncoder.encode(entry.getValue(), "UTF-8"));
	    byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
	    int length = out.length;
	    
	    // Sets length and type of message
	    connection.setFixedLengthStreamingMode(length);
	    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	    
	    // Connects to the URL
	    connection.connect();
	    
	    // Writes the POST message to the URL
	    try(OutputStream os = connection.getOutputStream()) {
	        os.write(out);
	    }
	    
	    // Informative print statement
	    System.out.println("Send 'HTTP POST' request to : " + searchURL);
	 
	    // Stores response of the URL
	    int responseCode = connection.getResponseCode();
	   	System.out.println(responseCode);
	    // If connection was successful then read output
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader inputReader = new BufferedReader(
	            new InputStreamReader(connection.getInputStream()));
	        String inputLine;

	        ArrayList<String> response = new ArrayList<String>();
	 
	        while ((inputLine = inputReader.readLine()) != null) {
	            response.add(inputLine);
	        }
	        inputReader.close();
	        
	        return response;
	    }
	    else 
	    	return null;
	}
	
	/*
	 * Description: Generic function for inserting into the database
	 * Input: A list of arguments in the order (PId, PName, PType, Picture, Artist, Tag)
	 * Output: True or False depending on if the image was able to be put into the database
	 */
	private static boolean insertQuery(ArrayList<String> args) {
	
		try {
			URL UrlObj = new URL(insertURL);
			 
		    // Creates the Connection to website, and sets the Request and Output forms to POST
		    HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setDoOutput(true);
		    
		    // Creates the message to be sent by POST
		    Map<String,String> arguments = new HashMap<>();
		    
		    // First arguments is used in php script
		    int i = 0;
		    for( i = 0; i < args.size(); i++) {
		    	arguments.put(String.format("p%d", i), args.get(i));
		    }
		    
		    // Formats arguments into a POST message
		    StringJoiner sj = new StringJoiner("&");
		    for(Map.Entry<String,String> entry : arguments.entrySet())
		        sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" 
		             + URLEncoder.encode(entry.getValue(), "UTF-8"));
		    byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
		    int length = out.length;
		    
		    // Sets length and type of message
		    connection.setFixedLengthStreamingMode(length);
		    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		    
		    // Connects to the URL
		    connection.connect();
		    
		    // Writes the POST message to the URL
		    try(OutputStream os = connection.getOutputStream()) {
		        os.write(out);
		    }
		    
		    return true;
		} catch(Exception e) {
			return false;
		}  
	}	
}