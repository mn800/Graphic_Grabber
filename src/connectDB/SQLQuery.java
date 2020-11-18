package connectDB;

import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import application.DBImage;

public class SQLQuery {
	
	public String insertImage(DBImage img) {
		try {
			// Create Arguments from image
			ArrayList<String> args = new ArrayList<String>();
			String output = insertQuery(args);
		
			return output;
		} catch(Exception e) {
			return null;
		}
	}
	
	public DBImage tagQuery(ArrayList<String> args) {
		try {
			String response = retrieveQuery(args);
			// Format response
		
			return new DBImage();
		} catch(Exception e) {
			return null;
		}
	}
	
	public DBImage authorQuery(ArrayList<String> args) {
		try {
			String response = retrieveQuery(args);
			// Format response
		
			return new DBImage();
		} catch(Exception e) {
			return null;
		}
	}
	
	private String insertQuery(ArrayList<String> args) throws Exception {
		// Change to the appropriate php script
		String url = "http://dipie111.myweb.cs.uwindsor.ca/test.php";
		 
	    URL UrlObj = new URL(url);
	 
	    // Creates the Connection to website, and sets the Request and Output forms to POST
	    HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setDoOutput(true);
	    
	    // Creates the message to be sent by POST
	    Map<String,String> arguments = new HashMap<>();
	    
	    // First arguments is used in php script
	    arguments.put("param1", args.get(0));
	    arguments.put("param2", args.get(1));
	    
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
	    System.out.println("Send 'HTTP POST' request to : " + url);
	 
	    // Stores response of the URL
	    int responseCode = connection.getResponseCode();
	   	
	    // If connection was successful then read output
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader inputReader = new BufferedReader(
	            new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = inputReader.readLine()) != null) {
	            response.append(inputLine);
	            response.append('\n');
	        }
	        inputReader.close();
	        
	   // When receiving an image, it will have to be decoded
	   // Consider sending two separate messages, one for image and one for the other fields
	        return response.toString();
	    }
	    else 
	    	return "Error Occured";
	}
	
	private String retrieveQuery(ArrayList<String> args) throws Exception {
		String url = "http://dipie111.myweb.cs.uwindsor.ca/test.php";
		 
	    URL UrlObj = new URL(url);
	 
	    // Creates the Connection to website, and sets the Request and Output forms to POST
	    HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setDoOutput(true);
	    
	    // Creates the message to be sent by POST
	    Map<String,String> arguments = new HashMap<>();
	    
	    // First arguments is used in php script
	    arguments.put("param1", args.get(0));
	    arguments.put("param2", args.get(1));
	    
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
	    System.out.println("Send 'HTTP POST' request to : " + url);
	 
	    // Stores response of the URL
	    int responseCode = connection.getResponseCode();
	   	 
	    // If connection was successful then read output
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader inputReader = new BufferedReader(
	            new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = inputReader.readLine()) != null) {
	            response.append(inputLine);
	            response.append('\n');
	        }
	        inputReader.close();
	        
       // Display output
	   // When receiving an image, it will have to be decoded
	   // Consider sending two separate messages, one for image and one for the other fields
	        return response.toString();
	    }
	    else 
	    	return "Error Occured";
	}
}