package onliner.tests.cucumbertests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features",
        glue = "onliner/tests/cucumbertests/stepdefs",
        tags = "@Onliner"
)

public class CucumberRunner extends AbstractTestNGCucumberTests {
}
