package TestRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"target/parallel/features/[FEATURE_FILE_NAME].feature"},
        glue={"StepDef"},
        plugin = {"json:./Reports/test-report/cucumber-json-report/[FEATURE_FILE_NAME].json"}
)

public class RunnerFile{
}
