package test;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.awt.List;
import java.awt.print.Printable;
import java.lang.reflect.Array;
import java.util.*;

import static org.hamcrest.Matcher.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class APITest {
		
@Test
public void getCall() {
         RestAssured
        .given().log().all()
        .get("https://api.instantwebtools.net/v1/airlines/52aab3841847750ce8ef4a41")
		 .then()
         .statusCode(200);
	}



//newly added line for git commit checking

@Test
public void getCallBodyValidation() {
	
         RestAssured
        .given().log().all()
        .get("https://api.instantwebtools.net/v1/airlines/52aab3841847750ce8ef4a41")
		 .then()
         .statusCode(200)
         .body("name", Matchers.equalTo("Eva Air"))
         .body("country",Matchers.equalTo("Taiwan"))
         .body("slogan",Matchers.equalTo("Sharing the World, Flying Together"))
         .body("website",Matchers.equalTo("www.evaair.com"))
         .body("established",Matchers.equalTo("1989"));
}

     @Test(enabled= true)
     public void getCallValidation() {
    	String response =
    	RestAssured 
    	.given() 
    	.get("https://api.instantwebtools.net/v1/airlines")
    	.then()
    	.extract().response().asString();
    	 
    	 JsonPath path =JsonPath.from(response);
 		List<String> airlineNamesList = path.getList("name");
    	for(String name:airlineNamesList) {
    		System.out.println(name);
    	}
     }

     @Test(enabled= true)
     public void getCallComplexValidation() {
    	String response =
    	RestAssured 
    	.given() 
    	.param("delay","3")
    	.get("https://reqres.in/api/users?delay=3")
    	.then()
    	.extract().response().asString();
    	 
    	 JsonPath path =JsonPath.from(response);
    	 
    	 
 		List<Integer> list=path.getList("data.id");
    	for(int name:list) {
    		System.out.println(name);
    	}
		System.out.println("Size of the list : "+list.size());

    	System.out.println(path.getInt("data.id[4]"));
     }
    	
    	
     }	
    	
     