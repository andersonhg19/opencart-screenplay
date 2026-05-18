package co.edu.udea.calidad.opencart.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/compra_opencart.feature",
        glue = "co.edu.udea.calidad.opencart.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json"
        }
)
public class Runner {
}
