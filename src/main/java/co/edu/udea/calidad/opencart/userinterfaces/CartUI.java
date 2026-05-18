package co.edu.udea.calidad.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CartUI {

    private CartUI() {}

    public static final Target EMPTY_CART_MESSAGE = Target.the("Empty cart message")
            .locatedBy("//div[@id='content']//p[contains(., 'Your shopping cart is empty!')]");

    public static final Target CHECKOUT_BUTTON_PAGE = Target.the("Checkout button in cart page")
            .locatedBy("//div[@id='content']//a[normalize-space()='Checkout']");

    public static final Target PRODUCT_NAME_IN_CART = Target.the("Product name {0} in cart row")
            .locatedBy("//div[@id='content']//table//td[contains(@class,'text-left')]//a[contains(normalize-space(),'{0}')]");

    public static final Target REMOVE_PRODUCT_BUTTON = Target.the("Remove product button (X) in cart")
            .locatedBy("(//div[@id='content']//table//button[@data-original-title='Remove' or contains(@class,'btn-danger') or .//i[contains(@class,'fa-times-circle')]])[1]");
}
