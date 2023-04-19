package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//add tags to @CucumberOptions parameters to run specific tags as wanted else, all tests in feature file are run e.g
// @CucumberOptions(features="src/test/java/features",tags= {"@AddPlace"},plugin ="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinations"},strict = true)
// will run all features, but to run a specific feature file, use features="src/test/java/features/<featureFileName>"
// plugin ="json:target/jsonReports/cucumber-report.json" (test run generated json report (as you wish))
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin ="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinations"},strict = true)
public class TestRunner {
}
