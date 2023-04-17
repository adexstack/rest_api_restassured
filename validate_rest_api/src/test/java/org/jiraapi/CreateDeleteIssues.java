package org.jiraapi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

// Note that for this test to pass: Jira server (not cloud) has to be installed, started, code updated with credentials, project key
public class CreateDeleteIssues {
    @Test
    public void create_delete_jira_issue() throws IOException {
        String projectDir = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        // Create cookie session
        RestAssured.baseURI = "http://localhost:8080";
        String response = given().header("content-type", "application/json").
                body("{ \"username\": \"sdetdevops\", \"password\": \"jira9595\" }").when().post("/rest/auth/1/session").
                then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(response);
        String name = js.getString("session.name");
        String value = js.getString("session.value");
        String cookie = (name+"="+value);
        System.out.println(name);
        System.out.println(value);

        // Create issue (Note that Project key is required in payload)
        String issue = given().header("content-type", "application/json").header("cookie",cookie)
                .body(new String(Files.readAllBytes(Paths.get(projectDir+"/jiraapi-resources/createIssue.json"))))
                .when().post("/rest/api/2/issue")
                .then().assertThat().statusCode(201).extract().response().asString();
        JsonPath js1 = new JsonPath(issue);
        String id = js1.getString("id");
        System.out.println(id);

        //Delete issue
        given().when().header("cookie",cookie).
                when().delete("/rest/api/2/issue/"+id)
                .then().assertThat().statusCode(204);
    }
}
