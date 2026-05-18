package co.edu.udea.calidad.opencart.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ConfirmationUI {

    private ConfirmationUI() {}

    public static final Target CONFIRMATION_HEADER = Target.the("Order confirmation header")
            .located(By.cssSelector("#content h1"));
}
