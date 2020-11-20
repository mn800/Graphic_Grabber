package application;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class Base64Encoder {

	public static String encodeFileToBase64Binary(File file){
	    try {
	    	FileInputStream fileInputStreamReader = new FileInputStream(file);
	    	byte[] bytes = new byte[(int)file.length()];
	    	fileInputStreamReader.read(bytes);
	    	String encodedfile = new String(Base64.getEncoder().encodeToString(bytes));
	    	fileInputStreamReader.close();
	    	return encodedfile;
	    }
	    catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
    	}
		return null;
    }
	
	public static ByteArrayInputStream decodeBase64Binary(String file){
	    byte[] imageback = Base64.getDecoder().decode(file);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageback);
		return bis;
    }
}