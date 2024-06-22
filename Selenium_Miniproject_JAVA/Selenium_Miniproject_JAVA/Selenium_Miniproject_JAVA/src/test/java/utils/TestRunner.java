package utils;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue= "stepDefinitions",
        plugin = {"pretty","html:target/cucumber"})
public class TestRunner {
}
