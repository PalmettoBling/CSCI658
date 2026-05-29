package edu.citadel;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "edu.citadel",
        plugin = {"pretty", "html:target/cucumber-reports/matchmaking.html"}
)
public class MatchmakingRunner {
}
