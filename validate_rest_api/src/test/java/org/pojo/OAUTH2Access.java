package org.pojo;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class OAUTH2Access {

    public static void main(String[]args) throws InterruptedException {
        //get_access_token_for_api();
    }

    public static String get_access_token_for_api(String code) throws InterruptedException {

        // ensure you are signed in to google on your desktop else it would require you to sign in if not logged in
        // code keeps on changing and can be generated everytime by manually pasting the url below in
        // chrome browser and copying the generated code <value> from the browser using the url below
        //https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
        // substitute the newly generated code value with the below code (generated code expires in few mins, regenerate)

        String accessTokenResponse = given()
                .urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token")//.then().extract().response();
                .asString();

        System.out.println("access token response is "+accessTokenResponse);
        JsonPath jp = new JsonPath(accessTokenResponse);
        String access_token = jp.getString("access_token");

        if(access_token.equals("null")){
            System.out.println("No access token was generated, further authentication will fail");
        }
        else System.out.println(access_token);


        //String response = given().queryParam("access_token", access_token).
        //        when().get("https://rahulshettyacademy.com/getCourse.php").asString();

        //System.out.println(response);
        return access_token;
    }
}
