package co.edu.udea.calidad.opencart.questions;

import co.edu.udea.calidad.opencart.userinterfaces.CartUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartContainsProduct implements Question<Boolean> {

    private final String productName;

    public CartContainsProduct(String productName) {
        this.productName = productName;
    }

    public static CartContainsProduct named(String productName) {
        return new CartContainsProduct(productName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String xpath = "//div[@id='content']//table//td[contains(@class,'text-left')]//a[contains(normalize-space(),'"
                + productName.replace("\"", "\\\"") + "')]";
        List<WebElement> elements = BrowseTheWeb.as(actor)
                .getDriver()
                .findElements(By.xpath(xpath));
        return !elements.isEmpty();
    }
}
