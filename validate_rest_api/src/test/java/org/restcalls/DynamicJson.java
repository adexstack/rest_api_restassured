package org.restcalls;

import org.files.Payload;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson {
    @Test(dataProvider="BooksData")
    public void addBook(String isbn,String aisle) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        Response resp=given().
                header("Content-Type","application/json").
                body(Payload.addBook(isbn,aisle)).
                when().
                post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response();
        JsonPath js= ReUsableMethods.rawToJson(resp.asString());
        String id=js.get("ID");
        System.out.println(id);

        //deleteBOok
    }

    @DataProvider(name="BooksData")
    public Object[][] getData() {
        //array=collection of elements
        //multidimensional array= collection of arrays
        return new Object[][] {{"ojfwty","9363"}, {"cwetee","4253"}, {"okmfet","533"}};

    }
}
