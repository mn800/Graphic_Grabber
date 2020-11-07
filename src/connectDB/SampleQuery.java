package connectDB;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class SampleQuery {

	public static void main(String[] args) throws Exception {
		String url = "http://dipie111.myweb.cs.uwindsor.ca/test.php";
		 
	    URL UrlObj = new URL(url);
	 
	    // Creates the Connection to website, and sets the Request and Output forms to POST
	    HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setDoOutput(true);
	    
	    // Creates the message to be sent by POST
	    Map<String,String> arguments = new HashMap<>();
	    
	    // First arguments is used in php script
	    arguments.put("param1", "*");
	    arguments.put("param2", "Tags");
	    
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
	    System.out.println("Response Code : " + responseCode);
	 
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
	        System.out.println(response.toString());
	    }
	}
}
