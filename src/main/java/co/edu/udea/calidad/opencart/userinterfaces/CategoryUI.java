package co.edu.udea.calidad.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CategoryUI {

    private CategoryUI() {}

    public static final Target PRODUCT_LINK = Target.the("Product link {0} in category grid")
            .locatedBy("//a[contains(normalize-space(),'{0}') and (contains(@href,'product_id') or contains(@href,'product/product'))]");

    public static final Target PRODUCT_ADD_TO_CART = Target.the("Add to cart button for {0}")
            .locatedBy("//*[(self::div or self::article) and .//a[contains(normalize-space(),'{0}')]]//button[contains(@onclick,'cart.add')]");

    public static final Target FIRST_PRODUCT_NAME = Target.the("First product link in category page")
            .locatedBy("(//a[contains(@href,'product_id') or contains(@href,'product/product')])[1]");

    public static final Target FIRST_PRODUCT_ADD_TO_CART = Target.the("First product Add-to-Cart button")
            .locatedBy("(//button[contains(@onclick,'cart.add')])[1]");
}
