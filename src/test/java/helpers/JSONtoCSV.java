package helpers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.opendevl.JFlat;

public class JSONtoCSV {
	
	public void CSVconversion(String inputJson, String outputCSV) throws Exception {
	String str = new String(Files.readAllBytes(Paths.get(inputJson)));

    JFlat flatMe = new JFlat(str);

    //get the 2D representation of JSON document
    flatMe.json2Sheet().headerSeparator("_").getJsonAsSheet();

    //write the 2D representation in csv format
    flatMe.write2csv(outputCSV);

}
}
