/*package connectDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Base64;

public class Base64Encoder {
	public static void main(String[] args) {
	    File f =  new File("C:/Users/Desktop/name.jpg"); //Don't know how to save to database
	    String encodstring = encodeFileToBase64Binary(f);
	    System.out.println(encodstring);
    }
	
	private static String encodeFileToBase64Binary(File file){
	    String encodedfile = null;
	    try {
	    	FileInputStream fileInputStreamReader = new FileInputStream(file);
	    	byte[] bytes = new byte[(int)file.length()];
	    	fileInputStreamReader.read(bytes);
	    	encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	    }
	    catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
    	}
    	return encodedfile;
	    }
}*/