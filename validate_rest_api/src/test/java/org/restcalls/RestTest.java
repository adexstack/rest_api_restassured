package org.restcalls;
import io.restassured.path.json.JsonPath;
import org.files.Payload;
import org.testng.Assert;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class RestTest {
    public static void main(String[] args) {
    baseURI = "https://rahulshettyacademy.com";
    String response = given().log().all().queryParam("key", "qaclick123")
            .header("Content-Type", "application/json")
            .body(Payload.addPlace())
            .when().post("/maps/api/place/add/json")
            .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
            .header("Server", "Apache/2.4.41 (Ubuntu)")
            .extract().response().asString();

        System.out.println("****************************");
        System.out.println(response);

        JsonPath js = ReUsableMethods.rawToJson(response);
        String placeId = js.getString("place_id");
        System.out.println(placeId);

        String newAddress = "70 Summer walk, USA";

        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("/maps/api/place/update/json")
                .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        Assert.assertEquals(newAddress, actualAddress, "Not equal");
    }
}
