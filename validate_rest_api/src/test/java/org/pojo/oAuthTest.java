package org.pojo;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import io.restassured.parsing.Parser;

public class oAuthTest {

	// Before this test can pass, ensure new code is generated for OAUTH2Access.java for access_token generation
	public static void main(String[] args) throws InterruptedException {
			/* Disabling getting access token via browser as google has blocked this. Hence manually getting code and

			WebDriverManager.chromedriver().setup();
			WebDriver driver= new ChromeDriver();
			driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
			driver.findElement(By.cssSelector("input[type='email']")).sendKeys("sdetdevops");
			driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Sdet1Devops1@");
			driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
			Thread.sleep(4000);
			String url=driver.getCurrentUrl();
			String partialcode=url.split("code=")[1];
			String code=partialcode.split("&scope")[0];
			System.out.println(code);
			 */

		// generating access_token from code used in OAUTH2Access. Ensure you regenerate the code for this test to pass
		// see  OAUTH2Access on how to generate code before running this class
		String code = "4%2F0AVHEtk6ZiuylbH3dMF-sXh41nNTSLOskvvH3vwi89a81po31NWNWoZ0iceqCFMDxZINZLQ";
		String accessToken = OAUTH2Access.get_access_token_for_api(code);

		// Getting response as string only for debugging purpose
		String gcAsString=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();

		// print all the json response body to string for debugging purpose only
		System.out.println(gcAsString);

		// POJO:  (Serialization) Getting Json response as Java Object
		GetCourse gc=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

		// POJO: printing out values from serialized Java Object
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		
		List<Api> apiCourses=gc.getCourses().getApi();
		for (Api apiCours : apiCourses) {
			if (apiCours.getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCours.getPrice());
			}
		}

		String[] courseTitlesArray= { "Selenium Webdriver Java","Cypress","Protractor"};
		

		ArrayList<String> webAutoCourseTitleList = new ArrayList<>();

		//Get all list of webAutomation
		List<WebAutomation> webAutomationList=gc.getCourses().getWebAutomation();

		for (WebAutomation webAutomation : webAutomationList) {
			// adding all webAutomation courseTitle to list
			webAutoCourseTitleList.add(webAutomation.getCourseTitle());
		}

		// converting array to array list so I can assert with list
		List<String> expectedList=	Arrays.asList(courseTitlesArray);

		Assert.assertEquals(expectedList, webAutoCourseTitleList);
	}

}
