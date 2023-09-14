package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@Cucumber",
        features = "src/test/java/Cucumber",
        glue = "Steps",
        monochrome = true,
        plugin = {"html:target/cucumber.html", "pretty"},
        publish = true)

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
