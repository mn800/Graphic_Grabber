package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PIdTracker {
	private static String getPId = "http://dipie111.myweb.cs.uwindsor.ca/GraphicGrabber/PIdTracker.php";
	private static String updatePId = "http://dipie111.myweb.cs.uwindsor.ca/GraphicGrabber/UpdatePId.php";	
	
	public static String getCurrentPId() throws Exception{
		 URL UrlObj = new URL(getPId);
		 
	    // Creates the Connection to website, and sets the Request and Output forms to POST
	    HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
	 
	    // Stores response of the URL
	    int responseCode = connection.getResponseCode();
	   	System.out.println(responseCode);
	    // If connection was successful then read output
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader inputReader = new BufferedReader(
	        new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        inputLine = inputReader.readLine();
	        inputReader.close();
	        return inputLine;
	    }
	    else {
	    	return null;
	    }
	}
	
	public static void setCurrentPId(String arg) throws Exception{
		URL UrlObj = new URL(updatePId);
		 
	    // Creates the Connection to website, and sets the Request and Output forms to POST
	    HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setDoOutput(true);
	    
	    // Creates the message to be sent by POST
	    Map<String,String> arguments = new HashMap<>();
	    
	    // Formats arguments into a POST message
	    String PId = "PId="+arg;
;
	    byte[] out = PId.getBytes(StandardCharsets.UTF_8);
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
	    }
	}
}
