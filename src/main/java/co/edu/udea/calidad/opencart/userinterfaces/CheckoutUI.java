package co.edu.udea.calidad.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutUI {

    private CheckoutUI() {}

    public static final Target STEP1_GUEST_RADIO = Target.the("Guest Checkout radio")
            .located(By.cssSelector("input[name='account'][value='guest']"));
    public static final Target STEP1_CONTINUE = Target.the("Step 1 Continue button")
            .located(By.id("button-account"));

    public static final Target STEP2_FIRSTNAME = Target.the("Billing First Name")
            .located(By.id("input-payment-firstname"));
    public static final Target STEP2_LASTNAME = Target.the("Billing Last Name")
            .located(By.id("input-payment-lastname"));
    public static final Target STEP2_EMAIL = Target.the("Billing Email")
            .located(By.id("input-payment-email"));
    public static final Target STEP2_TELEPHONE = Target.the("Billing Telephone")
            .located(By.id("input-payment-telephone"));
    public static final Target STEP2_ADDRESS_1 = Target.the("Billing Address 1")
            .located(By.id("input-payment-address-1"));
    public static final Target STEP2_CITY = Target.the("Billing City")
            .located(By.id("input-payment-city"));
    public static final Target STEP2_COUNTRY = Target.the("Billing Country")
            .located(By.id("input-payment-country"));
    public static final Target STEP2_ZONE = Target.the("Billing Zone / State")
            .located(By.id("input-payment-zone"));
    public static final Target STEP2_CONTINUE = Target.the("Step 2 Continue button")
            .located(By.id("button-guest"));
    public static final Target STEP2_EMAIL_VALIDATION = Target.the("Email field validation message")
            .locatedBy("//input[@id='input-payment-email']/following-sibling::div[contains(@class,'text-danger')]");

    public static final Target STEP3_SHIPPING_RADIO = Target.the("Shipping method radio (any)")
            .located(By.cssSelector("input[name='shipping_method']"));
    public static final Target STEP3_CONTINUE = Target.the("Step 3 Continue button")
            .located(By.id("button-shipping-method"));

    public static final Target STEP4_PAYMENT_RADIO = Target.the("Payment method radio (any)")
            .located(By.cssSelector("input[name='payment_method']"));
    public static final Target STEP4_AGREE_TERMS = Target.the("Agree to Terms checkbox")
            .located(By.name("agree"));
    public static final Target STEP4_CONTINUE = Target.the("Step 4 Continue button")
            .located(By.id("button-payment-method"));

    public static final Target STEP5_CONFIRM = Target.the("Confirm Order button")
            .located(By.id("button-confirm"));
}
