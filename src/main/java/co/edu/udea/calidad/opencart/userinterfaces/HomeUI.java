package co.edu.udea.calidad.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomeUI {

    private HomeUI() {}

    public static final Target MENU_CATEGORY = Target.the("Top menu category {0}")
            .locatedBy("//header//nav//a[contains(@class,'dropdown-toggle') and normalize-space()='{0}']");

    public static final Target CART_LINK = Target.the("Header cart link")
            .located(By.id("cart-total"));

    public static final Target CART_VIEW_BUTTON = Target.the("View cart button (mini-cart)")
            .locatedBy("//div[@id='cart']//strong[contains(., 'View Cart')]/..");

    public static final Target CART_CHECKOUT_BUTTON = Target.the("Checkout button (mini-cart)")
            .locatedBy("//div[@id='cart']//strong[contains(., 'Checkout')]/..");

    public static final Target ALERT_SUCCESS = Target.the("Success alert after add to cart")
            .locatedBy("//div[contains(@class,'alert') and (contains(.,'Success') or contains(.,'added'))]");

    public static final Target CART_TOTAL_TEXT = Target.the("Cart total text in header")
            .locatedBy("//div[@id='cart']//span[@id='cart-total'] | //*[@id='cart-total']");
}
