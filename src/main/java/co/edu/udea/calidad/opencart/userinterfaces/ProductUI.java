package co.edu.udea.calidad.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductUI {

    private ProductUI() {}

    public static final Target ADD_TO_CART_BUTTON = Target.the("Add to Cart button (product detail)")
            .located(By.id("button-cart"));

    public static final Target STOCK_STATUS = Target.the("Stock / availability status text")
            .locatedBy("//ul[contains(@class,'list-unstyled')]//li[contains(., 'Availability') or contains(., 'Stock') or contains(., 'Status')]");
}
