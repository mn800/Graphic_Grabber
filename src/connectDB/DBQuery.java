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
	
	// Used to insert images into the Database
	public static boolean insertImage() {
		try {
			// Create Arguments from image
			ArrayList<String> args = new ArrayList<String>();
			insertQuery(args);
		
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	// Used to query the database based on tags
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
	
	// Used to query the database based on authors
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

	// Generic query that sends parameters to the php scripts
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
	        
       // Display output
	   // When receiving an image, it will have to be decoded
	   // Consider sending two separate messages, one for image and one for the other fields
	        return response;
	    }
	    else 
	    	return null;
	}
	
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
		    for( i = 0; i < args.size()-1; i++) {
		    	arguments.put(String.format("p%d", i), args.get(i));
		    }
		    
		    arguments.put(String.format("p%d", i),Base64Encoder.encodeFileToBase64Binary(new File(args.get(i))));
		    
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