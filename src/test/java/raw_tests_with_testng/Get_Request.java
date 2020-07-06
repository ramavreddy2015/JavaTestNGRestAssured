package raw_tests_with_testng;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;

import helpers.CSVComparer;
import helpers.CSVtoJSON;
import helpers.JSONtoCSV;
import helpers.JSONtoFile;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.matcher.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;


public class Get_Request {

	/* 
	 * ### Test 1. given() get() then() statusCode(int)
	 */
	@Test
	public void test1() throws Exception {
		
		List < Map < String, String >> list;
		CSVtoJSON CSVObject = new CSVtoJSON();
		JSONtoCSV JSONobject = new JSONtoCSV();
		JSONtoFile JSONFileObject = new JSONtoFile();
		CSVComparer CSVResults = new CSVComparer();
		
		
		JSONobject.CSVconversion("C:\\Users\\ramar\\git\\JavaTestNGRestAssured\\src\\test\\java\\data\\Expected.JSON",  "C:\\Users\\ramar\\git\\JavaTestNGRestAssured\\src\\test\\java\\data\\Expected.csv");
		list = CSVObject.CSVtoJSONConverter("C:\\Users\\ramar\\git\\JavaTestNGRestAssured\\src\\test\\java\\data\\Expected.csv");
		System.out.println(list);
		Response response = given().get("https://samples.openweathermap.org/data/2.5/history/city?q=London,UK&appid=b1b15e\n" + 
        		"88fa797225412429c1c50c122a1");
		
		System.out.println(response.asString());
		JSONFileObject.JSONFileConverter(response.asString(), "C:\\Users\\ramar\\git\\JavaTestNGRestAssured\\src\\test\\java\\data\\Actual.JSON");
		JSONobject.CSVconversion("C:\\Users\\ramar\\git\\JavaTestNGRestAssured\\src\\test\\java\\data\\Actual.JSON",  "C:\\Users\\ramar\\git\\JavaTestNGRestAssured\\src\\test\\java\\data\\Actual.csv");
		CSVResults.CSVResults("C:\\Users\\ramar\\git\\JavaTestNGRestAssured\\src\\test\\java\\data\\Actual.csv", "C:\\Users\\ramar\\git\\JavaTestNGRestAssured\\src\\test\\java\\data\\Expected.csv", "C:\\Users\\ramar\\git\\JavaTestNGRestAssured\\src\\test\\java\\data\\Results.csv");
	}

}
