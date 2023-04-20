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
        return new Object[][] {{"ojfwty","9363"}, {"cwetee","4253"}, {"okmfet","533"}}; // this will perform the test 3 times
        // note you can also pass data from excel using ExcelTest.java (See class implementation) to drive your test from excel

    }

/*    //Another way to use dataprovider you can get e.g data[0][0] from excel, smarter than the hardcoded string used here
    @DataProvider
    public Object[][] getData() {
        // Row stands for how many different data types test should run
        //coloumn stands for how many values per each test

        // Array size is 2
        // 0,1
        Object[][] data = new Object[2][3];
        //0th row
        data[0][0] = "nonrestricteduser@qw.com";
        data[0][1] = "123456";
        data[0][2] = "Restrcited User";
        //1st row
        data[1][0] = "restricteduser@qw.com";
        data[1][1] = "456788";
        data[1][2] = "Non restricted user";

        return data;
    }

 */
}
