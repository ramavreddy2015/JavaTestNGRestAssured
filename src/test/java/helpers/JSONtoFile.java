package helpers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
import java.io.FileWriter;
import java.io.IOException;

public class JSONtoFile {
	
	 
	/**
	 * @author Crunchify.com
	 * How to write JSON object to File in Java?
	 */
	 
	    private static FileWriter file;
	 
	    @SuppressWarnings("unchecked")
	    public static void JSONFileConverter(String obj, String ActualJson) {
	 
	        // JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
	      
	        try {
	 
	            // Constructs a FileWriter given a file name, using the platform's default charset
	            file = new FileWriter(ActualJson);
	            file.write(obj);
	            CrunchifyLog("Successfully Copied JSON Object to File...");
	            CrunchifyLog("\nJSON Object: " + obj);
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	 
	        } finally {
	 
	            try {
	                file.flush();
	                file.close();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
	 
	    static public void CrunchifyLog(String str) {
	        System.out.println("str");
	    }
	 
	}

