import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class AuthorQuery {

	public static void main(String[] args) throws Exception {
		String url = "";
		 
	    URL UrlObj = new URL(url);
	 
	    // Creates the Connection to website, and sets the Request and Output forms to POST
	    HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setDoOutput(true);
	    
	    // Creates the message to be sent by POST
	    Map<String,String> arguments = new HashMap<>();
	    
	    // First arguments is used in php script
	    arguments.put("param1", "*"); 
	    arguments.put("param2", "Author");
	    
	    StringBuilder requestData = new StringBuilder();

	    for (Map.Entry<String, String> param : arguments.entrySet()) {
	        if (requestData.length() != 0) {
	            requestData.append('&');
	        }
	        // Encode the parameter based on the parameter map we've defined
	        // and append the values from the map to form a single parameter
	        requestData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	        requestData.append('=');
	        requestData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	    }
 
	    byte[] requestDataBytes = requestData.toString().getBytes("UTF-8");
	    int length = requestDataBytes.length;
	    connection.setFixedLengthStreamingMode(length);
	    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	    
	    // Connects to the URL
	    connection.connect();
	    
	    try (OutputStream writer = connection.getOutputStream()) {
	        writer.write(requestDataBytes);
	        
	        writer.flush();
	        writer.close();
	    }
	    
	    System.out.println("Send 'HTTP POST' request to : " + url);
		 
	    // Stores response of the URL
	    int responseCode = connection.getResponseCode();
	    System.out.println("Response Code : " + responseCode);
	    
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
	        
	        System.out.println(response.toString());
	}
	    

	}
}
