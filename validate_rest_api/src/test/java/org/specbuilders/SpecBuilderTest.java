package org.specbuilders;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.specbuilders.AddPlace;
import org.specbuilders.Location;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {

	// Deserialization (Constructing Json payload using POJO and smartly using SpecBuilders for smart code re-usability
	public static void main(String[] args) {

		String baseURI = "https://rahulshettyacademy.com";
		String addPlaceResource = "/maps/api/place/add/json";

		RestAssured.baseURI = baseURI;

		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite(baseURI);
		p.setName("Frontline house");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		// Creating reusable request object that can be used by multiple tests
		RequestSpecification req = new RequestSpecBuilder().setBaseUri(baseURI).addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();

		// Creating reusable response object that can be used by multiple tests (customize as needed)
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		// Reusable request object
		RequestSpecification res = given().spec(req)
				.body(p);

		// Hit API and get response
		Response response = res.when().post(addPlaceResource)
							.then().spec(resspec).extract().response();

		String responseString = response.asString();
		System.out.println(responseString);

	}
}